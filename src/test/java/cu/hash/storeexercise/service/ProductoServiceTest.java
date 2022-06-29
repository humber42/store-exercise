package cu.hash.storeexercise.service;

import cu.hash.storeexercise.StoreExerciseApplication;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.repository.ProductoRepository;
import cu.hash.storeexercise.repository.ProductoRepositoryTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StoreExerciseApplication.class)
@RunWith(SpringRunner.class)
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Before
    public void setUp(){
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Onions");
        producto1.setPrecio((float) 2.36);

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Avocado");
        producto2.setPrecio((float) 4.85);

        Producto producto3 = new Producto();
        producto3.setId(3);
        producto3.setNombre("Apples");
        producto3.setPrecio((float) 0.65);

        Mockito.when(productoRepository.save(producto1)).thenReturn(producto1);
        Mockito.when(productoRepository.save(producto2)).thenReturn(producto2);
        Mockito.when(productoRepository.save(producto3)).thenReturn(producto3);

        List<Producto> allProduct = Arrays.asList(producto1,producto2,producto3);

        Mockito.when(productoRepository.getProductoByNombre("Apples")).thenReturn(Optional.of(producto3));
        Mockito.when(productoRepository.findAll()).thenReturn(allProduct);
        Mockito.when(productoRepository.findById((long)2)).thenReturn(Optional.of(producto2));
    }

    @Test
    public void whenSaveProduct_thenRetrieveTheProduct(){
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Onions");
        producto1.setPrecio((float) 2.36);

        Producto test = productoService.registerProduct(producto1);

        assertThat(test).isEqualTo(producto1);
    }

    @Test
    public void whenFetchAll_thenReturnAllProducts(){
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Onions");
        producto1.setPrecio((float) 2.36);

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Avocado");
        producto2.setPrecio((float) 4.85);

        Producto producto3 = new Producto();
        producto3.setId(3);
        producto3.setNombre("Apples");
        producto3.setPrecio((float) 0.65);

        List<Producto> allProductTesting = productoService.getAllProduct();

        assertThat(allProductTesting).hasSize(3)
                .extracting(Producto::getNombre)
                .contains(producto1.getNombre(),producto2.getNombre(), producto3.getNombre());
    }

    @Test
    public void whenGetById_thenReturnProduct(){
        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Avocado");
        producto2.setPrecio((float) 4.85);

        Producto test = productoService.getProductById(2);

        assertThat(test).isEqualTo(producto2);
    }

    @Test
    public void whenSendName_thenReturnProduct(){
        Producto producto3 = new Producto();
        producto3.setId(3);
        producto3.setNombre("Apples");
        producto3.setPrecio((float) 0.65);

        Producto test = productoService.getProductByName("Apples");
        assertThat(test).isEqualTo(producto3);
    }

}
