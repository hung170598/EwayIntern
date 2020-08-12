package pack2

import pack1.MyClass1

class MyClass2 {
    int id;
    String name;

    MyClass2(int id, String name) {
        this.id = id
        this.name = name
    }

    void combine(MyClass1 myClass1){
        this.id = myClass1.id;
        this.name = myClass1.name;
    }
}
