package com.trustbycode.shortner.Service;

import com.trustbycode.shortner.Entity.User;
import com.trustbycode.shortner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String shortenLink(String longLink) {

        String generateHash = DigestUtils.md5DigestAsHex(longLink.getBytes());
        String shortLink = generateHash.substring(0, 5);

        User userAccess = new User();
        userAccess.setLongLink(longLink);
        userAccess.setShortLink(shortLink);

        userRepository.save(userAccess);
        return shortLink;
    }

    public String requestLongLink(String shortLink) {
        Optional<User> user = userRepository
                .findByShortLink(shortLink);
        return user.map(User::getLongLink)
                .orElseThrow();
    }

    public boolean deleteLink(String shortLink) {
        Optional<User> userMap = userRepository.findByShortLink(shortLink);

        if (userMap.isPresent()) {
            userRepository.delete(userMap.get());
            return true;
        } else {
            return false;
        }
    }
}
