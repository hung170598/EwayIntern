import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse

class ServerVerticle extends AbstractVerticle{

    HttpServer httpServer
    Producer producer

    @Override
    void start() throws Exception {
        httpServer = vertx.createHttpServer()
        producer = new Producer("DemoQueue")

        httpServer.requestHandler({request ->
            if(request.method() == HttpMethod.GET){
                this.get(request)
            } else if(request.method() == HttpMethod.POST){
                this.post(request)
            }
        })

        httpServer.listen(8080)
    }

    void get(HttpServerRequest request){
        HttpServerResponse response = request.response();
        response.setStatusCode(200);
        response.end("Success!")
    }

    void post(HttpServerRequest request){
        request.handler({buffer ->
            producer.sendMessage(buffer.toString())
            def response = request.response()
            response.setStatusCode(200)
            response.end("Success!")
        })
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx()
        vertx.deployVerticle(new ServerVerticle())
    }
}
