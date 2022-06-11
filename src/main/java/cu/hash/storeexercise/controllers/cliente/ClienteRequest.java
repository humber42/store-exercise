package cu.hash.storeexercise.controllers.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
}
