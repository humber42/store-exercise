package cu.hash.storeexercise.service.impl;

import cu.hash.storeexercise.controllers.detalleVenta.DetalleVentaWithoutList;
import cu.hash.storeexercise.exceptions.ItemAlreadyExistException;
import cu.hash.storeexercise.models.DetalleVenta;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.repository.DetalleVentaRepository;
import cu.hash.storeexercise.service.DetalleVentaService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;



@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository repository;
    private final Mapper mapper;

    @Override
    public Flux<DetalleVentaWithoutList> getAll(long idCliente, long idVenta) {
        return Flux.fromStream(repository.findAll()
                .stream().
                filter(p->p.getVenta().getCliente().getId()==idCliente||p.getVenta().getId()==idVenta))
                .map(p-> mapper.map(p,DetalleVentaWithoutList.class))
                ;
    }

    @Override
    public DetalleVenta registerWithoutVenta(long idProducto, long idVenta) {

        if(this.repository.existsByVenta_IdAndProducto_Id(idVenta,idProducto))
            throw new ItemAlreadyExistException("El detalle de venta ya existe");

        Producto producto = new Producto();
        producto.setId(idProducto);
        Venta venta = new Venta();
        venta.setId(idVenta);

        DetalleVenta newDetalleVenta = new DetalleVenta();
        newDetalleVenta.setVenta(venta);
        newDetalleVenta.setProducto(producto);

        return newDetalleVenta;

    }




    public DetalleVentaServiceImpl (DetalleVentaRepository repository, Mapper mapper){
        this.repository=repository;
        this.mapper=mapper;
    }
}
