package dao

import com.mongodb.BasicDBObject
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoCursor
import com.mongodb.client.model.Filters
import groovy.json.JsonOutput
import model.User
import org.bson.Document
import org.bson.types.ObjectId

import java.util.logging.Filter

class UserDAO extends DAO{
    MongoCollection<Document> userCollection

    UserDAO() {
        userCollection = db.getCollection("userCollection");
    }

    void findAllUser(){
        FindIterable<Document> documents = userCollection.find()
        MongoCursor<Document> itr = documents.iterator()
        while (itr.hasNext()){
            println itr.next()
        }
    }

    User findById(String id){
        ObjectId objectId = new ObjectId(id)
        FindIterable<Document> documents = userCollection.find(Filters.eq("_id", objectId))
        MongoCursor<Document> itr = documents.iterator()
        Document doc = itr.next()
        return new User(doc.get("username"), doc.get("password"))
    }

    User findByName(String username){
        FindIterable<Document> documents = userCollection.find(Filters.eq("username", username))
        MongoCursor<Document> itr = documents.iterator()
        if(!itr.hasNext()){
            return new User(username, "user khong ton tai")
        }
        Document doc = itr.next()
        return new User(doc.get("username"), doc.get("password"))
    }

    void addUser(User user){
        String userJson = JsonOutput.toJson(user)
        Document doc = Document.parse(userJson)
        this.userCollection.insertOne(doc)
    }

    void putUser(User user){
        String userJson = JsonOutput.toJson(user)
        Document doc = Document.parse(userJson)
        Document updateDoc = new Document().append('$set', doc)
        this.userCollection.updateOne(Filters.eq("username", user.username), updateDoc)
    }

    void deleteUserByName(String username){
        this.userCollection.deleteOne(Filters.eq("username", username))
    }
}
