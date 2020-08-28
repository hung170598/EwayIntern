import activemq.Producer
import dao.UserDAO
import groovy.json.JsonSlurper
import io.vertx.core.AbstractVerticle
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import model.User

class HttpServerVerticle extends AbstractVerticle{

    HttpServer httpServer = null;
    UserDAO userDAO


    @Override
    void start() throws Exception {
        userDAO = new UserDAO()

        httpServer= vertx.createHttpServer();

        httpServer.requestHandler({request ->
            if(request.method() == HttpMethod.GET){
                this.get(request)
            } else if(request.method() == HttpMethod.POST){
                this.post(request)
            } else if(request.method() == HttpMethod.PUT){
                this.put(request)
            } else if(request.method() == HttpMethod.DELETE){
                this.delete(request)
            }
        })

        httpServer.listen(8080);
    }

    void get(HttpServerRequest request){
//        println (request.path() + 'sadasdas')
        if(request.path() == "/users"){
            this.getUser(request)
            return
        }

        HttpServerResponse response = request.response();
        response.setStatusCode(200);
        response.end("Success!")
    }

    void getUser(HttpServerRequest request){
        def response = request.response()
        def params = request.params()
        User user = null;
        if(params.contains("id")){
            user = userDAO.findById(params.id)
        } else if(params.contains("username")){
            user = userDAO.findByName(params.username)
        } else{
            response.end("Dien truong id hoac username")
            return
        }
        response.end("\nUsername: ${user.username}" +
                "\nPassword: ${user.password}")
        println "Response success"
    }

    void post(HttpServerRequest request){
        if(request.path() == '/register'){
            this.register(request)
            return;
        }

        Buffer bodyBuffer = Buffer.buffer()

        request.handler({buffer ->
            bodyBuffer.appendBuffer(buffer)
            HttpServerResponse response = request.response()
            response.setStatusCode(200)
            response.putHeader("content-length", "1000")
            response.putHeader("content-type", "text/plain")
            println("POST SUCCESS: Body: ${bodyBuffer.toString()}")
            response.end("${buffer.toString()}")
        })
    }

    void register(HttpServerRequest request){
        def producer = new Producer("DemoQueue")
        request.handler({buffer ->
            producer.sendMessage(buffer.toString())
            def response = request.response()
            response.setStatusCode(200)
            response.end("Success!")
        })

        println("Da dang ky")
    }

    void put(HttpServerRequest request){
        if(request.path() == "/users"){
            this.putUser(request);
        }
    }

    void putUser(HttpServerRequest request){
        JsonSlurper jsonSlurper = new JsonSlurper()
        request.handler({buffer ->
            User user = new User(jsonSlurper.parseText(buffer.toString()))
            this.userDAO.putUser(user)
        })
        println "Da put user"
        request.response().end("Da put user")
    }

    void delete(HttpServerRequest request){
        if(request.path() == "/users"){
            this.deleteUser(request)
        }
    }

    void deleteUser(HttpServerRequest request){
        def params = request.params()
        def response = request.response()
        if(!params.contains("username")){
            response.setStatusCode(404)
            response.end("Thieu username")
            return
        }
        userDAO.deleteUserByName(params.username)
        response.end("Da xoa")
        println "da xoa"
    }
}
