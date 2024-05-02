package com.acme.myshoes.platform.authentication;

import com.acme.myshoes.platform.authentication.api.rest.UserController;
import com.acme.myshoes.platform.authentication.domain.model.User;
import com.acme.myshoes.platform.authentication.domain.service.UserService;
import com.acme.myshoes.platform.authentication.mapping.UserMapper;
import com.acme.myshoes.platform.authentication.resource.CreateUserResource;
import com.acme.myshoes.platform.authentication.resource.UpdateUserResource;
import com.acme.myshoes.platform.authentication.resource.UserResource;
import com.acme.myshoes.platform.shared.exception.ResourceValidationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private Validator validator;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserTest() {
        // Mock data
        CreateUserResource createUserResource = new CreateUserResource();
        createUserResource.setName("Test User");
        createUserResource.setEmail("test@example.com");
        createUserResource.setPassword("password");

        User mappedUser = new User();
        mappedUser.setId(1L);
        mappedUser.setName(createUserResource.getName());
        mappedUser.setEmail(createUserResource.getEmail());
        mappedUser.setPassword(createUserResource.getPassword());

        UserResource mappedUserResource = new UserResource();
        mappedUserResource.setId(mappedUser.getId());
        mappedUserResource.setName(mappedUser.getName());
        mappedUserResource.setEmail(mappedUser.getEmail());

        // Mocking behavior
        when(userMapper.toModel(createUserResource)).thenReturn(mappedUser);
        when(userService.create(any(User.class))).thenReturn(mappedUser);
        when(userMapper.toResource(mappedUser)).thenReturn(mappedUserResource);

        // Test
        ResponseEntity<UserResource> responseEntity = userController.createUser(createUserResource);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mappedUserResource, responseEntity.getBody());
    }

    @Test
    public void deleteUserTest() {
        when(userService.delete(1L)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> result = userController.deleteUser(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserByIdTest() {
        // Mock data
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("Test User");
        user.setEmail("test@example.com");
        // Simulate UserService behavior
        when(userService.getById(userId)).thenReturn(user);

        UserResource expectedUserResource = new UserResource();
        expectedUserResource.setId(userId);
        expectedUserResource.setName(user.getName());
        expectedUserResource.setEmail(user.getEmail());
        // Simulate UserMapper behavior
        when(userMapper.toResource(user)).thenReturn(expectedUserResource);

        // Test
        UserResource actualUserResource = userController.getUserById(userId);

        // Assertions
        assertEquals(expectedUserResource, actualUserResource);
    }

}
