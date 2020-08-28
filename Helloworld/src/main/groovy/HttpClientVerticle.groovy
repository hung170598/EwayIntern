import groovy.json.JsonOutput
import io.vertx.core.AbstractVerticle
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpClient
import model.User

class HttpClientVerticle extends AbstractVerticle{

    HttpClient httpClient = null;

    @Override
    void start() throws Exception {

        httpClient = vertx.createHttpClient()
        Buffer responseBuffer = Buffer.buffer()

//        for(int i = 1; i< 100; i++){
            this.register(new User("user3", "sadsd1212123"))
//        }
//        this.putUser(new User("hung", "13dadsa123"))
//        this.deleteUserByName("ads2")

//        httpClient.post(8080,"localhost","/", {response ->
//
//            response.handler({buffer ->
//                println ("Response from server: Code:${response.statusCode()}" +
//                        " Body: ${buffer}")
//            })
//        })
//        .end("Day la client")
    }

    void register(User user){
        def request = httpClient.post(8080, "localhost", "/register", {response ->
            println ("Da dang ky! Status Code: " + response.statusCode())
            this.stop()
        })
        request.end(JsonOutput.toJson(user))
    }

    void putUser(User user){
        def request = httpClient.put(8080, "localhost", "/users", {response ->
            println ("Da Put! Status Code: " + response.statusCode())
            System.exit(0)
        })
        request.end(JsonOutput.toJson(user))
    }

    void deleteUserByName(String username){
        def request = httpClient.delete(8080, "localhost", "/users?username=${username}",
                {response ->
                    println ("Da Xoa! Status Code: " + response.statusCode())
                    System.exit(0)
                })
        request.end()
    }

    void stop(){
        httpClient.close()
    }
}
