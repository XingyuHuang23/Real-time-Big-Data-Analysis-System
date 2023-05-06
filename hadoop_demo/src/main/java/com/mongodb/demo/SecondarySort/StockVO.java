package com.mongodb.demo.SecondarySort;

import org.apache.hadoop.io.Writable;

import javax.xml.crypto.Data;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class StockVO implements Writable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private  String date;

    @Override
    public String toString() {
        return "WorldCupVO{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", closePrice=" + closePrice +
                '}';
    }

    private  String name;
    private  int volume;
    private  float closePrice;

    @Override
    public void write(DataOutput Output) throws IOException {
        Output.writeUTF(date);
        Output.writeUTF(name);
        Output.writeInt(volume);
        Output.writeFloat(closePrice);
    }

    @Override
    public void readFields(DataInput Input) throws IOException {
        date = Input.readUTF();
        name = Input.readUTF();
        volume = Input.readInt();
        closePrice = Input.readFloat();
    }
}
