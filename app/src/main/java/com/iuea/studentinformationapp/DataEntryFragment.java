package com.iuea.studentinformationapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class DataEntryFragment extends Fragment {

    private EditText etName, etAge, etGrade, etMajor;
    private Button btnSubmit, btnClear;
    private StudentViewModel studentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_data_entry_fragment, container, false);

        etName = view.findViewById(R.id.etStudentName);
        etAge = view.findViewById(R.id.etStudentAge);
        etGrade = view.findViewById(R.id.etStudentGrade);
        etMajor = view.findViewById(R.id.etStudentMajor);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnClear = view.findViewById(R.id.btnClear);

        btnSubmit.setOnClickListener(v -> submitData());
        btnClear.setOnClickListener(v -> clearFields());

        return view;
    }

    private void submitData() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String gradeStr = etGrade.getText().toString().trim();
        String major = etMajor.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Name cannot be empty");
            return;
        }

        Integer age = parseInteger(ageStr);
        if (age == null || age <= 0) {
            etAge.setError("Enter a valid age");
            return;
        }

        Integer grade = parseInteger(gradeStr);
        if (grade == null || grade < 0 || grade > 100) {
            etGrade.setError("Enter a grade between 0 and 100");
            return;
        }

        Student student = new Student(name, age, grade, major);
        studentViewModel.addStudent(student);
        Toast.makeText(requireContext(), "Student Added", Toast.LENGTH_SHORT).show();
        
        loadDisplayFragment();
    }

    private void loadDisplayFragment() {
        // Retrieve the list of students from ViewModel
        ArrayList<Student> students = new ArrayList<>(studentViewModel.getStudents().getValue());
        DisplayFragment displayFragment = DisplayFragment.newInstance(students);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, displayFragment)
                .addToBackStack(null) // Allows back navigation
                .commit();
    }

    private void clearFields() {
        etName.setText("");
        etAge.setText("");
        etGrade.setText("");
        etMajor.setText("");
    }

    private Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
