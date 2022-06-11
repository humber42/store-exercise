package cu.hash.storeexercise.service;

import cu.hash.storeexercise.models.Producto;

import java.util.List;

public interface ProductoService {

    Producto registerProduct(Producto producto);
    List<Producto> getAllProduct();
    Producto getProductById(long id);
    Producto getProductByName(String nombre);
    void deleteProductById(long id);

    Producto updateProducto(Producto producto);

}
