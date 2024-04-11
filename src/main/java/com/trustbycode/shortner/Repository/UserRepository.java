package com.trustbycode.shortner.Repository;

import com.trustbycode.shortner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByShortLink(String shortLink);
}
