package tech.joaovic.exemple_spring_mvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID idUser;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 a 100 caracteres")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @Valid
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_cep")
    @JsonProperty("address")
    private AddressModel addressModel;
}
