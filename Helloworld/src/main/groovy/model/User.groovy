package model

class User {

    String username
    String password

    User(String username, String password) {
        this.username = username
        this.password = password
    }

    User(def map) {
        this(map.username, map.password)
    }
}
