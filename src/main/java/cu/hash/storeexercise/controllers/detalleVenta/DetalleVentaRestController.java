package cu.hash.storeexercise.controllers.detalleVenta;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.service.DetalleVentaService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Flux<DetalleVentaWithoutList> getAllDetalleVenta(@Parameter(description = "Id of the cliente")@RequestParam("idCliente")long idCliente,@Parameter(description = "Id of the venta") @RequestParam("idVenta")long idVenta){
        return service.getAll(idCliente,idVenta);
    }

    @Operation(summary = "Register detalle venta", description = "Register a detalle venta on the system and return it",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = DetalleVentaWithoutList.class))),
            @ApiResponse(responseCode = "400",description = "El detalle venta ya existe o contenido incorrecto")
    })
    @PostMapping(produces = "application/json")
    public DetalleVentaWithoutList register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (description = "A Detalle Venta to register", required = true, content =
                    @Content(schema = @Schema(implementation = DetalleVentaRequest.class)))@RequestBody DetalleVentaRequest request){
        return mapper.map(service.registerWithoutVenta(request.getIdProducto(), request.getIdVenta()),DetalleVentaWithoutList.class);
    }

    @Autowired
    public DetalleVentaRestController(DetalleVentaService service,Mapper mapper){
        this.service=service;
        this.mapper=mapper;
    }

}
