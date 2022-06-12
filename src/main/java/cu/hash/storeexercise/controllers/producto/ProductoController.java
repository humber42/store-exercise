package cu.hash.storeexercise.controllers.producto;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.service.ProductoService;
import io.swagger.annotations.Api;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Producto services", value = "This is the producto services")
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_PRODUCT)
public class ProductoController {

    private final ProductoService service;
    private final Mapper mapper;

    @Operation(summary = "Get a product by id", description = "Return a producto",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = ProductoResponse.class))),
            @ApiResponse(responseCode ="404",description = "Not found product"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<ProductoResponse> getProductsById(@Parameter(description = "Id of the producto", required = true)@PathVariable("id")long id){
        ProductoResponse response = mapper.map(service.getProductById(id),ProductoResponse.class);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all productos", description = "Return a list of producto",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = ProductoResponse.class))),
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductoResponse>> getAllProducts(){
        List<ProductoResponse> productoResponses = service.getAllProduct()
                .stream()
                .map(producto -> mapper.map(producto, ProductoResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productoResponses);
    }

    @Operation(summary = "Get a product by name", description = "Return a producto",responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation",content = @Content(schema = @Schema(implementation = ProductoResponse.class))),
            @ApiResponse(responseCode ="404",description = "Not found product"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @GetMapping(value = "/name/{name}",produces = "application/json")
    public ResponseEntity<ProductoResponse> getProductByName(@Parameter(description = "Name of the producto", required = true)@PathVariable("name")String name){
        ProductoResponse response = mapper.map(service.getProductByName(name),ProductoResponse.class);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Register a producto", description = "Return the register producto", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(
                    schema = @Schema(implementation = ProductoResponse.class)
            )),
            @ApiResponse(responseCode = "400",description = "The product already exist or the fields are invalid")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoResponse> registerProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (description = "A producto to register", required = true, content =
                    @Content(schema = @Schema(implementation = ProductoRequest.class)))
            @RequestBody @Valid ProductoRequest request){
      ProductoResponse response =  mapper
              .map(
                      service.registerProduct(mapper.map(request, Producto.class))
                      ,ProductoResponse.class);
      return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete producto", description = "Delete a producto", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Producto not found")
    })
    @DeleteMapping("/{id}")
    public void deleteProductById(@Parameter(description = "Id of the producto", required = true)@PathVariable("id")long id){
        service.deleteProductById(id);
    }

    @Operation(summary = "Update a producto", description = "Return the producto updated", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(
                    schema = @Schema(implementation = ProductoResponse.class)
            )),
            @ApiResponse(responseCode = "404", description = "Not producto found")
    })
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
