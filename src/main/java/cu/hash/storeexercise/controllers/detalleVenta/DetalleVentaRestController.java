package cu.hash.storeexercise.controllers.detalleVenta;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.controllers.producto.ProductoResponse;
import cu.hash.storeexercise.models.DetalleVenta;
import cu.hash.storeexercise.repository.DetalleVentaRepository;
import cu.hash.storeexercise.service.DetalleVentaService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Api(tags = "Detalle Venta services", value = "This is the detalle venta services")
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_DETALLES_VENTA)
public class DetalleVentaRestController {

    private final DetalleVentaService service;
    private final Mapper mapper;


    @Operation(summary = "List of detalle venta", description = "Return a list of detalle venta by id cliente or id venta",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = DetalleVentaWithoutList.class))),
            @ApiResponse(responseCode ="404",description = "Not found detalle venta"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @GetMapping(params = {"idCliente","idVenta"},produces = "application/json")
    public Flux<DetalleVentaWithoutList> getAllDetalleVenta(@Parameter(description = "Id of the cliente", required = false)@RequestParam("idCliente")long idCliente,@Parameter(description = "Id of the venta", required = false) @RequestParam("idVenta")long idVenta){
        return service.getAll(idCliente,idVenta);
    }

    @Autowired
    public DetalleVentaRestController(DetalleVentaService service,Mapper mapper){
        this.service=service;
        this.mapper=mapper;
    }

}
