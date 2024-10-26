package com.iuea.studentinformationapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Student> students;

    public static DisplayFragment newInstance(ArrayList<Student> students) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("students", students);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_display_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            students = getArguments().getParcelableArrayList("students");
            StudentAdapter adapter = new StudentAdapter(students);
            recyclerView.setAdapter(adapter);
        }

        Button btnAddAnother = view.findViewById(R.id.btnAddAnother);
        btnAddAnother.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(); // Navigate back to DataEntryFragment
        });

        return view;
    }
}
