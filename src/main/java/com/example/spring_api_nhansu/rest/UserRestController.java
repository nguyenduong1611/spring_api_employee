package com.example.spring_api_nhansu.rest;

import com.example.spring_api_nhansu.entity.User;
import com.example.spring_api_nhansu.service.EmailSenderService;
import com.example.spring_api_nhansu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.findAllUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<User> addUser(@RequestBody User user) throws IOException {
//        User newUser = userService.addUser(user);
//        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
//    }
    @PostMapping("/addfile")
    public ResponseEntity<User> addUserfile(@RequestParam(value = "username",required = false) String username,
                                            @RequestParam(value = "passwordHash",required = false) String passwordHash,
                                            @RequestParam(value = "name",required = false) String name,
                                            @RequestParam(value = "birthday",required = false) Date birthday,
                                            @RequestParam(value = "email",required = false) String email,
                                            @RequestParam(value = "avatar",required = false) MultipartFile avatar,
                                            @RequestParam(value = "level_id",required = false) Integer level_id,
                                            @RequestParam(value = "status_id",required = false) Integer status_id
                                            ) throws IOException {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(userService.generatedpassword());
        String passwordRandom = newUser.getPassword();
        System.out.println(passwordRandom);
        String sha256hex = DigestUtils.sha256Hex(passwordRandom + passwordHash);
        newUser.setPasswordHash(sha256hex);
        newUser.setName(name);
        newUser.setBirthday(birthday);
        newUser.setEmail(email);
        File myfile = new File(FILE_DIRECTORY + avatar.getOriginalFilename());
        myfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myfile);
        fos.write(avatar.getBytes());
        fos.close();
        newUser.setAvatar(String.valueOf(myfile));
        newUser.setLevel_id(level_id);
        newUser.setStatus_id(status_id);
        System.out.println(email);
        userService.addUser(newUser);
        emailSenderService.sendEmail(email, "Tạo tài khoản thành công", "Bạn đã tạo tài khoản thành công. Truy cập đường linnk bên dưới để thiết lập mật khẩu");
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<User> changePassword(@RequestParam(value = "password", required = false) String password){
        String sha256hex = DigestUtils.sha256Hex(password);
        User updatePasswordHash = new User();
        updatePasswordHash.setPasswordHash(sha256hex);
        System.out.println(sha256hex);
//        userService.updateUser(updatePasswordHash);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(
                                            @RequestParam(value = "id",required = false) Integer id,
                                            @RequestParam(value = "username",required = false) String username,
                                            @RequestParam(value = "passwordHash",required = false) String passwordHash,
                                            @RequestParam(value = "name",required = false) String name,
                                            @RequestParam(value = "birthday",required = false) Date birthday,
                                            @RequestParam(value = "email",required = false) String email,
                                            @RequestParam(value = "avatar",required = false) MultipartFile avatar,
                                            @RequestParam(value = "level_id",required = false) Integer level_id,
                                            @RequestParam(value = "status_id",required = false) Integer status_id
                                            ) throws IOException {
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setUsername(username);
        updateUser.setPasswordHash(passwordHash);
        updateUser.setName(name);
        updateUser.setBirthday(birthday);
        updateUser.setEmail(email);
        File myfile = new File(FILE_DIRECTORY + avatar.getOriginalFilename());
        myfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myfile);
        fos.write(avatar.getBytes());
        fos.close();
        updateUser.setAvatar(String.valueOf(myfile));
        updateUser.setLevel_id(level_id);
        updateUser.setStatus_id(status_id);
        userService.updateUser(updateUser);

        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File myfile = new File(FILE_DIRECTORY + file.getOriginalFilename());
        myfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myfile);
        fos.write(file.getBytes());
        fos.close();
        return new ResponseEntity<Object>("Upload successful", HttpStatus.OK);
    }



}
