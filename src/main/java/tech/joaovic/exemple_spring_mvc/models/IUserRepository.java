package tech.joaovic.exemple_spring_mvc.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByEmail(String email);
}
