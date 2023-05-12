package com.example.rest_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.rest_api.auth.AuthenticationRequest;
import com.example.rest_api.auth.RegisterRequest;
import com.example.rest_api.configuration.AppConfig;
import com.example.rest_api.daos.TokenRepo;
import com.example.rest_api.daos.UserRepo;
import com.example.rest_api.entities.Direction;
import com.example.rest_api.entities.Role;
import com.example.rest_api.entities.Token;
import com.example.rest_api.entities.User;
import com.example.rest_api.exceptions.InvalidUserException;
import com.example.rest_api.utils.JwtUtil;
import com.example.rest_api.utils.UserUtil;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class, AppConfig.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private DirectionService directionService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private MailService mailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleService roleService;

    @MockBean
    private TokenRepo tokenRepo;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private UserService userService;

    @MockBean
    private UserUtil userUtil;

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister() throws InvalidUserException {
        when(directionService.getDirectionByName((String) any())).thenReturn(Optional.of(new Direction()));
        when(roleService.findRolebyname((String) any())).thenReturn(Optional.of(new Role()));
        when(userService.saveUser((User) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        ResponseEntity<Void> actualRegisterResult = authenticationService.register(new RegisterRequest());
        assertNull(actualRegisterResult.getBody());
        assertEquals(201, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        verify(directionService).getDirectionByName((String) any());
        verify(roleService).findRolebyname((String) any());
        verify(userService).saveUser((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister2() throws InvalidUserException {
        when(directionService.getDirectionByName((String) any())).thenReturn(Optional.of(new Direction()));
        when(roleService.findRolebyname((String) any())).thenReturn(Optional.of(new Role()));
        when(userService.saveUser((User) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> authenticationService.register(new RegisterRequest()));
        verify(directionService).getDirectionByName((String) any());
        verify(roleService).findRolebyname((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister3() throws InvalidUserException {
        when(directionService.getDirectionByName((String) any())).thenReturn(Optional.empty());
        when(roleService.findRolebyname((String) any())).thenReturn(Optional.of(new Role()));
        when(userService.saveUser((User) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        ResponseEntity<Void> actualRegisterResult = authenticationService.register(new RegisterRequest());
        assertNull(actualRegisterResult.getBody());
        assertEquals(201, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        verify(directionService).getDirectionByName((String) any());
        verify(roleService).findRolebyname((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister4() throws InvalidUserException {
        when(directionService.getDirectionByName((String) any())).thenReturn(Optional.of(new Direction()));
        when(roleService.findRolebyname((String) any())).thenReturn(Optional.empty());
        when(userService.saveUser((User) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        ResponseEntity<Void> actualRegisterResult = authenticationService.register(new RegisterRequest());
        assertNull(actualRegisterResult.getBody());
        assertEquals(201, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        verify(directionService).getDirectionByName((String) any());
        verify(roleService).findRolebyname((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister5() throws InvalidUserException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.rest_api.auth.RegisterRequest.getRoleName()" because "request" is null
        //       at com.example.rest_api.services.AuthenticationService.register(AuthenticationService.java:47)
        //   In order to prevent register(RegisterRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   register(RegisterRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        when(directionService.getDirectionByName((String) any())).thenReturn(Optional.of(new Direction()));
        when(roleService.findRolebyname((String) any())).thenReturn(Optional.of(new Role()));
        when(userService.saveUser((User) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        authenticationService.register(null);
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate() throws AuthenticationException {
        when(userRepo.findUserByUsername((String) any())).thenReturn(Optional.of(new User()));
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(tokenRepo.save((Token) any())).thenReturn(new Token());
        when(tokenRepo.findAllValidTokenByUser((Long) any())).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertEquals("ABC123",
                authenticationService.authenticate(new AuthenticationRequest("janedoe", "User Pass")).getToken());
        verify(userRepo).findUserByUsername((String) any());
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(tokenRepo).save((Token) any());
        verify(tokenRepo).findAllValidTokenByUser((Long) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {
        when(userRepo.findUserByUsername((String) any())).thenReturn(Optional.of(new User()));
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(tokenRepo.save((Token) any())).thenReturn(new Token());
        when(tokenRepo.findAllValidTokenByUser((Long) any())).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate((Authentication) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate(new AuthenticationRequest("janedoe", "User Pass")));
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate3() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenThrow(new IllegalArgumentException("foo"));
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findUserByUsername((String) any())).thenReturn(ofResult);
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(tokenRepo.save((Token) any())).thenReturn(new Token());
        when(tokenRepo.findAllValidTokenByUser((Long) any())).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(IllegalArgumentException.class,
                () -> authenticationService.authenticate(new AuthenticationRequest("janedoe", "User Pass")));
        verify(userRepo).findUserByUsername((String) any());
        verify(user).getId();
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate4() throws AuthenticationException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.rest_api.daos.UserRepo.findUserByUsername(String)" is null
        //       at com.example.rest_api.services.AuthenticationService.authenticate(AuthenticationService.java:104)
        //   In order to prevent authenticate(AuthenticationRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   authenticate(AuthenticationRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepo.findUserByUsername((String) any())).thenReturn(null);
        new IllegalArgumentException("foo");
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(tokenRepo.save((Token) any())).thenReturn(new Token());
        when(tokenRepo.findAllValidTokenByUser((Long) any())).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        authenticationService.authenticate(new AuthenticationRequest("janedoe", "User Pass"));
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate5() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenThrow(new IllegalArgumentException("foo"));
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findUserByUsername((String) any())).thenReturn(ofResult);
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(tokenRepo.save((Token) any())).thenReturn(new Token());
        when(tokenRepo.findAllValidTokenByUser((Long) any())).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(IllegalArgumentException.class, () -> authenticationService.authenticate(null));
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate6() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getId()).thenThrow(new IllegalArgumentException("foo"));
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findUserByUsername((String) any())).thenReturn(ofResult);
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(tokenRepo.save((Token) any())).thenReturn(new Token());
        when(tokenRepo.findAllValidTokenByUser((Long) any())).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationRequest authenticationRequest = mock(AuthenticationRequest.class);
        when(authenticationRequest.getUserName()).thenThrow(new IllegalArgumentException("foo"));
        when(authenticationRequest.getUserPass()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> authenticationService.authenticate(authenticationRequest));
        verify(authenticationRequest).getUserName();
    }

    /**
     * Method under test: {@link AuthenticationService#UpdatePassword(String, String)}
     */
    @Test
    void testUpdatePassword() {
        when(userRepo.save((User) any())).thenReturn(new User());
        when(userRepo.findUserByUsername((String) any())).thenReturn(Optional.of(new User()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        ResponseEntity<String> actualUpdatePasswordResult = authenticationService.UpdatePassword("iloveyou", "janedoe");
        assertEquals("Password Updated", actualUpdatePasswordResult.getBody());
        assertEquals(200, actualUpdatePasswordResult.getStatusCodeValue());
        assertTrue(actualUpdatePasswordResult.getHeaders().isEmpty());
        verify(userRepo).save((User) any());
        verify(userRepo).findUserByUsername((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#UpdatePassword(String, String)}
     */
    @Test
    void testUpdatePassword2() {
        when(userRepo.save((User) any())).thenReturn(new User());
        when(userRepo.findUserByUsername((String) any())).thenReturn(Optional.of(new User()));
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new IllegalArgumentException("Password Updated"));
        assertThrows(IllegalArgumentException.class, () -> authenticationService.UpdatePassword("iloveyou", "janedoe"));
        verify(userRepo).findUserByUsername((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#UpdatePassword(String, String)}
     */
    @Test
    void testUpdatePassword3() {
        User user = mock(User.class);
        doThrow(new IllegalArgumentException("foo")).when(user).setUserPass((String) any());
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.save((User) any())).thenReturn(new User());
        when(userRepo.findUserByUsername((String) any())).thenReturn(ofResult);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertThrows(IllegalArgumentException.class, () -> authenticationService.UpdatePassword("iloveyou", "janedoe"));
        verify(userRepo).findUserByUsername((String) any());
        verify(user).setUserPass((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AuthenticationService#UpdatePassword(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdatePassword4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.example.rest_api.services.AuthenticationService.UpdatePassword(AuthenticationService.java:138)
        //   In order to prevent UpdatePassword(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   UpdatePassword(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepo.save((User) any())).thenReturn(new User());
        when(userRepo.findUserByUsername((String) any())).thenReturn(Optional.empty());
        new IllegalArgumentException("foo");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        authenticationService.UpdatePassword("iloveyou", "janedoe");
    }
}

