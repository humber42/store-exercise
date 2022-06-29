package cu.hash.storeexercise.repository;

import cu.hash.storeexercise.models.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestEntityManager
public class ClienteRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void whenFindByDni_thenReturnCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        testEntityManager.persistAndFlush(cliente);

        Cliente clienteFound = clienteRepository.getClienteByDni("78023102589").orElse(null);

        assertThat(clienteFound).isNotNull();
        assertThat(clienteFound.getDni()).isEqualTo(cliente.getDni());

    }

    @Test
    public void whenRegisterCliente_thenReturnClient(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        Cliente clienteRegister = clienteRepository.save(cliente);
        assertThat(clienteRegister.getApellido()).isEqualTo(cliente.getApellido());
    }

}
