package ru.mirea.pract_23.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_23.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
