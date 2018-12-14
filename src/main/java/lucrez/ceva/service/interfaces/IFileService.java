package lucrez.ceva.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;

public interface IFileService {
    void save(MultipartFile file, String where);
    void saveAsImage(MultipartFile file, String where);
    boolean isImage(MultipartFile file);
    Path load(String location);

    Resource loadAsResource(String s);

    InputStream loadAsStream(String s);
}
