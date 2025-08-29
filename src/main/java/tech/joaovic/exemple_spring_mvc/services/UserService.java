package tech.joaovic.exemple_spring_mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.joaovic.exemple_spring_mvc.exceptions.ConflictException;
import tech.joaovic.exemple_spring_mvc.exceptions.NotFoundException;
import tech.joaovic.exemple_spring_mvc.models.AddressModel;
import tech.joaovic.exemple_spring_mvc.models.IAddressRepository;
import tech.joaovic.exemple_spring_mvc.models.IUserRepository;
import tech.joaovic.exemple_spring_mvc.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IAddressRepository iAddressRepository;
    @Autowired
    ViaCepService viaCepService;


    @Override
    public List<UserModel> findAll() {
        return this.iUserRepository.findAll();
    }

    @Override
    @Transactional
    public UserModel createUser(UserModel userModel) {
        UserModel existingUser = this.iUserRepository.findByEmail(userModel.getEmail());

        if (existingUser != null) {
            throw new ConflictException("Email já está em uso");
        }

        return saveUserWithCEP(userModel);
    }

    @Override
    @Transactional
    public UserModel updateUser(UUID id, UserModel userModel) {
        Optional<UserModel> existingUser = this.iUserRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        UserModel userWithSameEmail = this.iUserRepository.findByEmail(userModel.getEmail());
        if (userWithSameEmail != null && !userWithSameEmail.getIdUser().equals(id)) {
            throw new ConflictException("Email já está em uso por outro usuário");
        }

        userModel.setIdUser(id);
        return saveUserWithCEP(userModel);
    }

    @Override
    public UserModel deleteUser(UUID id) {
        UserModel user = this.iUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        this.iUserRepository.deleteById(id);
        return user;
    }

    private UserModel saveUserWithCEP(UserModel userModel) {
        String cep = userModel.getAddressModel().getCep();

        if (cep.length() != 8) {
            throw new IllegalArgumentException("CEP inválido");
        }

        AddressModel addressModel = this.iAddressRepository.findById(cep).orElseGet(() -> {
            AddressModel newAddress = viaCepService.findByCep(cep);

            if (newAddress.getCep() == null) {
                throw new NotFoundException("CEP não encontrado");
            }

            return this.iAddressRepository.save(newAddress);
        });

        userModel.setAddressModel(addressModel);
        return this.iUserRepository.save(userModel);
    }
}
