package com.example.studentrecordfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentrecordfinal.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    TextView data;
    Button save, retrieve;
    EditText studentID, studentName, courseSection, residenceAddress, contactNumber;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.buttonSaveRecord);
        retrieve = findViewById(R.id.buttonRetrieveRecord);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student_id = (studentID = findViewById(R.id.editTextStudentId)).getText().toString();
                String student_name = (studentName = findViewById(R.id.editTextStudentName)).getText().toString();
                String course_section = (courseSection = findViewById(R.id.editTextCourseSection)).getText().toString();
                String address = (residenceAddress = findViewById(R.id.editTextResidenceAddress)).getText().toString();
                String contact = (contactNumber = findViewById(R.id.editTextContactNumber)).getText().toString();

                HashMap<String, String> studentData = new HashMap<>();
                studentData.put("student_name", student_name);
                studentData.put("student_id", student_id);
                studentData.put("course_section", course_section);
                studentData.put("address", address);
                studentData.put("contact", contact);

                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append("I am ").append(studentData.get("student_name")).append(" with ")
                        .append(studentData.get("student_id")).append(" taken up ")
                        .append(studentData.get("course_section")).append(" with ")
                        .append(studentData.get("address")).append(", for any question you may contact me at ")
                        .append(studentData.get("contact"));

                writeInFile(messageBuilder.toString());
            }
        });


        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (data = findViewById(R.id.data)).setText(readFromFile());
            }
        });
    }

    //Write file
    private void writeInFile(String data) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Read File
    private String readFromFile() {
        FileInputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = openFileInput(filename);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}