package cu.hash.storeexercise.repository;

import cu.hash.storeexercise.models.Producto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestEntityManager
public class ProductoRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductoRepository productoRepository;


    @Test
    public void whenFindByName_thenReturnProduct() {
        Producto producto = new Producto();
        producto.setPrecio((float) 2.36);
        producto.setNombre("Onions");

        testEntityManager.persistAndFlush(producto);

        Producto productoFound = productoRepository.getProductoByNombre("Onions").orElse(null);
        assertThat(productoFound).isNotNull();
        assertThat(productoFound.getNombre()).isEqualTo(producto.getNombre());
    }

    @Test
    public void whenInvalidName_thenReturnNull(){
        Producto productoFromDb=productoRepository.getProductoByNombre("notAValidName").orElse(null);
        assertThat(productoFromDb).isNull();
    }

}
