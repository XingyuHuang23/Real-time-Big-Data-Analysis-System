package com.mongodb.demo.FinalPackage.SecondarySortByNationalityWithOverAll;

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
        int cmp = ip1.getNationality().compareTo(ip2.getNationality());

        if (cmp != 0) {
            return cmp;
        }

        return ip2.getOverall().compareTo(ip1.getOverall()); //set the order
    }
}