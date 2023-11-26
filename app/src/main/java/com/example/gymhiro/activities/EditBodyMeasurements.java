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

public class EditBodyMeasurements extends AppCompatActivity {

    EditText date;
    EditText lBiceps;
    EditText rBiceps;
    EditText chest;
    EditText waist;
    EditText lThigh;
    EditText rThigh;
    EditText lCalf;
    EditText rCalf;
    Button editBodyMeasurements;
    UserBodyMeasurements userBodyMeasurements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_body_measurements);
        DatabaseHelper myDB = new DatabaseHelper(EditBodyMeasurements.this);
        date = findViewById(R.id.editBodyMeasurementsDate);
        lBiceps = findViewById(R.id.editBodyMeasurementsLeftBiceps);
        rBiceps = findViewById(R.id.editBodyMeasurementsRightBiceps);
        chest = findViewById(R.id.editBodyMeasurementsChestValue);
        waist = findViewById(R.id.editBodyMeasurementsWaistValue);
        lThigh = findViewById(R.id.editBodyMeasurementsLeftThigh);
        rThigh = findViewById(R.id.editBodyMeasurementsRightThigh);
        lCalf = findViewById(R.id.editBodyMeasurementsLeftCalf);
        rCalf = findViewById(R.id.editBodyMeasurementsRightCalf);
        editBodyMeasurements = findViewById(R.id.editBodyMeasurementsSaveButton);

        userBodyMeasurements = myDB.getLastUserBodyMeasurements();

        date.setText(userBodyMeasurements.getDate());
        lBiceps.setText(String.valueOf(userBodyMeasurements.getlBiceps()));
        rBiceps.setText(String.valueOf(userBodyMeasurements.getrBiceps()));
        chest.setText(String.valueOf(userBodyMeasurements.getChest()));
        waist.setText(String.valueOf(userBodyMeasurements.getWaist()));
        lThigh.setText(String.valueOf(userBodyMeasurements.getlThigh()));
        rThigh.setText(String.valueOf(userBodyMeasurements.getrThigh()));
        lCalf.setText(String.valueOf(userBodyMeasurements.getlCalf()));
        rCalf.setText(String.valueOf(userBodyMeasurements.getrCalf()));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditBodyMeasurements.this,
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

        editBodyMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.updateUserBodyMeasures(new UserBodyMeasurements(userBodyMeasurements.getId(),
                        String.valueOf(date.getText()),
                        Double.parseDouble(lBiceps.getText().toString()),
                        Double.parseDouble(rBiceps.getText().toString()),
                        Double.parseDouble(chest.getText().toString()),
                        Double.parseDouble(waist.getText().toString()),
                        Double.parseDouble(lThigh.getText().toString()),
                        Double.parseDouble(rThigh.getText().toString()),
                        Double.parseDouble(lCalf.getText().toString()),
                        Double.parseDouble(rCalf.getText().toString())));

                finish();
            }
        });

    }
}