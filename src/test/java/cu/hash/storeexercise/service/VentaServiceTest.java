package cu.hash.storeexercise.service;


import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.repository.ClienteRepository;
import cu.hash.storeexercise.repository.VentaRepository;
import cu.hash.storeexercise.service.impl.ClienteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class VentaServiceTest {

    @Autowired
    private VentaService ventaService;

    @MockBean
    private VentaRepository ventaRepository;



    @Before
    public void setUp(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");


        Venta venta1 = new Venta();
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(cliente);

        Venta venta2 = new Venta();
        venta2.setId(2);
        venta2.setFecha(Date.valueOf("1998-01-27"));
        venta2.setCliente(cliente);

        Venta venta3 = new Venta();
        venta3.setFecha(Date.valueOf("2002-01-25"));
        venta3.setCliente(cliente);

        Venta venta4 = new Venta();
        venta4.setFecha(Date.valueOf("2006-03-25"));
        venta4.setCliente(cliente);

        Mockito.when(ventaRepository.saveAndFlush(venta1)).thenReturn(venta1);
        Mockito.when(ventaRepository.saveAndFlush(venta2)).thenReturn(venta2);
        Mockito.when(ventaRepository.saveAndFlush(venta3)).thenReturn(venta3);
        Mockito.when(ventaRepository.saveAndFlush(venta4)).thenReturn(venta4);

        List<Venta> allVentas = Arrays.asList(venta1,venta2,venta3,venta4);
        List<Venta> ventasSameDay = Arrays.asList(venta1,venta4);

        Mockito.when(ventaRepository.findById(Long.valueOf(2))).thenReturn(Optional.of(venta2));
        Mockito.when(ventaRepository.getAllByFecha(Date.valueOf("2006-03-25"))).thenReturn(ventasSameDay);
        Mockito.when(ventaRepository.findAll()).thenReturn(allVentas);
        Mockito.when(ventaRepository.findById(venta1.getId())).thenReturn(Optional.of(venta1));
        Mockito.when(ventaRepository.getAllByCliente_Id(cliente.getId())).thenReturn(allVentas);

    }


    @Test
    public void whenSendAClientId_thenReturnAllVentasByThisClient(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        Venta venta1 = new Venta();
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(cliente);

        Venta venta2 = new Venta();
        venta2.setFecha(Date.valueOf("1998-01-27"));
        venta2.setCliente(cliente);

        Venta venta3 = new Venta();
        venta3.setFecha(Date.valueOf("2002-01-25"));
        venta3.setCliente(cliente);

        Venta venta4 = new Venta();
        venta4.setFecha(Date.valueOf("2006-03-25"));
        venta4.setCliente(cliente);

        List<Venta> allVentasByClient = ventaService.getAllVentaByClientId(cliente.getId());

        assertThat(allVentasByClient).hasSize(4)
                .extracting((venta)->venta.getCliente().getNombre())
                .contains(venta1.getCliente().getNombre(),venta2.getCliente().getNombre(),venta3.getCliente().getNombre(),venta4.getCliente().getNombre());
    }

    @Test
    public void whenSendADate_thenReturnAllVentasOnThisDate(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        Venta venta1 = new Venta();
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(cliente);


        Venta venta4 = new Venta();
        venta4.setFecha(Date.valueOf("2006-03-25"));
        venta4.setCliente(cliente);

        List<Venta> allVentasOnDate = ventaService.getAllVentaByDate(Date.valueOf("2006-03-25"));

        assertThat(allVentasOnDate).hasSize(2).extracting(Venta::getFecha).contains(venta1.getFecha(),venta4.getFecha());

    }

    @Test
    public void whenProvideAnId_thenReturnVenta(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");
        Venta venta2 = new Venta();
        venta2.setId(2);
        venta2.setFecha(Date.valueOf("1998-01-27"));
        venta2.setCliente(cliente);

        assertThat(ventaService.getByIdVenta(2)).isEqualTo(venta2);
    }

    @Test
    public void whenGetAllVentas_thenReturnAllVentas(){

        Venta venta1 = new Venta();
        venta1.setFecha(Date.valueOf("2006-03-25"));

        Venta venta2 = new Venta();
        venta2.setId(2);
        venta2.setFecha(Date.valueOf("1998-01-27"));

        Venta venta3 = new Venta();
        venta3.setFecha(Date.valueOf("2002-01-25"));

        Venta venta4 = new Venta();
        venta4.setFecha(Date.valueOf("2006-03-25"));


        List<Venta> allVentas= ventaService.getAll();

        assertThat(allVentas).hasSize(4)
                .extracting(Venta::getFecha)
                .contains(venta1.getFecha(),venta2.getFecha(),venta3.getFecha(),venta4.getFecha());

    }

    @Test
    public void whenSaveAVenta_thenReturnVenta(){
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        Venta venta1 = new Venta();
        venta1.setFecha(Date.valueOf("2006-03-25"));
        venta1.setCliente(cliente);

        Venta ventaTotest = ventaService.registerVenta(venta1);

        assertThat(ventaTotest).isEqualTo(venta1);
    }

}
