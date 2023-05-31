package com.kz.events.listener;

import com.kz.entity.User;
import com.kz.events.RegistrationCompleteEvent;
import com.kz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener
        implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        // Create Verification Token for the User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);
        // Send Mail To User
        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;

        //send verification mail
        log.info("Click the link to verify your account: {}", url);
    }

}
