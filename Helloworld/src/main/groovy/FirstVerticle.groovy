import io.vertx.core.AbstractVerticle
import io.vertx.core.Future


class FirstVerticle extends AbstractVerticle{
    @Override
    void start(Future<Void> future) throws Exception {
        vertx.createHttpServer()
            .requestHandler({ r ->
                r.response().end("<h1>Success!</h1>")
                println "Statuvs Get Code: ${r.response().getStatusCode()}"
            }).listen(8080, {result ->
                if(result.succeeded()){
                    future.complete()
                } else {
                    future.fail(result.cause())
                }
        })
    }
}
