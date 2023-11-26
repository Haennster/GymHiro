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

public class EditGeneralBodyMeasurements extends AppCompatActivity {

    EditText date;
    EditText weight;
    EditText height;
    Button editNewGeneral;
    UserGeneralData userGeneralData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_general_body_measurements);
        DatabaseHelper myDB = new DatabaseHelper(this);
        date = findViewById(R.id.editGeneralDate);
        weight = findViewById(R.id.editGeneralWeight);
        height = findViewById(R.id.editGeneralHeight);
        editNewGeneral = findViewById(R.id.editGeneralButton);

        userGeneralData = myDB.getLastUserGeneral();
        date.setText(userGeneralData.getDate());
        weight.setText(String.valueOf(userGeneralData.getWeight()));
        height.setText(String.valueOf(userGeneralData.getHeight()));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditGeneralBodyMeasurements.this,
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


        editNewGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.updateUserGeneral(new UserGeneralData(userGeneralData.getId(),
                        String.valueOf(date.getText()),
                        Double.parseDouble(weight.getText().toString()),
                        Integer.parseInt(height.getText().toString())));
                finish();
            }
        });

    }
}