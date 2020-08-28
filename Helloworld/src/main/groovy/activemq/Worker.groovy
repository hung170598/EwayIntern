package activemq

import dao.UserDAO
import groovy.json.JsonSlurper
import model.User
import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Destination
import javax.jms.MessageProducer
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
        JsonSlurper jsonSlurper = new JsonSlurper()
        UserDAO userDAO = new UserDAO()
        while(true){
            def msg = consumer.receive()
            message = ((TextMessage) msg).getText()
            println message
            User user = new User(jsonSlurper.parseText(message))
            userDAO.addUser(user)
        }
    }
}
