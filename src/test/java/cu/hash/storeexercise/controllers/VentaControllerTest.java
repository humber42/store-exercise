package cu.hash.storeexercise.controllers;

import cu.hash.storeexercise.StoreExerciseApplication;
import cu.hash.storeexercise.controllers.login.UserLogin;
import cu.hash.storeexercise.models.Cliente;
import cu.hash.storeexercise.models.Venta;
import cu.hash.storeexercise.repository.ClienteRepository;
import cu.hash.storeexercise.repository.VentaRepository;
import cu.hash.storeexercise.utils.JsonUtil;
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

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = StoreExerciseApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class VentaControllerTest {

    private Cliente cliente;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @AfterEach
    public void resetDb(){
        ventaRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @BeforeEach
    public void setup(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Fernando");
        cliente.setApellido("Lugo");
        cliente.setDni("78023102589");
        cliente.setEmail("fernando.lugo@mail.com");
        this.cliente=clienteRepository.save(cliente);
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
    public void registerVenta() throws Exception{
        Venta venta = new Venta();
        venta.setFecha(Date.valueOf("1998-01-27"));
        venta.setCliente(this.cliente);

        String accessToken = this.obtainAccesToken("cliente1","48623786");
        mockMvc.perform(post("/api/v1/venta")
                .header("Authorization", "Bearer "+accessToken)
                .contentType("application/json;charset=UTF-8")
                .content(JsonUtil.toJson(venta))
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk());

        Venta test = ventaRepository.getAllByFecha(Date.valueOf("1998-01-27")).get(0);

        assertThat(test.getFecha()).isEqualTo(venta.getFecha());
    }

    @Test
    public void getAllProducts() throws Exception{
        String accessToken = this.obtainAccesToken("cliente1","48623786");
        mockMvc.perform(get("/api/v1/venta")
                .header("Authorization","Bearer "+accessToken)
                .accept("application/json;charset=UTF-8"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void getProductById_thenNotFound() throws Exception{
        String accessToken = this.obtainAccesToken("cliente1","48623786");
        mockMvc.perform(get("/api/v1/venta/1")
                .accept("application/json;charset=UTF-8")
                .header("Authorization", "Bearer "+accessToken))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }





}
