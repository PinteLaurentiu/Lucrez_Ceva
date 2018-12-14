package lucrez.ceva.service;

import lucrez.ceva.exceptions.ValidationException;
import lucrez.ceva.service.interfaces.IFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService implements IFileService {
    private static final int BUFFER_SIZE = 0x1000; // 4KB
    private static final long MAX_SIZE = 0x300000; // 3MB
    private static final List<String> imageTypes = Arrays.asList("image/png", "image/jpeg", "image/bmp");
    private static final String FILES_LOCATION = "files/";

    private final Validator<MultipartFile> sizeValidator = new Validator<>(this::validateSize);
    private final Validator<MultipartFile> imageValidator = new Validator<>(this::validateImageType);

    @Override
    public void save(MultipartFile file, String where) {
        sizeValidator.validate(file);
        uncheckedSave(file, where);
    }

    private void uncheckedSave(MultipartFile file, String where) {
        try (OutputStream out = new FileOutputStream(getLocation(where))) {
            InputStream in = file.getInputStream();
            long resultSize = copyAll(in, out);
            if (resultSize != file.getSize())
                throw new RuntimeException("The copy process failed!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getLocation(String where) {
        return FILES_LOCATION + where;
    }

    private long copyAll(InputStream in, OutputStream out) throws IOException {
        long size = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = in.read(buffer);
        while (bytesRead != -1) {
            out.write(buffer, 0, bytesRead);
            size += bytesRead;
            bytesRead = in.read(buffer);
        }
        return size;
    }

    @Override
    public void saveAsImage(MultipartFile file, String where) {
        sizeValidator.validate(file);
        imageValidator.validate(file);
        uncheckedSave(file, where);
    }

    @Override
    public boolean isImage(MultipartFile file) {
        return imageTypes.contains(file.getContentType());
    }

    @Override
    public Path load(String name) {
        String location = getLocation(name);
        if (!Files.exists(Paths.get(location)))
            throw new RuntimeException("File doesn't exist!");
        return Paths.get(location);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    @Override
    public InputStream loadAsStream(String filename) {
        Resource resource = loadAsResource(filename);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    private void validateSize(MultipartFile multipartFile) {
        if (multipartFile.getSize() > MAX_SIZE)
            throw new ValidationException("File is too big maximum size is 3MB");
    }

    private void validateImageType(MultipartFile multipartFile) {
        if (!isImage(multipartFile))
            throw new ValidationException("File is not an image");
    }
}
