package cu.hash.storeexercise.service;

import cu.hash.storeexercise.StoreExerciseApplication;
import cu.hash.storeexercise.controllers.detalleVenta.DetalleVentaWithoutList;
import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.models.DetalleVenta;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.repository.DetalleVentaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StoreExerciseApplication.class)
@RunWith(SpringRunner.class)
public class DetalleVentaServiceTest {

    @Autowired
    DetalleVentaService detalleVentaService;

    @MockBean
    DetalleVentaRepository detalleVentaRepository;

    @Before
    public void setup(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        Venta venta1 = new Venta();
        venta1.setId(1);
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(cliente);

        Venta venta2 = new Venta();
        venta2.setId(2);
        venta2.setFecha(Date.valueOf("1998-01-27"));
        venta2.setCliente(cliente);

        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Onions");
        producto1.setPrecio((float) 2.36);

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Avocado");
        producto2.setPrecio((float) 4.85);


        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setVenta(venta1);
        detalleVenta.setId(1);
        detalleVenta.setProducto(producto2);

        DetalleVenta detalleVenta2 = new DetalleVenta();
        detalleVenta2.setVenta(venta2);
        detalleVenta2.setId(2);
        detalleVenta2.setProducto(producto2);

        DetalleVenta detalleVenta3 = new DetalleVenta();
        detalleVenta3.setVenta(venta1);
        detalleVenta3.setId(3);
        detalleVenta3.setProducto(producto1);

        DetalleVenta detalleVenta4 = new DetalleVenta();
        detalleVenta4.setVenta(venta2);
        detalleVenta4.setId(4);
        detalleVenta4.setProducto(producto1);

        Mockito.when(detalleVentaRepository.save(detalleVenta)).thenReturn(detalleVenta);
        Mockito.when(detalleVentaRepository.save(detalleVenta2)).thenReturn(detalleVenta2);
        Mockito.when(detalleVentaRepository.save(detalleVenta3)).thenReturn(detalleVenta3);
        Mockito.when(detalleVentaRepository.save(detalleVenta4)).thenReturn(detalleVenta4);

        List<DetalleVenta> detalleVentaList = Arrays.asList(detalleVenta,detalleVenta3);

        Mockito.when(detalleVentaRepository.findAll()).thenReturn(detalleVentaList);


    }

    @Test
    public void whenRegisterDetalleVenta_thenReturnDetalleVenta(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        Venta venta1 = new Venta();
        venta1.setId(1);
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(cliente);


        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Avocado");
        producto2.setPrecio((float) 4.85);

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setVenta(venta1);
        detalleVenta.setId(1);
        detalleVenta.setProducto(producto2);

        DetalleVenta test = detalleVentaService.registerWithoutVenta(2,1);

        assertThat(test.getVenta().getId()).isEqualTo(detalleVenta.getVenta().getId());
    }




}
