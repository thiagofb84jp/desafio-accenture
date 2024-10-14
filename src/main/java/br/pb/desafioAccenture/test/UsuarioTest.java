package br.pb.desafioAccenture.test;

import br.pb.desafioAccenture.core.APILinks;
import br.pb.desafioAccenture.core.BaseTest;
import br.pb.desafioAccenture.dto.UsuarioDTO;
import br.pb.desafioAccenture.utils.UsuarioUtils;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static br.pb.desafioAccenture.utils.UsuarioUtils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SuppressWarnings("UnnecessaryLocalVariable")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTest extends BaseTest {
    public UsuarioDTO usuario;

    @Test
    public void test1_CriarUsuario() {
        usuario = gerarDadosDeTeste();

        given()
             .body(usuario)
        .when()
             .post(APILinks.USER)
        .then()
             .statusCode(201)
        .and()
             .body("userID", is(notNullValue()))
             .body("username", is(usuario.getUserName()))
             .body("books", is(notNullValue()))
        ;
    }

    @Test
    public void test2_gerarTokenAcesso() {
        usuario = getUsuarioValido();

        given()
             .body(usuario)
        .when()
             .post(APILinks.GENERATE_TOKEN)
        .then()
             .statusCode(200)
        .and()
             .body("token", is(notNullValue()))
             .body("expires", is(notNullValue()))
             .body("status", is("Success"))
             .body("result", is("User authorized successfully."))
        ;
    }

    @Test
    public void test3_confirmarAutorizacaoAcessoUsuario() {
        usuario = getUsuarioComToken();

        given()
             .body(usuario)
        .when()
             .post(APILinks.VERIFY_AUTHORIZED_USER)
        .then()
             .statusCode(200)
        .and()
             .body(equalTo("true"))
        ;
    }

    @Test
    public void test4_listarLivrosDisponiveis() {
        given()
        .when()
             .get(APILinks.BOOKS)
        .then()
             .statusCode(200)
        .and()
             .body("books[0].isbn", is("9781449325862"))
             .body("books[0].title", is("Git Pocket Guide"))
             .body("books[0].subTitle", is("A Working Introduction"))
             .body("books[0].author", is("Richard E. Silverman"))
             .body("books[0].publish_date", is("2020-06-04T08:48:39.000Z"))
             .body("books[0].publisher", is("O'Reilly Media"))
             .body("books[0].pages", is(234))
             .body("books[0].description", startsWith("This pocket guide is the perfect"))
             .body("books[0].website", is("http://chimera.labs.oreilly.com/books/1230000000561/index.html"));
        ;
    }

    @Test
    public void test5_alugarLivros(){
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
        .and()
             .body("books.isbn", hasItems("9781593275846", "9781491950296"));
        ;
    }

    @Test
    public void test6_listarDetalhesUsuarioComLivrosEscolhidos(){
        String userId = getUserIDWithBooks();

        given()
             .pathParam("userID", userId)
             .header("Authorization", "Bearer " + getToken())
        .when()
             .get(APILinks.USER + "/{userID}")
        .then()
             .statusCode(200)
        .and()
             .body("userId", is(userId))
             .body("username", is(notNullValue()))
        .and()
             .body("books.isbn", hasItems("9781593275846", "9781491950296"));
        ;
    }
}
