import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage

class Producer {

    MessageProducer producer;
    Session session
    def connection

    Producer(String queueName) {
        def connectionFactory = new ActiveMQConnectionFactory('tcp://localhost:61616')
        connection = connectionFactory.createConnection("admin", "admin");
        connection.start()
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
        Destination destination = session.createQueue(queueName)
        this.producer = session.createProducer(destination)
    }

    void sendMessage(String message){
        TextMessage textMessage = session.createTextMessage(message)
        producer.send(textMessage)
    }

    void stop(){
        connection.close()
    }

    public static void main(String[] args) {
        Producer producer1 = new Producer("DemoQueue")
        producer1.sendMessage("Hello World")
        producer1.stop()
    }
}
