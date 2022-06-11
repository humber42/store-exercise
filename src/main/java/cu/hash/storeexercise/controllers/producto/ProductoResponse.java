package cu.hash.storeexercise.controllers.producto;

import cu.hash.storeexercise.controllers.detalleVenta.DetalleVentaWithoutList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoResponse {

    private long id;
    private String nombre;
    private float precio;

    private List<DetalleVentaWithoutList> detalleVentas;
}
