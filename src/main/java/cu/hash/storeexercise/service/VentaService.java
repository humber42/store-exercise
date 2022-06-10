package cu.hash.storeexercise.service;

import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.models.Venta;

import java.sql.Date;
import java.util.List;

public interface VentaService {
    Venta registerVenta(Venta venta);
    List<Venta> getAll();
    Venta getByIdVenta(long id);
    List<Venta> getAllVentaByDate(Date date);
    List<Venta> getAllVentaByClient(Cliente client);
}
