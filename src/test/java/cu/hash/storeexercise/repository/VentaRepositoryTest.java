package cu.hash.storeexercise.repository;

import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.models.Venta;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class VentaRepositoryTest {

    private Cliente cliente;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void beforeEach(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        this.cliente = clienteRepository.save(cliente);

        Venta venta1 = new Venta();
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(this.cliente);

        Venta venta2 = new Venta();
        venta2.setFecha(Date.valueOf("1998-01-27"));
        venta2.setCliente(this.cliente);

        Venta venta3 = new Venta();
        venta3.setFecha(Date.valueOf("2002-01-25"));
        venta3.setCliente(this.cliente);

        Venta venta4 = new Venta();
        venta4.setFecha(Date.valueOf("2006-03-25"));
        venta4.setCliente(this.cliente);

        entityManager.persist(venta1);
        entityManager.persist(venta2);
        entityManager.persist(venta3);
        entityManager.persist(venta4);
    }

    @After
    public void afterEach(){
        clienteRepository.deleteAll();
    }


    @Test
    public void whenFindByFecha_thenReturnListVenta(){
        List<Venta> ventas = ventaRepository.getAllByFecha(Date.valueOf("2006-03-25"));
        assertThat(ventas).hasSize(2).extracting(Venta::getFecha).contains(Date.valueOf("2006-03-25"));
    }

    @Test
    public void whenFindByIdClient_thenReturnListVenta(){
        List<Venta> ventaLista = ventaRepository.getAllByCliente_Id(this.cliente.getId());
        assertThat(ventaLista).hasSize(4).extracting(Venta::getCliente).contains(this.cliente);
    }
}
