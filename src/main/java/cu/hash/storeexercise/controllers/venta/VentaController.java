package cu.hash.storeexercise.controllers.venta;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_VENTA)
public class VentaController {

    private final VentaService service;
    private final Mapper mapper;

    @Operation(summary = "Register a venta", description = "Return the register venta", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(
                    schema = @Schema(implementation = VentaResponse.class)
            ))
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<VentaResponse> registerVenta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (description = "A Venta to register", required = true, content =
                    @Content(schema = @Schema(implementation = VentaRequest.class)))
            @Valid @RequestBody VentaRequest request){
        VentaResponse response = mapper.map(
                service.registerVenta(mapper.map(request, Venta.class))
                ,VentaResponse.class);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all ventas on the system", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(
                    schema = @Schema(implementation = VentaResponse.class)
            ))
    })
    @GetMapping
    public ResponseEntity<List<VentaResponse>> getAllVentas(){
        List<VentaResponse> ventaResponses = service.getAll()
                .stream()
                .map(object->mapper.map(object,VentaResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ventaResponses);
    }

    @Operation(summary = "Get a venta by id", description = "Return a venta",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = VentaResponse.class))),
            @ApiResponse(responseCode ="404",description = "Not found venta"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> getVentaById(@Parameter(description = "Id of the venta", required = true)@PathVariable("id")long id){
        VentaResponse response = mapper.map(service.getByIdVenta(id),VentaResponse.class);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "List a ventas by date", description = "Return a list of venta",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = VentaResponse.class))),
            @ApiResponse(responseCode ="404",description = "Not found venta"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @GetMapping(value = "/date",params = "date")
    public ResponseEntity<List<VentaResponse>> getAllVentasByDate(@Parameter(description = "Date of the ventas", required = true)@RequestParam("date")Date fecha){
        List<VentaResponse> ventaResponses= service.getAllVentaByDate(fecha)
                .stream()
                .map(object->mapper.map(object,VentaResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ventaResponses);
    }

    @Operation(summary = "List a ventas by id cliente", description = "Return a list of venta",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = VentaResponse.class))),
            @ApiResponse(responseCode ="404",description = "Not found venta"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @GetMapping(value = "/cliente/{id}")
    public ResponseEntity<List<VentaResponse>> getAllVentasByClientId(@Parameter(description = "Id of the cliente", required = true)@PathVariable("id") long id){
        List<VentaResponse> ventaResponses= service.getAllVentaByClientId(id)
                .stream()
                .map(object->mapper.map(object,VentaResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ventaResponses);
    }


    @Autowired
    public VentaController(VentaService service,Mapper mapper){
        this.mapper=mapper;
        this.service=service;
    }

}
