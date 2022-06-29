package cu.hash.storeexercise.controllers;

import cu.hash.storeexercise.StoreExerciseApplication;
import cu.hash.storeexercise.controllers.login.UserLogin;
import cu.hash.storeexercise.exceptions.NotFoundItemException;
import cu.hash.storeexercise.models.Producto;
import cu.hash.storeexercise.repository.ProductoRepository;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = StoreExerciseApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private ProductoRepository productoRepository;

    @AfterEach
    public void resetDb(){
        productoRepository.deleteAll();
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
    public void getProducts() throws Exception{
        String accessToken = obtainAccesToken("cliente1","48623786");
        mockMvc.perform(get("/api/v1/product")
                .header("Authorization","Bearer "+accessToken)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    public void registerProduct()throws Exception{
        String accessToken = obtainAccesToken("cliente1","48623786");
        Producto producto = new Producto();
        producto.setNombre("Onions");
        producto.setPrecio((float) 2.35);

        mockMvc.perform(post("/api/v1/product")
                .header("Authorization", "Bearer "+ accessToken)
                .contentType("application/json;charset=UTF-8")
                .content(JsonUtil.toJson(producto))
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk());

        Producto productoTest = productoRepository.getProductoByNombre("Onions").orElseThrow(()->new NotFoundItemException("Not found"));

        assertThat(productoTest.getNombre()).isEqualTo(producto.getNombre());
    }

    @Test
    public void getProductById_andStatusIsNotFoundProduct() throws Exception{
        String accessToken = obtainAccesToken("cliente1","48623786");

        mockMvc.perform(get("/api/v1/product/1")
                .header("Authorization","Bearer "+accessToken)
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void getProductByName() throws Exception{
        String accessToken = obtainAccesToken("cliente1","48623786");
        Producto producto = new Producto();
        producto.setNombre("Onions");
        producto.setPrecio((float) 2.35);
        productoRepository.save(producto);

        mockMvc.perform(get("/api/v1/product/name/Onions")
                .header("Authorization","Bearer "+accessToken)
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Onions"));

    }

}
