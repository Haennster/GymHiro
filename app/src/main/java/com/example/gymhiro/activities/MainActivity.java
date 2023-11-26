package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.database.DatabaseHelper;
import com.example.gymhiro.databinding.ActivityMainBinding;
import com.example.gymhiro.fragments.ExerciseFragment;
import com.example.gymhiro.fragments.HistoryEmptyFragment;
import com.example.gymhiro.fragments.HistoryFragment;
import com.example.gymhiro.fragments.MoreFragment;
import com.example.gymhiro.fragments.NewTraining;
import com.example.gymhiro.fragments.TrainingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    ArrayList<Exercise> exercises = new ArrayList<>();
    SharedPreferences pref;
    SharedPreferences.Editor ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pref = getSharedPreferences("ActivityPREF",Context.MODE_PRIVATE);
        ed = pref.edit();
//         Czynności wykonywanie podczas pierwszego uruchomienia aplikacji
        if(pref.getBoolean("first_launch",true) ){
            ed.putBoolean("first_launch", false);
            ed.putBoolean("new_training_active", false);
            ed.putString("history", "");
            ed.putString("active_training", "");
            ed.putString("last_training", "");
            ed.putInt("timer", 90);
            ed.commit();
            loadInitialDataToDatabase();

            replaceFragment(new HistoryEmptyFragment());
        }
        else{
//            Początkowy fragment
            if(pref.getString("history", "").equals("") || pref.getString("history", "").equals("[]")){
                replaceFragment(new HistoryEmptyFragment());
            }else{
                replaceFragment(new HistoryFragment());
            }
        }


        if(getIntent().hasExtra("fragToLoad")){
           int fragToLoad = getIntent().getExtras().getInt("fragToLoad");
           switch (fragToLoad) {
               case 1:
                   replaceFragment(new NewTraining());
                   break;
               default: replaceFragment(new TrainingFragment());
           }

        }


//      uruchamianie poszczególnych fragmentów
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.history:
                    if(pref.getString("history", "").equals("") || pref.getString("history", "").equals("[]")){
                        replaceFragment(new HistoryEmptyFragment());
                    }else{
                        replaceFragment(new HistoryFragment());
                    }

                    break;
                case R.id.training:
                    if(!pref.getBoolean("new_training_active", false)) {
                        replaceFragment(new TrainingFragment());
                    }else {
                        replaceFragment(new NewTraining());
                    }
                    break;
                case R.id.exercise:
                    replaceFragment(new ExerciseFragment());
                    break;
                case R.id.more:
                    replaceFragment(new MoreFragment());
                    break;
            }

            return true;
        });


    }

    public void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();

    }
//    Wprowadzenie danych do bazy SQLite
    public void loadInitialDataToDatabase(){
        DatabaseHelper myDB = new DatabaseHelper(this);

//      Categorie
        myDB.addCategory("Klatka piersiowa");
        myDB.addCategory("Ręce");
        myDB.addCategory("Plecy");
        myDB.addCategory("Barki");
        myDB.addCategory("Nogi");
        myDB.addCategory("Własne");

//      Ćwiczenia
//        Klatka Piersiowa
        myDB.addExercise("Pompki", 1);
        myDB.addExercise("Pompki na hantlach", 1);
        myDB.addExercise("Pompki na poręczach", 1);
        myDB.addExercise("Przenoszenie hantli za głowę oburącz leżąc",1 );
        myDB.addExercise("Rozpiętki na maszynie", 1);
        myDB.addExercise("Wyciskanie sztangi na płaskiej ławce", 1);
        myDB.addExercise("Wyciskanie sztangi na ławce dodatniej",1 );
        myDB.addExercise("Wyciskanie sztangi na ławce ujemnej",1 );
        myDB.addExercise("Wyciskanie sztangielek na płaskiej ławce", 1);
        myDB.addExercise("Wyciskanie sztangielek na ławce dodatniej", 1);
        myDB.addExercise("Wyciskanie sztangielek na ławce ujemnej",1 );

        //        Ręce
        myDB.addExercise("Body-up", 2);
        myDB.addExercise("Diamentowe pompki", 2);
        myDB.addExercise("Pompki w podporze tyłem o ławeczkę", 2);
        myDB.addExercise("Prostowanie przedramion przy wyciągu stojąc", 2);
        myDB.addExercise("Prostowanie ramienia z hantlem w opadzie tułowia", 2);
        myDB.addExercise("Wyciskanie francuskie hantla oburącz", 2);
        myDB.addExercise("Uginanie ramion na maszynie", 2);
        myDB.addExercise("Uginanie ramion z hantlami stojąc", 2);
        myDB.addExercise("Uginanie ramion z hantlami w podporze o kolano", 2);
        myDB.addExercise("Uginanie ramion na modlitewniku", 2);

        //        Plecy
        myDB.addExercise("Martwy ciąg", 3);
        myDB.addExercise("Prostowanie tułowia na ławce rzymskiej", 3);
        myDB.addExercise("Przyciąganie linki wyciągu dolnego siedząc", 3);
        myDB.addExercise("Szrugsy na ławce skośnej", 3);
        myDB.addExercise("Szrugsy hantlami", 3);
        myDB.addExercise("Ściąganie drążka wyciągu górnego do klatki",3 );
        myDB.addExercise("Ściąganie drążka wyciągu górnego za głowę", 3);
        myDB.addExercise("Wiosłowanie sztanga nachwytem", 3);
        myDB.addExercise("Wiosłowanie sztanga podchwytem", 3);


        //        Barki
        myDB.addExercise("Unoszenie hantli w przód", 4);
        myDB.addExercise("Unoszenie ramion bokiem w górę z hantlami", 4);
        myDB.addExercise("Wyciskanie sztangi nad głowę siedząc", 4);
        myDB.addExercise("Wyciskanie sztangi nad głowę siedząc", 4);
        myDB.addExercise("Wyciskanie sztangi nad głowę stojąc", 4);
        myDB.addExercise("Wyciskanie sztangieleg siedząc", 4);
        myDB.addExercise("Wznosy hantli w opadzie bokiem", 4);
        //        Nogi
        myDB.addExercise("Martwy ciąg na prostych nogach", 5);
        myDB.addExercise("Prostowanie nóg na maszynie", 5);
        myDB.addExercise("Przysiad bułgarski", 5);
        myDB.addExercise("Przysiad ze sztangą na barkach", 5);
        myDB.addExercise("Przysiad ze sztangą trzymaną z przodu", 5);
        myDB.addExercise("Hip-thrust", 5);
        myDB.addExercise("Wspięcie na palce siedząc z obciążeniem", 5);
    }



}