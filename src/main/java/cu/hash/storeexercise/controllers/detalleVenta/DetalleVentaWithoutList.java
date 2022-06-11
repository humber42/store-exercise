package cu.hash.storeexercise.controllers.detalleVenta;

import cu.hash.storeexercise.controllers.producto.ProductoResponseWithoutList;
import cu.hash.storeexercise.controllers.venta.VentaResponseWithoutList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleVentaWithoutList {

    private long id;
    private VentaResponseWithoutList venta;
    private ProductoResponseWithoutList producto;
}
