package cu.hash.storeexercise.service.impl;

import cu.hash.storeexercise.constants.KeyConstants;
import cu.hash.storeexercise.exceptions.ItemAlreadyExistException;
import cu.hash.storeexercise.exceptions.NotFoundItemException;
import cu.hash.storeexercise.exceptions.NotValidFieldsException;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.repository.ProductoRepository;
import cu.hash.storeexercise.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    @Override
    public Producto getProductById(long id) {
        return repository.findById(id)
                .orElseThrow(()->new NotFoundItemException("El producto no existe"));
    }

    @Override
    public List<Producto> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Producto getProductByName(String nombre) {
        return repository.getProductoByNombre(nombre).orElseThrow(()->new NotFoundItemException("El producto no existe"));
    }

    @Override
    public Producto registerProduct(Producto producto) {
        if(Objects.nonNull(producto.getNombre())) {
            try{
                getProductByName(producto.getNombre());
                throw new ItemAlreadyExistException("El producto ya existe");
            }catch (NotFoundItemException e){
                return repository.save(producto);
            }
        }else
            throw new NotValidFieldsException(KeyConstants.NOT_NULL);
    }

    @Override
    public void deleteProductById(long id) {
        getProductById(id);
        repository.deleteById(id);
    }


    @Override
    public Producto updateProducto(Producto producto) {
        if(Objects.isNull(producto)){
            throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Producto}");
        }
        getProductById(producto.getId());
        return repository.saveAndFlush(producto);
    }

    @Autowired
    public ProductoServiceImpl(ProductoRepository repository){
        this.repository=repository;
    }
}

