package cu.hash.storeexercise.repository;


import cu.hash.storeexercise.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    Optional<Producto>getProductoByNombre(String nombre);

}
