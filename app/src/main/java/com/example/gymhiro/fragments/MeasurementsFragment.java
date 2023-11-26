package com.example.gymhiro.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymhiro.R;
import com.example.gymhiro.activities.EditBodyMeasurements;
import com.example.gymhiro.activities.EditGeneralBodyMeasurements;
import com.example.gymhiro.activities.NewBodyMeasurements;
import com.example.gymhiro.activities.NewGeneralBodyMeasurements;
import com.example.gymhiro.classes.UserBodyMeasurements;
import com.example.gymhiro.classes.UserGeneralData;
import com.example.gymhiro.database.DatabaseHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MeasurementsFragment extends Fragment {


    ImageView generalMeasurements;
    ImageView bodyMeasurementMore;
    TextView dateGeneralMeasurements;
    TextView dateBodyMeasurements;
    TextView weight;
    TextView height;
    TextView BMI;
    TextView lBiceps;
    TextView rBiceps;
    TextView chest;
    TextView waist;
    TextView lThigh;
    TextView rThigh;
    TextView lCalf;
    TextView rCalf;
    UserGeneralData userGeneralData;
    UserBodyMeasurements userBodyMeasurements;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_measurements, container, false);

        generalMeasurements = v.findViewById(R.id.userGeneralData);
        bodyMeasurementMore = v.findViewById(R.id.bodyMeasurementMore);

        weight = v.findViewById(R.id.weightValue);
        height = v.findViewById(R.id.heightValue);
        BMI = v.findViewById(R.id.bmiValue);
//        dateGeneralMeasurements = v.findViewById(R.id.);

        lBiceps = v.findViewById(R.id.leftBicepsValue);
        rBiceps = v.findViewById(R.id.rightBicepsValue);
        chest = v.findViewById(R.id.chestValue);
        waist = v.findViewById(R.id.waistValue);
        lThigh = v.findViewById(R.id.leftThighValue);
        rThigh = v.findViewById(R.id.rightThighValue);
        lCalf = v.findViewById(R.id.leftCalfValue);
        rCalf = v.findViewById(R.id.rightCalfValue);
//        dateBodyMeasurements = v.findViewById(R.id.);
        setUpDataIfExist();
//      todo:pobrać dane z bazy i wyświetlić w pomiarach jeżeli jakieś są

        generalMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(),generalMeasurements);
                popupMenu.getMenuInflater().inflate(R.menu.popup_general_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        DatabaseHelper myDB = new DatabaseHelper(getContext());
                        switch (item.getItemId()){
                            case R.id.editGeneral:
                                if(myDB.getAllUserGeneral().size()!=0){
                                    Intent intent1 = new Intent(getActivity(), EditGeneralBodyMeasurements.class);
                                    startActivity(intent1);
                                }else{
                                    Toast.makeText(getContext(), "Nie dodano jeszcze żadnych danych", Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            case R.id.newGeneral:
                                    Intent intent2 = new Intent(getActivity(), NewGeneralBodyMeasurements.class);
                                    startActivity(intent2);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        bodyMeasurementMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(),bodyMeasurementMore);
                popupMenu.getMenuInflater().inflate(R.menu.popup_general_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        DatabaseHelper myDB = new DatabaseHelper(getContext());
                        switch (item.getItemId()){
                            case R.id.editGeneral:
                                if(myDB.getAllUserBodyMeasurements().size()!=0){
                                    Intent intent1 = new Intent(getActivity(), EditBodyMeasurements.class);
                                    startActivity(intent1);
                                }else{
                                    Toast.makeText(getContext(), "Nie dodano jeszcze żadnych danych", Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            case R.id.newGeneral:
                                    Intent intent2 = new Intent(getActivity(), NewBodyMeasurements.class);
                                    startActivity(intent2);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        return v;
    }



    public void setUpDataIfExist(){
        DatabaseHelper myDB = new DatabaseHelper(getContext());
        userGeneralData = myDB.getLastUserGeneral();
        userBodyMeasurements = myDB.getLastUserBodyMeasurements();
        if (userGeneralData != null){
            weight.setText(String.valueOf(userGeneralData.getWeight()));
            height.setText(String.valueOf(userGeneralData.getHeight()));
            BMI.setText(String.valueOf(BMI(userGeneralData.getHeight(),userGeneralData.getWeight())));
        }
        if (userBodyMeasurements != null){
            lBiceps.setText(String.valueOf(userBodyMeasurements.getlBiceps()));
            rBiceps.setText(String.valueOf(userBodyMeasurements.getrBiceps()));
            chest.setText(String.valueOf(userBodyMeasurements.getChest()));
            waist.setText(String.valueOf(userBodyMeasurements.getWaist()));
            lThigh.setText(String.valueOf(userBodyMeasurements.getlThigh()));
            rThigh.setText(String.valueOf(userBodyMeasurements.getrThigh()));
            lCalf.setText(String.valueOf(userBodyMeasurements.getlCalf()));
            rCalf.setText(String.valueOf(userBodyMeasurements.getrCalf()));
        }
    }

    private double BMI(int height, double weight){
        double heightInMeters = height*0.01;
        double BMI = weight / Math.pow(heightInMeters,2);
        if(height != 0 && weight != 0) {
            BigDecimal bigDecimal = new BigDecimal(BMI);
            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

            return bigDecimal.doubleValue();
        }
        return 0;
    }

}