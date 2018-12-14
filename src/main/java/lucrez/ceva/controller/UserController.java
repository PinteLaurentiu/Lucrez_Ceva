package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.*;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.dto.mappers.UpdateUserMapper;
import lucrez.ceva.dto.mappers.UserMapper;
import lucrez.ceva.model.User;
import lucrez.ceva.service.interfaces.IFileService;
import lucrez.ceva.service.interfaces.IUserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserController {
    private IUserService userService;
    private UserMapper mapper;
    private IFileService fileService;

    @PostMapping("/unauthenticated/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            User user = mapper.dtoToModelUser(userDto);
            userService.save(user);
            return ResponseStatus.create();
        } catch (Exception ex) {
            return ResponseError.create(ex.getMessage());
        }
    }

    @GetMapping("/unauthenticated/activate")
    public ResponseEntity<?> activate(@RequestParam Long id, @RequestParam String uuid) {
        try {
            userService.activate(id, uuid);
            return new ResponseEntity<>("Account activated!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("This activation link is invalid! Try recreating your account!",
                    HttpStatus.OK);
        }
    }

    @ResponseBody
    @GetMapping(value = "/unauthenticated/avatar-{userId}/avatar.png", produces = "image/png")
    public ResponseEntity<?> pngAvatar(@PathVariable Long userId) {
        return getAvatar(userId);
    }

    @ResponseBody
    @GetMapping(value = "/unauthenticated/avatar-{userId}/avatar.jpg", produces = "image/jpeg")
    public ResponseEntity<?> jpegAvatar(@PathVariable Long userId) {
        return getAvatar(userId);
    }

    @ResponseBody
    @GetMapping(value = "/unauthenticated/avatar-{userId}/avatar.bmp", produces = "image/bmp")
    public ResponseEntity<?> bmpAvatar(@PathVariable Long userId) {
        return getAvatar(userId);
    }

    private ResponseEntity<?> getAvatar(Long userId) {
        User user = userService.get(userId);
        if (user == null)
            return ResponseStatus.create("There is no user with this id!");
        Resource resource = fileService.loadAsResource("avatar/"+userId);
        return ResponseStatus.create(resource);
    }


    @PostMapping("/authenticated/test")
    public ResponseEntity<?> test(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        fileService.saveAsImage(file, "barbiduc.png");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/authenticated/update-account-details")
    public ResponseEntity<?> update(@RequestBody UpdateUserDto updateDto) {
        try {
            User user = userService.getCurrent();
            UpdateUserMapper.updateUserDetails(user, updateDto);
            userService.update(user);
            return ResponseStatus.create();
        } catch (Exception ex) {
            return ResponseError.create(ex.getMessage());
        }
    }

    @PostMapping("/authenticated/upload-avatar")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        return ResponseStatus.create();
    }
}
