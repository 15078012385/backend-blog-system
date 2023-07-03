package com.dingding.backendblogsystem;

import java.io.*;

public class SerializationExample {

    public static void main(String[] args) {
        // 创建一个Person对象
        Person person = new Person("Tom", 30);

        // 将Person对象序列化并保存到文件中
        serialize(person, "person.txt");

        // 从文件中读取序列化的Person对象
        Person restoredPerson = (Person) deserialize("person.txt");

        // 输出反序列化后的Person对象的属性值
        System.out.println("Name: " + restoredPerson.getName());
        System.out.println("Age: " + restoredPerson.getAge());
    }

    // 将Java对象序列化并保存到文件中
    public static void serialize(Object obj, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
            System.out.println("Java对象已被序列化并保存到文件" + fileName + "中");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件中读取序列化的Java对象
    public static Object deserialize(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Java对象已从文件" + fileName + "中反序列化");
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
