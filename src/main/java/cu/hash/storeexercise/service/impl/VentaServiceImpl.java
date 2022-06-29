package cu.hash.storeexercise.service.impl;

import cu.hash.storeexercise.constants.KeyConstants;
import cu.hash.storeexercise.exceptions.NotFoundItemException;
import cu.hash.storeexercise.exceptions.NotValidFieldsException;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.repository.VentaRepository;
import cu.hash.storeexercise.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository repository;

    @Override
    public List<Venta> getAll() {
        return repository.findAll();
    }

    @Override
    public Venta getByIdVenta(long id) {
        return repository.findById(id).orElseThrow(()->new NotFoundItemException("No existe niguna venta"));
    }

    @Override
    public List<Venta> getAllVentaByClientId(long id) {
        if(id<=0){
            throw new NotValidFieldsException(KeyConstants.NOT_NULL);
        }
        return repository.getAllByCliente_Id(id);
    }

    @Override
    public Venta registerVenta(Venta venta) {
        if(Objects.nonNull(venta)){
            if(Objects.nonNull(venta.getCliente())){
                if(Objects.nonNull(venta.getFecha())){
                    return repository.saveAndFlush(venta);
                }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Fecha}");
            }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Cliente}");
        }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+ " {Venta}");

    }

    @Override
    public List<Venta> getAllVentaByDate(Date date) {
        if(Objects.isNull(date)){
            throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Fecha}");
        }
        return repository.getAllByFecha(date);
    }

    @Autowired
    public VentaServiceImpl(VentaRepository repository){
        this.repository=repository;
    }

}
