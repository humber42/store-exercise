package cu.hash.storeexercise.models;

import cu.hash.storeexercise.constants.KeyConstants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "venta")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = KeyConstants.NOT_NULL)
    @Column(name = "fecha")
    private Date fecha;

    @JoinColumn(name = "id_cliente")
    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Cliente cliente;
}
