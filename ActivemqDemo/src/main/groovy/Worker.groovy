import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Session
import javax.jms.TextMessage

class Worker {

    public static void main(String[] args) {
        def conectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")

        def connection = conectionFactory.createConnection("admin", "admin")
        connection.start()

        def  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

        def destination = session.createQueue("DemoQueue")

        def consumer = session.createConsumer(destination)

        println "Start Read"
        String message;
        while(true){
            def msg = consumer.receive()
            message = ((TextMessage) msg).getText()
            println message
        }
    }
}
