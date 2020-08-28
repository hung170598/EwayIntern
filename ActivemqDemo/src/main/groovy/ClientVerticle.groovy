import groovy.json.JsonOutput
import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.http.HttpClient

class ClientVerticle extends AbstractVerticle{

    HttpClient httpClient

    @Override
    void start() throws Exception {
        httpClient = vertx.createHttpClient()

        this.post(new MyClass(2, "dasdasdsad", "dasdasdsrqqwr"))
    }

    void post(MyClass myObject){
        def request = httpClient.post(8080, "localhost", "/", {response ->
            println "Status Code: ${response.statusCode()}"
            System.exit(0)
        })
        request.end(JsonOutput.toJson(myObject))
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx()
        vertx.deployVerticle(new ClientVerticle())
    }
}
