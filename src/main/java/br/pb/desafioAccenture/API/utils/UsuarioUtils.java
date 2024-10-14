package br.pb.desafioAccenture.API.utils;

import br.pb.desafioAccenture.API.core.APILinks;
import br.pb.desafioAccenture.API.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

@SuppressWarnings("UnnecessaryLocalVariable")
public class UsuarioUtils {
    public static UsuarioDTO usuario;

    public static UsuarioDTO gerarDadosDeTeste() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 99999);

        return new UsuarioDTO("user_" + randomNum, "Abc&defghI_123");
    }

    public static UsuarioDTO getUsuarioValido() {
        usuario = gerarDadosDeTeste();

        given()
             .body(usuario)
        .when()
             .post(APILinks.USER)
        .then()
             .statusCode(201)
        ;

        return usuario;
    }

    public static UsuarioDTO getUsuarioComToken() {
        usuario = getUsuarioValido();

        given()
             .body(usuario)
        .when()
             .post(APILinks.GENERATE_TOKEN)
        .then()
             .statusCode(200)
        ;

        return usuario;
    }

    public static String getUsuarioID() {
        usuario = gerarDadosDeTeste();

        String userID = given()
                              .body(usuario)
                        .when()
                              .post(APILinks.USER)
                        .then()
                              .statusCode(201)
                        .and()
                              .extract().path("userID")
                        ;
        return userID;
    }

    public static String getToken() {
        String token = given()
                            .body(usuario)
                       .when()
                            .post(APILinks.GENERATE_TOKEN)
                       .then()
                            .statusCode(200)
                       .and()
                            .extract().path("token")
                       ;
        return token;
    }

    public static String getUserIDWithBooks() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUserId(getUsuarioID());

        List<HashMap<String, String>> collectionOfIsbns = new ArrayList<>();
        HashMap<String, String> isbn1 = new HashMap<>();
        isbn1.put("isbn", "9781593275846");

        HashMap<String, String> isbn2 = new HashMap<>();
        isbn2.put("isbn", "9781491950296");

        collectionOfIsbns.add(isbn1);
        collectionOfIsbns.add(isbn2);

        usuario.setCollectionOfIsbns(collectionOfIsbns);

        given()
             .header("Authorization", "Bearer " + getToken())
             .body(usuario)
        .when()
             .post(APILinks.BOOKS)
        .then()
             .statusCode(201)
        ;

        String userWithBooks = usuario.getUserId();
        return userWithBooks;
    }
}
