import dao.UserDAO
import io.vertx.core.Vertx
import model.User

class HttpServer {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx()

        vertx.deployVerticle(new HttpServerVerticle())
    }
}
