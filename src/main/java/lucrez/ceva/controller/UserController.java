package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.dto.UpdateUserDto;
import lucrez.ceva.dto.UserDto;
import lucrez.ceva.dto.mappers.UpdateUserMapper;
import lucrez.ceva.dto.mappers.UserAdminMapper;
import lucrez.ceva.dto.mappers.UserMapper;
import lucrez.ceva.model.User;
import lucrez.ceva.service.interfaces.IFileService;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserController {
    private IUserService userService;
    private UserMapper mapper;
    private IFileService fileService;

    @PostMapping("/unauthenticated/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User user = mapper.dtoToModelUser(userDto);
        userService.save(user);
        return ResponseStatus.create();
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
    @GetMapping(value = "/unauthenticated/avatar-{userId}/avatar.jpeg", produces = "image/jpeg")
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
        Resource resource = fileService.loadAsResource(userService.getAvatarPath(user));
        return ResponseStatus.create(resource);
    }

    @PostMapping("/authenticated/update-account-details")
    public ResponseEntity<?> update(@RequestBody UpdateUserDto updateDto) {
        User user = userService.getCurrent();
        UpdateUserMapper.updateUserDetails(user, updateDto);
        userService.update(user);
        return ResponseStatus.create();
    }

    @PostMapping("/authenticated/upload-avatar")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        User user = userService.getCurrent();
        fileService.saveAsImage(file, userService.getAvatarPath(user));
        userService.changeAvatarPath(user, fileService.getImageExtension(file));
        return ResponseStatus.create();
    }

    @GetMapping(value = "/authenticated/test")
    public ResponseEntity<?> test() {
        return ResponseStatus.create();
    }

    @GetMapping(value = "/authenticated/whoAmI")
    public ResponseEntity<?> userPrincipal() {
        return new ResponseEntity<>(UserAdminMapper.toDto(userService.getCurrent()), HttpStatus.OK);
    }

    @GetMapping(value = "/unauthenticated/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(UserAdminMapper.toDto(userService.get(id)), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> catchExceptions(Exception ex) {
        return ResponseStatus.create(ex);
    }
}
