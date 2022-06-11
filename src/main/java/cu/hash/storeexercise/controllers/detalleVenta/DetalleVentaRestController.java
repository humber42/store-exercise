package cu.hash.storeexercise.controllers.detalleVenta;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.models.DetalleVenta;
import cu.hash.storeexercise.repository.DetalleVentaRepository;
import cu.hash.storeexercise.service.DetalleVentaService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_DETALLES_VENTA)
public class DetalleVentaRestController {

    private final DetalleVentaService service;
    private final Mapper mapper;


    @GetMapping(params = {"idCliente","idVenta"})
    public Flux<DetalleVentaWithoutList> getAllDetalleVenta(@RequestParam("idCliente")long idCliente, @RequestParam("idVenta")long idVenta){
        return service.getAll(idCliente,idVenta);
    }

    @Autowired
    public DetalleVentaRestController(DetalleVentaService service,Mapper mapper){
        this.service=service;
        this.mapper=mapper;
    }

}
