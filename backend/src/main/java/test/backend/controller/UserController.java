package test.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.backend.model.dto.CreateUser;
import test.backend.model.dto.NotifyCreateUser;
import test.backend.model.dto.UpdateUserEmail;
import test.backend.model.dto.UserCreated;
import test.backend.model.dto.UserEmailUpdated;
import test.backend.model.dto.UserUpdated;
import test.backend.model.dto.ValidateEmail;
import test.backend.model.entity.UserEntity;
import test.backend.service.EmailNotificationService;
import test.backend.service.UserService;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserCreated createUser(@RequestBody CreateUser createUser) {
        UserEntity user = userService.createUser(createUser);
        return UserCreated.from(user);
    }

    @GetMapping
    public List<UserCreated> getAllUsers() {
        return UserCreated.from(userService.findAll());
    }

    @PostMapping("/email-check")
    public void checkEmailAvailability(@RequestBody ValidateEmail validateEmail) {
        userService.checkEmail(validateEmail);
    }

    @GetMapping("/{uuid}")
    public UserCreated findUser(@PathVariable UUID uuid) {
        return UserCreated.from(userService.findByUuid(uuid));
    }

    @PatchMapping("/{uuid}")
    public UserUpdated updateUser(@PathVariable("uuid") UUID uuid, @RequestBody UpdateUser updateUser) {
        UserEntity user = userService.updateUser(uuid, updateUser);
        return UserUpdated.from(user);
    }

    @PatchMapping("/{uuid}/updateEmail")
    public UserEmailUpdated updateEmail(@PathVariable("uuid") UUID uuid, @RequestBody UpdateUserEmail updateUserEmail) {
        UserEntity user = userService.updateUserEmail(uuid, updateUserEmail);
        return UserEmailUpdated.from(user);
    }
}

