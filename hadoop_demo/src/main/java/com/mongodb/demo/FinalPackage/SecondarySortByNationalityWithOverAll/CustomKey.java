package com.mongodb.demo.FinalPackage.SecondarySortByNationalityWithOverAll;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CustomKey implements WritableComparable<CustomKey> {

    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getOverall() {
        return overall;
    }

    public void setOverall(Integer overall) {
        this.overall = overall;
    }

    private Integer age;

    private Integer overall;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(nationality);
        out.writeUTF(name);
        out.writeInt(age);
        out.writeInt(overall);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        nationality = in.readUTF();
        name = in.readUTF();
        age = in.readInt();
        overall = in.readInt();
    }

    @Override
    public int compareTo(CustomKey o) {
        int comparedValue = nationality.compareTo(o.getNationality());

        if (comparedValue != 0) {
            return comparedValue;
        }
        //return volume.compareTo(o.getVolume());
        return overall.compareTo(o.getOverall());
    }

}
