package cu.hash.storeexercise.models;

import cu.hash.storeexercise.constants.KeyConstants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message=KeyConstants.NOT_NULL)
    @Column(name = "nombre")
    private String nombre;

    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name = "precio")
    private float precio;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "producto")
    private List<DetalleVenta> detalleVentas;

}
