package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.UserGeneralData;
import com.example.gymhiro.database.DatabaseHelper;

import java.util.Calendar;

public class NewGeneralBodyMeasurements extends AppCompatActivity {

    EditText date;
    EditText weight;
    EditText height;
    Button saveNewGeneral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_general_body_measurements);

        date = findViewById(R.id.editGeneralDate);
        weight = findViewById(R.id.editGeneralWeight);
        height = findViewById(R.id.editTextHeight);
        saveNewGeneral = findViewById(R.id.editGeneralButton);

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        date.setText(((day<10) ? ("0"+day):day) + "."
                + ((month + 1)<10 ? ("0"+month):(month + 1)) + "." + year);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewGeneralBodyMeasurements.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date.setText(((dayOfMonth<10) ? ("0"+dayOfMonth):dayOfMonth) + "."
                                        + ((month + 1)<10 ? ("0"+month):(month + 1)) + "." + year );
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        saveNewGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(NewGeneralBodyMeasurements.this);
                UserGeneralData userGeneralData = new UserGeneralData(0,
                        String.valueOf(date.getText()),
                        Double.parseDouble(weight.getText().toString()),
                        Integer.parseInt(height.getText().toString()));

                myDB.insertNewUserGeneral(userGeneralData);
                finish();
            }
        });

       }



}