package cu.hash.storeexercise.repository;

import cu.hash.storeexercise.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

     Optional<Cliente> getClienteByDni(String dni);

}
