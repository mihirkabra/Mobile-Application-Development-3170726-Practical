package com.example.practical11;

public class Student {
    String name, city, docID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public Student(String name, String city, String docID) {
        this.name = name;
        this.city = city;
        this.docID = docID;
    }
}
