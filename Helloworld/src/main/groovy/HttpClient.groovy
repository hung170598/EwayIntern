import io.vertx.core.Vertx

class HttpClient {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new HttpClientVerticle())

    }

}
