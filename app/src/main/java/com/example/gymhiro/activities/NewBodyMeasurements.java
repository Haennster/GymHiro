package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.UserBodyMeasurements;
import com.example.gymhiro.database.DatabaseHelper;

import java.util.Calendar;

public class NewBodyMeasurements extends AppCompatActivity {

    EditText date;
    EditText lBiceps;
    EditText rBiceps;
    EditText chest;
    EditText waist;
    EditText lThigh;
    EditText rThigh;
    EditText lCalf;
    EditText rCalf;
    Button newBodyMeasurements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_body_measurements);
        date = findViewById(R.id.newBodyMeasurementsDate);
        lBiceps = findViewById(R.id.newBodyMeasurementsLeftBiceps);
        rBiceps = findViewById(R.id.newBodyMeasurementsRightBiceps);
        chest = findViewById(R.id.newBodyMeasurementsChestValue);
        waist = findViewById(R.id.newBodyMeasurementsWaistValue);
        lThigh = findViewById(R.id.newBodyMeasurementsLeftThigh);
        rThigh = findViewById(R.id.newBodyMeasurementsRightThigh);
        lCalf = findViewById(R.id.newBodyMeasurementsLeftCalf);
        rCalf = findViewById(R.id.newBodyMeasurementsRightCalf);
        newBodyMeasurements = findViewById(R.id.newBodyMeasurementsSaveButton);

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
                        NewBodyMeasurements.this,
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

        newBodyMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(NewBodyMeasurements.this);
                UserBodyMeasurements userBodyMeasurements = new UserBodyMeasurements(0,
                                    String.valueOf(date.getText()),
                                    Double.parseDouble(lBiceps.getText().toString()),
                                    Double.parseDouble(rBiceps.getText().toString()),
                                    Double.parseDouble(chest.getText().toString()),
                                    Double.parseDouble(waist.getText().toString()),
                                    Double.parseDouble(lThigh.getText().toString()),
                                    Double.parseDouble(rThigh.getText().toString()),
                                    Double.parseDouble(lCalf.getText().toString()),
                                    Double.parseDouble(rCalf.getText().toString()));

                myDB.insertNewBodyMeasurements(userBodyMeasurements);
                finish();
            }
        });
    }
}