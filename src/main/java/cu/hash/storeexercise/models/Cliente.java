package cu.hash.storeexercise.models;

import cu.hash.storeexercise.constants.KeyConstants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name = "nombre",length = 100)
    private String nombre;

    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name="apellido",length = 255)
    private String apellido;


    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name = "dni",length = 11)
    private String dni;

    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name = "telefono",length=32)
    private String telefono;

    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cliente")
    private List<Venta> ventas;

}
