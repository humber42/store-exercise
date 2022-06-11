package cu.hash.storeexercise.controllers.venta;

import cu.hash.storeexercise.controllers.cliente.ClienteResponseWithoutList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VentaRequest {
    private Date fecha;
    private ClienteResponseWithoutList cliente;
}
