package com.mongodb.demo.runboo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class importTest {
    public static void main(String[] args) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("logs");

        File file = new File("C:/Users/Animal/Downloads/access.log");
        BufferedReader reader = null;
        List<String> head = new ArrayList<>();
        head.add("ipAddr");
//        head.add("userName");
//        head.add("password");
//        head.add("time");
//        head.add("path");
//        head.add("response");

        List<Document> docs = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            while ((tempString = reader.readLine()) != null) {
                Document document = new Document();
//                String[] split = tempString.split(" ",2)[3].split("/");
                String[] split = tempString.split(" ",2);
                int length = split.length;
//                System.out.println(length);
//                System.out.println(tempString);
//                for (int i = 0; i < head.size(); i++) {
//                    document.put(head.get(i), split[1]);
//                }
//                document.put(head.get(0), split[1]);
                document.put(head.get(0), split[0]);
                docs.add(document);
            }
            reader.close();
            collection.insertMany(docs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
