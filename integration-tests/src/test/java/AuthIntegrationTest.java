import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:4004";

    }

    @Test
    public void shouldReturnOKWithValidToken(){
        //1.Arrange
        String loginPayload= """
                {
                    "email":"testuser@test.com",
                    "password":"password123"
                }
                """;
        //2.Act

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
        //3.Assert
                .statusCode(200)
                .body("token",notNullValue())
                .extract().response();
        System.out.println("Generated Token: " + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnUnauthorizedWithInvalidPassword(){

        String payload = """
                {
                    "email":"testuser@test.com",
                    "password":"wrong-password1234"
                }
                """;

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401)
                .extract().response();


    }

    @Test
    public void shouldReturnUnauthorizedWithInvalidToken(){
        String invalidToken = "Invalid-@token-dsfahkhsjfdlkjdakfkhjhkdjhfalh";
        given()
                .header("Authorization", "Bearer " + invalidToken)
                .when()
                .get("/auth/validate")
                .then()
                .statusCode(401)
                .extract().response();

    }
}
