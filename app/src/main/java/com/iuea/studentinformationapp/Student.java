package com.iuea.studentinformationapp;

// Student.java
import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String name;
    private int age;
    private int grade;
    private String major;

    public Student(String name, int age, int grade, String major) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
    }

    protected Student(Parcel in) {
        name = in.readString();
        age = in.readInt();
        grade = in.readInt();
        major = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() { return name; }
    public int getAge() { return age; }
    public int getGrade() { return grade; }
    public String getMajor() { return major; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeInt(grade);
        parcel.writeString(major);
    }
}

