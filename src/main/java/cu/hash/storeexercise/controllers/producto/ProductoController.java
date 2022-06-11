package cu.hash.storeexercise.controllers.producto;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.service.ProductoService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_PRODUCT)
public class ProductoController {

    private final ProductoService service;
    private final Mapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> getProductsById(@PathVariable("id")long id){
        ProductoResponse response = mapper.map(service.getProductById(id),ProductoResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> getAllProducts(){
        List<ProductoResponse> productoResponses = service.getAllProduct()
                .stream()
                .map(producto -> mapper.map(producto, ProductoResponse.class))
                .toList();
        return ResponseEntity.ok(productoResponses);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductoResponse> getProductByName(@PathVariable("name")String name){
        ProductoResponse response = mapper.map(service.getProductByName(name),ProductoResponse.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> registerProduct(@RequestBody @Valid ProductoRequest request){
      ProductoResponse response =  mapper
              .map(
                      service.registerProduct(mapper.map(request, Producto.class))
                      ,ProductoResponse.class);
      return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id")long id){
        service.deleteProductById(id);
    }

    @PutMapping
    public ResponseEntity<ProductoResponse> updateProduct(@RequestBody ProductoResponse request){
        ProductoResponse response =  mapper
                .map(
                        service.updateProducto(mapper.map(request, Producto.class))
                        ,ProductoResponse.class);
        return ResponseEntity.ok(response);
    }

    @Autowired
    public ProductoController(ProductoService service,Mapper mapper){
        this.service=service;
        this.mapper=mapper;
    }
}
