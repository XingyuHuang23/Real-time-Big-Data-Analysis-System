package com.mongodb.demo.SecondarySort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CustomKey implements WritableComparable<CustomKey> {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;



    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    private Integer volume;

    public Float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Float closePrice) {
        this.closePrice = closePrice;
    }

    private Float closePrice;


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(volume);
        out.writeFloat(closePrice);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        name = in.readUTF();
        volume = in.readInt();
        closePrice = in.readFloat();
    }

    @Override
    public int compareTo(CustomKey o) {
        int comparedValue = name.compareTo(o.getName());

        if (comparedValue != 0) {
            return comparedValue;
        }
        //return volume.compareTo(o.getVolume());
        return closePrice.compareTo(o.getClosePrice());
    }

}
