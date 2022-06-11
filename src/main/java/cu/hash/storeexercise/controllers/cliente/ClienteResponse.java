package cu.hash.storeexercise.controllers.cliente;

import cu.hash.storeexercise.controllers.venta.VentaResponseWithoutList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteResponse {
    private long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private List<VentaResponseWithoutList> ventas;
}
