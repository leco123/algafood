package com.algaworks.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Flyway flyway;

    @Before
    public void setUP() {
        // Habilita para mostrar o erro de forma mais clara
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();
    }
    @Test
    public void deveRetornarStatus200_QuandoConsultarConzinha() {
        given()
            .accept(ContentType.JSON)
        .when() //quando
            .get() // fizer requisição get
        .then() //então
            .statusCode(HttpStatus.OK.value()); // status code precisa ser 200
    }

    public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
        given()
            .accept(ContentType.JSON)
        .when() //quando
            .get() // fizer requisição get
        .then() //então
            .body("", hasSize(4))
            .body("nome", hasItems("Indiana","Tailandesa"));
    }

    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        given()
                .body("{\"nome\": \"Chinesa\"}")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
