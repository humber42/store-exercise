package cu.hash.storeexercise.controllers.venta;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.service.VentaService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_VENTA)
public class VentaController {

    private final VentaService service;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<VentaResponse> registerVenta(@Valid @RequestBody VentaRequest request){
        VentaResponse response = mapper.map(
                service.registerVenta(mapper.map(request, Venta.class))
                ,VentaResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VentaResponse>> getAllVentas(){
        List<VentaResponse> ventaResponses = service.getAll()
                .stream()
                .map(object->mapper.map(object,VentaResponse.class))
                .toList();
        return ResponseEntity.ok(ventaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> getVentaById(@PathVariable("id")long id){
        VentaResponse response = mapper.map(service.getByIdVenta(id),VentaResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/date",params = "date")
    public ResponseEntity<List<VentaResponse>> getAllVentasByDate(@RequestParam("date")Date fecha){
        List<VentaResponse> ventaResponses= service.getAllVentaByDate(fecha)
                .stream()
                .map(object->mapper.map(object,VentaResponse.class))
                .toList();
        return ResponseEntity.ok(ventaResponses);
    }

    @GetMapping(value = "/cliente/{id}")
    public ResponseEntity<List<VentaResponse>> getAllVentasByClientId(@PathVariable("id") long id){
        List<VentaResponse> ventaResponses= service.getAllVentaByClientId(id)
                .stream()
                .map(object->mapper.map(object,VentaResponse.class))
                .toList();
        return ResponseEntity.ok(ventaResponses);
    }


    @Autowired
    public VentaController(VentaService service,Mapper mapper){
        this.mapper=mapper;
        this.service=service;
    }

}
