package dao

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase

class DAO {

    static MongoDatabase db;

    DAO() {
        String dbHost = "localhost"
        int dbPort = 27017
        String dbName = "demo"
        MongoClient mongoClient = new MongoClient(dbHost, dbPort);
        DAO.db = mongoClient.getDatabase(dbName)
    }
}
