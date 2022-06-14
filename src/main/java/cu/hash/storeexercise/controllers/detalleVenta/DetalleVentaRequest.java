package cu.hash.storeexercise.controllers.detalleVenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaRequest {

    private long idVenta;
    private long idProducto;

}
