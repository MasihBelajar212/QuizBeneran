package com.example.quizbeneran;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private String dateOfBirth;
    private String gender;

    /* renamed from: id */
    private String f24id;
    private String password;
    private String phone;
    private String username;
    private Integer wallet;

    public Integer getWallet() {
        return this.wallet;
    }

    public void setWallet(Integer wallet2) {
        this.wallet = wallet2;
    }

    public void pay(Integer amount) {
        this.wallet = Integer.valueOf(this.wallet.intValue() - amount.intValue());
    }

    public void addFunds(Integer amount) {
        this.wallet = Integer.valueOf(this.wallet.intValue() + amount.intValue());
    }

    public User(int accCount, String username2, String gender2, String dateOfBirth2, String phone2, String password2) {
        this.f24id = idGenerator(accCount + 1);
        this.username = username2;
        this.gender = gender2;
        this.dateOfBirth = dateOfBirth2;
        this.phone = phone2;
        this.password = password2;
        this.wallet = 0;
    }

    private String idGenerator(int count) {
        if (count < 10) {
            return "000" + count;
        } else if (count >= 10) {
            return "00" + count;
        } else if (count >= 100) {
            return "0" + count;
        } else {
            return "" + count;
        }
    }

    protected User(Parcel in) {
        this.f24id = in.readString();
        this.username = in.readString();
        this.gender = in.readString();
        this.dateOfBirth = in.readString();
        this.phone = in.readString();
        this.password = in.readString();
        this.wallet = Integer.valueOf(in.readInt());
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender2) {
        this.gender = gender2;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth2) {
        this.dateOfBirth = dateOfBirth2;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone2) {
        this.phone = phone2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public String getId() {
        return this.f24id;
    }

    public void setId(String id) {
        this.f24id = id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f24id);
        dest.writeString(this.username);
        dest.writeString(this.gender);
        dest.writeString(this.dateOfBirth);
        dest.writeString(this.phone);
        dest.writeString(this.password);
        dest.writeInt(this.wallet.intValue());
    }
}
