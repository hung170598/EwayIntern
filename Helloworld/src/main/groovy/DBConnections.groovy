import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

class DBConnections extends MongoClient{
    String dbHost;
    int dbPort;
    String dbName

    DBConnections(String dbHost, int dbPort, String dbName) {
        this.dbHost = dbHost
        this.dbPort = dbPort
        this.dbName = dbName
        MongoClientURI uri = new MongoClientURI("mongodb://${this.dbHost}:")
    }

    DBConnections(String dbName) {
        this("localhost", 27017, dbName);
    }
}
