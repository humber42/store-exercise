package cu.hash.storeexercise.controllers.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoResponseWithoutList {
    private long id;
    private String nombre;
    private float precio;
}
