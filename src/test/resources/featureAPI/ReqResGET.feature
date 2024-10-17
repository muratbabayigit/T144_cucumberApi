# https://reqres.in/api/users/2 URL'sine bir GET isteği gönderin.
# Gelen yanıtın durum kodunun 200 olduğunu doğrulayın.
# Yanıtın JSON body'sinde yer alan data.id değerinin 2 olduğunu doğrulayın.
# Yanıt süresinin 2 saniyeden kısa olduğunu kontrol edin.


  Feature: ReqRes GET PUT POST Request İslemleri
  @mb
    Scenario: ReqRes Get Request

      Given kullanicimiz "reqResUrl" adresine gider
      Then  kullanicimiz path parametreleri olarak "api/users/2" kullanir
      Then  kullanicimiz response degerlerini kaydeder
      Then  kullanicimiz response degerlerini jSonPath olarak kaydeder
      Then  kullanicimiz response status kodunun 200
      Then  kullanici body icerisindeki "data" altinda olan "id" degerinin 2 oldugunu test eder

# https://reqres.in/api/users URL'sine
#   header değeri "Content-Type", "application/json" olan bir POST isteği gönderin.
#   İstek için JSON formatında şu bilgileri içeren bir body kullanın: { "name": "morpheus", "job": "leader" }
#   Gelen yanıtın durum kodunun 201 olduğunu doğrulayın.
#   Yanıtın JSON body'sinde name alanının morpheus olduğunu kontrol edin.
    @mb
    Scenario: ReqRes Post request
      
      Given kullanicimiz "reqResUrl" adresine gider
      Then  kullanicimiz path parametreleri olarak "api/users" kullanir
      Then kullanicimiz "name" degeri olarak "morpheus" ve "job" degeri olarak "Manager" girererk Request Body Olusturur
      Then  kullanicimiz header degeri olarak "Content-Type" , "application/json" girer ve response degerlerini kaydeder
      Then  kullanicimiz response degerlerini jSonPath olarak kaydeder
      Then  kullanicimiz response status kodunun 201
      Then  kullanici body icerisindeki "name" alaninin "morpheus" oldugunu test eder
      