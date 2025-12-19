package com.aniket.taskman.repository;

import com.aniket.taskman.entity.User;
import com.aniket.taskman.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.lang.ScopedValue;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<Object> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}