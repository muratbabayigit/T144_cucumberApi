package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class ReqResStepdefinitions {
/*
    https://reqres.in/api/users/2 URL'sine bir GET isteği gönderin.
    Gelen yanıtın durum kodunun 200 olduğunu doğrulayın.
    Yanıtın JSON body'sinde yer alan data.id değerinin 2 olduğunu doğrulayın.
    Yanıt süresinin 2 saniyeden kısa olduğunu kontrol edin.
*/

    public String endpoint = "";
    Response response;
    JsonPath resJP;
    JSONObject reqBody;

    @Given("kullanicimiz {string} adresine gider")
    public void kullanicimiz_adresine_gider(String adres) {
        endpoint += ConfigReader.getProperty(adres);
    }
    @Then("kullanicimiz path parametreleri olarak {string} kullanir")
    public void kullanicimiz_path_parametreleri_olarak_kullanir(String pathparametreleri) {
        endpoint += pathparametreleri;
    }
    @Then("kullanicimiz response degerlerini kaydeder")
    public void kullanicimiz_response_degerlerini_kaydeder() {
        response = RestAssured
                   .given()
                   .when()
                   .get(endpoint);
    }
    @Then("kullanicimiz response degerlerini jSonPath olarak kaydeder")
    public void kullanicimiz_response_degerlerini_j_son_path_olarak_kaydeder() {
        JsonPath resJP=response.jsonPath();

    }
    @Then("kullanicimiz response status kodunun {int}")
    public void kullanicimiz_response_status_kodunun(Integer StatusCode) {

        Assert.assertEquals(response.statusCode(), StatusCode);

    }
    @Then("kullanici body icerisindeki {string} altinda olan {string} degerinin {int} oldugunu test eder")
    public void kullanici_body_icerisindeki_altinda_olan_degerinin_oldugunu_test_eder(String data, String id, Integer sayi) {
        int userId=response.jsonPath().getInt(data+"."+id);
       Assert.assertEquals(userId, sayi);
    }

    @Then("kullanicimiz {string} degeri olarak {string} ve {string} degeri olarak {string} girererk Request Body Olusturur")
    public void kullanicimiz_degeri_olarak_ve_degeri_olarak_girererk_request_body_olusturur(String name, String namevalue, String job, String jobValue) {
        reqBody=new JSONObject();
        reqBody.put(name, namevalue);
        reqBody.put(job, jobValue);
    }


    @Then("kullanicimiz header degeri olarak {string} , {string} girer ve response degerlerini kaydeder")
    public void kullanicimiz_header_degeri_olarak_girer_ve_response_degerlerini_kaydeder(String contentType, String contentTypevalue) {

        response = RestAssured
                .given()
                .header(contentType, contentTypevalue)
                .when()
                .body(reqBody)
                .post(endpoint);
    }
    @Then("kullanici body icerisindeki {string} alaninin {string} oldugunu test eder")
    public void kullanici_body_icerisindeki_alaninin_oldugunu_test_eder(String name, String nameValue) {

        String names=response.jsonPath().getString("map.name");
        Assert.assertEquals(names, nameValue,"Bu da bizim api'a vedamız olsun!");

    }

}
