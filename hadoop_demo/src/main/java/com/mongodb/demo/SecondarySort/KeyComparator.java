package com.mongodb.demo.SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KeyComparator extends WritableComparator {
    protected KeyComparator() {
        super(CustomKey.class, true);
    }
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        CustomKey ip1 = (CustomKey) w1;
        CustomKey ip2 = (CustomKey) w2;
        int cmp = ip1.getName().compareTo(ip2.getName());

        if (cmp != 0) {
            return cmp;
        }

        return ip2.getClosePrice().compareTo(ip1.getClosePrice()); //set the order
    }
}