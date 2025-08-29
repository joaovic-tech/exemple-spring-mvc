package tech.joaovic.exemple_spring_mvc.services;

import tech.joaovic.exemple_spring_mvc.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserModel createUser(UserModel userModel);
    List<UserModel> findAll();
    UserModel updateUser(UUID id, UserModel userModel);
    UserModel deleteUser(UUID id);
}
