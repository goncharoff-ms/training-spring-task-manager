package ru.alastor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.alastor.dao.InviteTokenDao;
import ru.alastor.dao.RoleUserDao;
import ru.alastor.dao.TokenDao;
import ru.alastor.dao.UserDao;
import ru.alastor.domain.*;
import ru.alastor.domain.builder.UserBuilder;
import ru.alastor.service.AuthTokenService;

/**
 * Created on 24.09.17.
 *
 * @author Maxim Goncharov
 */
@RestController
@CrossOrigin
public class UserController {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDao userServiceDao;
    private final InviteTokenDao inviteTokenDao;
    private final TokenDao tokenDao;

    private final AuthTokenService  authTokenService;

    @Autowired
    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder, RoleUserDao roleUserServiceDao, UserDao userServiceDao, TokenDao tokenServiceDao, AuthTokenService authTokenService, InviteTokenDao inviteTokenDao, TokenDao tokenDao) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userServiceDao = userServiceDao;
        this.authTokenService = authTokenService;
        this.inviteTokenDao = inviteTokenDao;
        this.tokenDao = tokenDao;
    }


    @RequestMapping(path = "/users/{userLogin}", method = RequestMethod.PUT)
    public ResponseEntity getUser(@RequestParam("token") String tokenString,
                                 @RequestParam("userLogin") String userLogin,
                                  @PathVariable(name = "userLogin") String requestLogin,
                                  @RequestParam("password") String password,
                                  @RequestParam("email") String email,
                                  @RequestParam("name") String name,
                                  @RequestParam("about") String about,
                                  @RequestParam("surname") String surname) {

        Token token = authTokenService.getExistAuthToken(userLogin, tokenString);
        if (token == null) {
            return new ResponseEntity<>("Error login or token", HttpStatus.ACCEPTED);
        }
        User user = token.getOwner();
        if (!user.getLogin().equals(requestLogin)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        user.setSurname(surname);
        user.setName(name);
        user.setEmail(email);
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
        }
        user.setAboutUser(about);

        userServiceDao.save(user);

        return new ResponseEntity<>(new UserResponse(
            user.getLogin(),
            user.getEmail(),
            user.getName(),
            user.getSurname(),
            user.getAboutUser()
        ), HttpStatus.OK);
    }

    @RequestMapping(path = "/users/{userLogin}", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam("token") String tokenString,
                                  @RequestParam("userLogin") String userLogin,
                                  @PathVariable(name = "userLogin") String requestLogin) {
        Token token = authTokenService.getExistAuthToken(userLogin, tokenString);
        if (token == null) {
            return new ResponseEntity<>("Error login or token", HttpStatus.ACCEPTED);
        }

        User user = userServiceDao.findByLogin(requestLogin);

        if (!token.getOwner().equals(user)) {
            return new ResponseEntity<>("Access denied", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new UserResponse(
                user.getLogin(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getAboutUser()
        ), HttpStatus.OK);
    }



    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestParam("login") String login,
                       @RequestParam("password") String password,
                       @RequestParam("email") String email,
                       @RequestParam("name") String name,
                       @RequestParam("about") String about,
                       @RequestParam("surname") String surname,
                       @RequestParam("token_invite") String tokenInvite) {

        InviteToken token = inviteTokenDao.findByToken(tokenInvite);

        if (token == null) {
            return new ResponseEntity<>("not corrected token_invite", HttpStatus.ACCEPTED);
        }

        User newUser = UserBuilder.getInstance()
                .createUser()
                .setLogin(login)
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setAboutUser(about)
                .setPassword(bCryptPasswordEncoder.encode(password))
                .setRole(token.getRoleUser())
                .getUser();

        userServiceDao.save(newUser);

        // TODO: 30.11.17 enable in future 
        //inviteTokenDao.delete(token);

        return new ResponseEntity<>(new ResponseToken(authTokenService.getAuthToken(newUser)),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/sign-in",
            method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password) {

        User logged = userServiceDao.findByLogin(login);
        if (logged == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!bCryptPasswordEncoder.matches(password, logged.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(
                new ResponseToken(authTokenService.getAuthToken(logged)),
                HttpStatus.OK);
    }



}
