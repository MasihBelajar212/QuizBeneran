package com.example.quizbeneran;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
    private String productName;
    private Integer productPrice;
    private String transDate;
    private String transUserId;

    public Transaction(String transUserId2, String productName2, String transDate2, Integer productPrice2) {
        this.transUserId = transUserId2;
        this.productName = productName2;
        this.transDate = transDate2;
        this.productPrice = productPrice2;
    }

    protected Transaction(Parcel in) {
        this.transUserId = in.readString();
        this.productName = in.readString();
        this.transDate = in.readString();
        if (in.readByte() == 0) {
            this.productPrice = null;
        } else {
            this.productPrice = Integer.valueOf(in.readInt());
        }
    }

    public String getTransUserId() {
        return this.transUserId;
    }

    public void setTransUserId(String transUserId2) {
        this.transUserId = transUserId2;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public String getTransDate() {
        return this.transDate;
    }

    public void setTransDate(String transDate2) {
        this.transDate = transDate2;
    }

    public Integer getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Integer productPrice2) {
        this.productPrice = productPrice2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.transUserId);
        dest.writeString(this.productName);
        dest.writeString(this.transDate);
        if (this.productPrice == null) {
            dest.writeByte((byte) 0);
            return;
        }
        dest.writeByte((byte) 1);
        dest.writeInt(this.productPrice.intValue());
    }
}
