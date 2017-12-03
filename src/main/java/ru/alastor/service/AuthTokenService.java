package ru.alastor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alastor.dao.InviteTokenDao;
import ru.alastor.dao.TokenDao;
import ru.alastor.dao.UserDao;
import ru.alastor.domain.Token;
import ru.alastor.domain.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created on 23.10.17.
 *
 * @author Maxim Goncharov
 */
@Service
public class AuthTokenService {

    private final TokenDao tokenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public AuthTokenService(TokenDao tokenServiceDao, InviteTokenDao inviteTokenDao) {
        this.tokenDao = tokenServiceDao;
    }

    public Token getAuthToken(User user) {
        Token tokenOwn = tokenDao.getTokenByOwnerId(user.getId());

        if (tokenOwn != null) {
            tokenDao.delete(tokenOwn.getId());
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 7 * 86400000L);
        String tokenString = UUID.randomUUID().toString();

        Token token = new Token();
        token.setOwner(user);
        token.setTimestamp(timestamp);
        token.setToken(tokenString);

        tokenDao.save(token);
        return token;
    }


    public Token getExistAuthToken(String userLogin, String token) {
        Token trueToken =  tokenDao.getTokenByOwnerId(userDao.findByLogin(userLogin).getId());
        if (new Date().getTime() > trueToken.getTimestamp().getTime() || !trueToken.getToken().equals(token)) {
            return null;
        }
        return trueToken;
    }
}
