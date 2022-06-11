package cu.hash.storeexercise.service.impl;

import cu.hash.storeexercise.constants.KeyConstants;
import cu.hash.storeexercise.exceptions.ItemAlreadyExistException;

import cu.hash.storeexercise.exceptions.NotValidFieldsException;
import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.repository.ClienteRepository;
import cu.hash.storeexercise.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private boolean existClientByDni(String dni){
        return clienteRepository.getClienteByDni(dni).isPresent();
    }

    @Override
    public Cliente registerClient(Cliente cliente) {
        if(Objects.nonNull(cliente)){
            if(Objects.nonNull(cliente.getNombre())){
                if(Objects.nonNull(cliente.getApellido())){
                    if(Objects.nonNull(cliente.getDni())){
                        if(Objects.nonNull(cliente.getEmail())){
                            if(Objects.nonNull(cliente.getTelefono())){
                                if (existClientByDni(cliente.getDni())){
                                    throw new ItemAlreadyExistException("Existe un cliente con el mismo DNI");
                                }else {
                                    return clienteRepository.save(cliente);
                                }
                            }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Telefono}");
                        }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Email}");
                    }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Dni}");
                }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Apellido}");
            }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Nombre}");
        }else throw new NotValidFieldsException(KeyConstants.NOT_NULL+" {Cliente}");
    }


    @Autowired
    public ClienteServiceImpl(ClienteRepository repository){
        this.clienteRepository=repository;
    }
}
