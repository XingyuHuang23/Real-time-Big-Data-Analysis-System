package com.mongodb.demo.FinalPackage.SecondarySortByNationalityWithOverAll;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WorldCupVO implements Writable {

    @Override
    public String toString() {
        return "WorldCupVO{" +
                "nationality='" + nationality + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", overall=" + overall +
                '}';
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private  String nationality;
    private  String name;
    private  int age;
    private  int overall;

    @Override
    public void write(DataOutput Output) throws IOException {
        Output.writeUTF(nationality);
        Output.writeUTF(name);
        Output.writeInt(age);
        Output.writeInt(overall);
    }

    @Override
    public void readFields(DataInput Input) throws IOException {
        nationality = Input.readUTF();
        name = Input.readUTF();
        age = Input.readInt();
        overall = Input.readInt();
    }


}
