package cu.hash.storeexercise.service.impl;

import cu.hash.storeexercise.constants.KeyConstants;
import cu.hash.storeexercise.exceptions.ItemAlreadyExistException;
import cu.hash.storeexercise.exceptions.NotFoundItemException;
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

    private boolean existClientByDni(String dni) {
        return clienteRepository.getClienteByDni(dni).isPresent();
    }

    @Override
    public Cliente registerClient(Cliente cliente) {
        try {
            if (Objects.nonNull(cliente)
                    || Objects.nonNull(cliente.getNombre())
                    || Objects.nonNull(cliente.getApellido())
                    || Objects.nonNull(cliente.getDni())
                    || Objects.nonNull(cliente.getEmail())
                    || Objects.nonNull(cliente.getTelefono())
            ) {

                if (existClientByDni(cliente.getDni())) {
                    throw new ItemAlreadyExistException("Existe un cliente con el mismo DNI");
                } else {
                    return clienteRepository.save(cliente);
                }
            } else throw new NotValidFieldsException(KeyConstants.NOT_NULL + KeyConstants.EMPTY_VALUES);
        }catch (NullPointerException e){
            throw new NotValidFieldsException(KeyConstants.NOT_NULL + KeyConstants.EMPTY_VALUES);
        }

    }

    @Override
    public Cliente getClient(String dni) {
        return clienteRepository.getClienteByDni(dni).orElseThrow(()->new NotFoundItemException("Item not found"));
    }

    @Autowired
    public ClienteServiceImpl(ClienteRepository repository) {
        this.clienteRepository = repository;
    }
}
