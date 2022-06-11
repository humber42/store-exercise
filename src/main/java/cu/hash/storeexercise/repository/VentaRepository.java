package cu.hash.storeexercise.repository;


import cu.hash.storeexercise.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {

    List<Venta> getAllByFecha(Date date);
    List<Venta> getAllByCliente_Id(long id);

}
