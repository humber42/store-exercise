package cu.hash.storeexercise.service;

import cu.hash.storeexercise.controllers.detalleVenta.DetalleVentaWithoutList;
import cu.hash.storeexercise.models.DetalleVenta;
import reactor.core.publisher.Flux;



public interface DetalleVentaService {

     Flux<DetalleVentaWithoutList> getAll(long idCliente, long idVenta);
     DetalleVenta registerWithoutVenta(long idProducto,long idVenta);

}
