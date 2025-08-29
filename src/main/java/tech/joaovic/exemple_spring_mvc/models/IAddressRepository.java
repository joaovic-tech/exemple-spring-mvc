package tech.joaovic.exemple_spring_mvc.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<AddressModel, String> {
}
