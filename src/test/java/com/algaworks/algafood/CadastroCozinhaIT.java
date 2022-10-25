package com.algaworks.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {
    @LocalServerPort
    private  int port;
    @Test
    public void deveRetornarStatus200_QuandoConsultarConzinha() {
        // Habilita para mostrar o erro de forma mais clara
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .basePath("/cozinhas")
            .port(port)
            .accept(ContentType.JSON)
        .when() //quando
            .get() // fizer requisição get
        .then() //então
            .statusCode(HttpStatus.OK.value()); // status code precisa ser 200
    }

    public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
        // Habilita para mostrar o erro de forma mais clara
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .basePath("/cozinhas")
            .port(port)
            .accept(ContentType.JSON)
        .when() //quando
            .get() // fizer requisição get
        .then() //então
            .body("", hasSize(4))
            .body("nome", hasItems("Indiana","Tailandesa")); // status code precis
    }
}
