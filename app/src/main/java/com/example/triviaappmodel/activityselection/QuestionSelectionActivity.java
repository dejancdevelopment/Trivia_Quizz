package com.example.triviaappmodel.activityselection;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Calendar;

import com.example.triviaappmodel.activityquestion.QuestionsActivity;
import com.example.triviaappmodel.Receiver.NotificationReciever;
import com.example.triviaappmodel.activitysettings.SettingsActivity;

import mk.codeacademy.triviaquizz.R;

public class QuestionSelectionActivity extends AppCompatActivity {

    Spinner sCategory, sDifficulty, sType;
    EditText editNumber;
    ImageView settings,infoApp;

    String category;
    String difficulty;
    String type;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showNotification();

        sCategory = findViewById(R.id.spinner_category);
        sDifficulty = findViewById(R.id.spinner_difficulty);
        sType = findViewById(R.id.spinner_type);
        settings=findViewById(R.id.settingsImage);
        infoApp=findViewById(R.id.aboutImage);

        editNumber = findViewById(R.id.editNumber);
        editNumber.setText(R.string.num_text);
        editNumber.setCursorVisible(false);
        editNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    editNumber.setCursorVisible(true);
                    editNumber.setText("");
                    editNumber.setError("Number of questions must be between 1 and 30");
                    editNumber.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);

            }
        });

        sCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (sCategory.getSelectedItem().equals("Film")) {
                    category = "11";
                } else if (sCategory.getSelectedItem().equals("Music")) {
                    category = "12";
                }else if(sCategory.getSelectedItem().equals("Computers")){
                    category = "18";
                } else if (sCategory.getSelectedItem().equals("Sport")) {
                    category = "21";
                }else if(sCategory.getSelectedItem().equals("Geography")) {
                    category = "22";
                } else if (sCategory.getSelectedItem().equals("Art")) {
                    category = "25";
                } else if(sCategory.getSelectedItem().equals("Celebrities")) {
                    category = "26";
                } else {
                    category = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (sType.getSelectedItem().equals("multiple")) {
                    type = "multiple";
                } else if (sType.getSelectedItem().equals("true/false")) {
                    type = "boolean";
                } else {
                    type = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (sDifficulty.getSelectedItem().equals("Difficulty")) {
                    difficulty = "";
                } else if (sDifficulty.getSelectedItem().equals("Easy")) {
                    difficulty = "easy";
                } else if (sDifficulty.getSelectedItem().equals("Medium")) {
                    difficulty = "medium";
                } else {
                    difficulty = "hard";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button button = findViewById(R.id.playBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editNumber.getText().toString().equals("No.")) {
                    editNumber.setError("enter number between 1 and 30");
                    editNumber.requestFocus();

                } else{
                    Intent intent = new Intent(QuestionSelectionActivity.this, QuestionsActivity.class);
                    intent.putExtra("Q_NUMBER", editNumber.getText().toString());
                    intent.putExtra("SPIN_CATEGORY", category);
                    intent.putExtra("SPIN_DIFF", difficulty);
                    intent.putExtra("SPIN_TYPE", type);
                    startActivity(intent);
                }
            }
        });
    }

    private void showNotification() {

        Intent intent = new Intent(this, NotificationReciever.class);

        boolean alarmExist = PendingIntent.getBroadcast(
                this,
                1,
                intent,
                PendingIntent.FLAG_NO_CREATE) != null;

        if (!alarmExist) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    1,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            if (alarmManager != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY,22);
                calendar.set(Calendar.MINUTE,0);
                alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent);
            }
        }
    }

    public void openSettingsOnClick(View view) {

        startActivity(new Intent(QuestionSelectionActivity.this, SettingsActivity.class));
    }

    public void openInfoOnClick(View view) {

        DialogFragment dialogFragment=new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
    }
}
