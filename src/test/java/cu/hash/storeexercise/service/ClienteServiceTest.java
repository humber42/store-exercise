package cu.hash.storeexercise.service;

import cu.hash.storeexercise.models.Cliente;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteServiceTest {

    @Autowired
    ClienteService service;


    @DisplayName("Testing service of clients: Register a Client")
    @Test
    public void testRegisterAClient(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");
        Cliente test1 = service.registerClient(cliente);

        assertThat(test1.getNombre()).isEqualTo(cliente.getNombre());
    }

}
