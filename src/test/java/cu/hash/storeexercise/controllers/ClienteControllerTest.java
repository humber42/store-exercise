package cu.hash.storeexercise.controllers;

import cu.hash.storeexercise.StoreExerciseApplication;
import cu.hash.storeexercise.controllers.login.UserLogin;
import cu.hash.storeexercise.exceptions.NotFoundItemException;
import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.repository.ClienteRepository;
import cu.hash.storeexercise.utils.JsonUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StoreExerciseApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private ClienteRepository clienteRepository;

    @AfterEach
    public void resetDb(){
        clienteRepository.deleteAll();
    }

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(filterChainProxy).build();
    }

    private String obtainAccesToken(String username,String password) throws Exception{
        UserLogin userLogin = new UserLogin(username,password);
        ResultActions result
                = mockMvc.perform(post("/api/v1/login")
                .contentType("application/json;charset=UTF-8")
                .content(JsonUtil.toJson(userLogin))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }


    @Test
    void registerAClient() throws Exception{

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        cliente.setTelefono("+5352789645");

        String accessToken = obtainAccesToken("cliente1","48623786");

        mockMvc.perform(post("/api/v1/client")
                .header("Authorization","Bearer "+accessToken)
                .contentType("application/json;charset=UTF-8")
                .content(JsonUtil.toJson(cliente))
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk());

       Cliente cliente1 = clienteRepository.getClienteByDni(cliente.getDni()).orElseThrow(() -> new NotFoundItemException("Not found"));

       assertThat(cliente1.getApellido()).isEqualTo(cliente.getApellido());

    }


}
