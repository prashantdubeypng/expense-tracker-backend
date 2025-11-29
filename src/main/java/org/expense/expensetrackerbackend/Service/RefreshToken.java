package org.expense.expensetrackerbackend.Service;

import org.expense.expensetrackerbackend.entity.RefereshToken;
import org.expense.expensetrackerbackend.entity.User;
import org.expense.expensetrackerbackend.repoistry.RefreshTokenrepo;
import org.expense.expensetrackerbackend.repoistry.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * @author prashant
 * A refresh token is a long-lived credential that the client uses to get new short-lived access
 * tokens (like JWTs) without forcing the user to log in
 * again, and it can be revoked or rotated independently for better security.*/
@Service
public class RefreshToken {
@Autowired RefreshTokenrepo RefreshTokenrepo;
@Autowired Userrepo userrepo;
public RefreshToken Createrefreshtoken(String username){
    User userinfo = userrepo.findByUsername(username);
    RefereshToken refresh = RefereshToken.builder().userInfo(userInfoExracted)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().piusMillis(600000))
            .build();
    return RefreshTokenrepo.save(refresh);


}



}
