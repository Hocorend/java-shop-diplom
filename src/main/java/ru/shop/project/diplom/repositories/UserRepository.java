package ru.shop.project.diplom.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.shop.project.diplom.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByLogin(String login);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.deposit = :#{#user.deposit} WHERE u.id = :#{#user.id}")
    public void updateById(User user);
}
