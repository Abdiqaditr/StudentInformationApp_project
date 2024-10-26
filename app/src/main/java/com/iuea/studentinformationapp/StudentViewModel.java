package com.iuea.studentinformationapp;

// StudentViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentViewModel extends ViewModel {

    private final MutableLiveData<List<Student>> students = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        List<Student> currentStudents = students.getValue();
        if (currentStudents != null) {
            currentStudents.add(student);
            students.setValue(currentStudents);
        }
    }
}
