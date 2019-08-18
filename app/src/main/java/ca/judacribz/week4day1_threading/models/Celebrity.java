package ca.judacribz.week4day1_threading.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Celebrity implements Parcelable {
    long id = -1;
    private String
            firstName,
            lastName;
    int age;

    public Celebrity(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    protected Celebrity(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        age = in.readInt();
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeInt(age);
    }
}
