package cu.hash.storeexercise.controllers.venta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VentaResponseWithoutList {
    private long id;
    private Date fecha;
}
