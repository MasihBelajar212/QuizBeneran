package com.example.quizbeneran;

import android.os.Parcel;
import android.os.Parcelable;

public class StoreItem implements Parcelable {
    public static final Parcelable.Creator<StoreItem> CREATOR = new Parcelable.Creator<StoreItem>() {
        public StoreItem createFromParcel(Parcel in) {
            return new StoreItem(in);
        }

        public StoreItem[] newArray(int size) {
            return new StoreItem[size];
        }
    };
    private Integer maxPlayer;
    private Integer minPlayer;
    private Integer price;
    private String productName;

    public StoreItem(String productName2, Integer minPlayer2, Integer maxPlayer2, Integer price2) {
        this.productName = productName2;
        this.minPlayer = minPlayer2;
        this.maxPlayer = maxPlayer2;
        this.price = price2;
    }

    protected StoreItem(Parcel in) {
        this.productName = in.readString();
        if (in.readByte() == 0) {
            this.minPlayer = null;
        } else {
            this.minPlayer = Integer.valueOf(in.readInt());
        }
        if (in.readByte() == 0) {
            this.maxPlayer = null;
        } else {
            this.maxPlayer = Integer.valueOf(in.readInt());
        }
        if (in.readByte() == 0) {
            this.price = null;
        } else {
            this.price = Integer.valueOf(in.readInt());
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productName);
        if (this.minPlayer == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.minPlayer.intValue());
        }
        if (this.maxPlayer == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.maxPlayer.intValue());
        }
        if (this.price == null) {
            dest.writeByte((byte) 0);
            return;
        }
        dest.writeByte((byte) 1);
        dest.writeInt(this.price.intValue());
    }

    public int describeContents() {
        return 0;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public Integer getMinPlayer() {
        return this.minPlayer;
    }

    public void setMinPlayer(int minPlayer2) {
        this.minPlayer = Integer.valueOf(minPlayer2);
    }

    public Integer getMaxPlayer() {
        return this.maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer2) {
        this.maxPlayer = Integer.valueOf(maxPlayer2);
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(int price2) {
        this.price = Integer.valueOf(price2);
    }
}
