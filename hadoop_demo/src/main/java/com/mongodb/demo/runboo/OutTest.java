package com.mongodb.demo.runboo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OutTest {
    public static void main(String[] args) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("logs");

        File file = new File("C:/Users/Animal/Downloads/access.log");
        File outfile = new File("G:/access.log");
        BufferedReader reader = null;
        PrintWriter pw = null;


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
            pw = new PrintWriter(new FileWriter(outfile));

            String tempString = null;

            while ((tempString = reader.readLine()) != null) {
                Document document = new Document();
//                String[] split = tempString.split(" ",2)[3].split("/");
                String[] split = tempString.split(" ",2);
                //int length = split.length;
//                System.out.println(length);
//                System.out.println(tempString);
//                for (int i = 0; i < head.size(); i++) {
//                    document.put(head.get(i), split[1]);
//                }
//                document.put(head.get(0), split[1]);

//                document.put(head.get(0),split[0] );
//                docs.add(document);

                pw.println(split[0]);
            }
            reader.close();
            pw.close();
           // collection.insertMany(docs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
