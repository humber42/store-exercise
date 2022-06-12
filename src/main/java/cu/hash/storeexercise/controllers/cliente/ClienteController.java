package cu.hash.storeexercise.controllers.cliente;

import cu.hash.storeexercise.constants.WebResourceKeyConstants;
import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.service.ClienteService;
import cu.hash.storeexercise.utils.BaseClass;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.dozer.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "Cliente Service", value = "This is the cliente rest service")
@RequestMapping(WebResourceKeyConstants.URL_BASE+WebResourceKeyConstants.URL_CLIENT)
public class ClienteController extends BaseClass {

    private final ClienteService service;
    private final Mapper mapper;

    @Operation(summary = "Register a cliente",responses = {
            @ApiResponse(responseCode = "200",description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "401",description = "Unauthorized"),
            @ApiResponse(responseCode = "400",description = "Bad request")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteResponse> registerClient(@Valid @RequestBody ClienteRequest request){
      ClienteResponse response = mapper.map(
                service.registerClient(mapper.map(request, Cliente.class))
                ,ClienteResponse.class);
      return ResponseEntity.ok(response);
    }

    public ClienteController(ClienteService service, Mapper mapper){
        this.service=service;
        this.mapper=mapper;
    }

}
