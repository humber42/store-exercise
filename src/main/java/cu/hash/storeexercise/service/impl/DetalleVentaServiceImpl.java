package cu.hash.storeexercise.service.impl;

import cu.hash.storeexercise.controllers.detalleVenta.DetalleVentaWithoutList;
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

    public DetalleVentaServiceImpl (DetalleVentaRepository repository, Mapper mapper){
        this.repository=repository;
        this.mapper=mapper;
    }
}
