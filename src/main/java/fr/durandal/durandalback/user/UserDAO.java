package fr.durandal.durandalback.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestUserController {
    @RequestMapping("/testUser")
    public User getTestUser() {
        return User.getTestUser();
    }
}