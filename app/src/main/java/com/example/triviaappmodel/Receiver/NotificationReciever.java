package com.example.triviaappmodel.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;

import com.example.triviaappmodel.activityselection.QuestionSelectionActivity;

import mk.codeacademy.triviaquizz.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.example.triviaappmodel.activitysettings.SettingsFragment.NOTIFICATION_KEY;

public class NotificationReciever extends BroadcastReceiver {


    private static final String CHANNEL_ID ="chanel_id" ;
    public static final int NOTIFICATION_ID=1;


    @Override
    public void onReceive(Context context, Intent intent) {

        ///Deklarirame Menager za da formirame kanal a notifikacija
        NotificationManager notificationManager= (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        //Kreirame kanal za nashata notifikacija
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel
                    (CHANNEL_ID,"TriviaNotification",NotificationManager.IMPORTANCE_DEFAULT);

            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channel);
            }
        }
        ///So ovoj intent ja otvaram aplikacijata pri klik na notifikacijata
        Intent openAppIntent=new Intent(context, QuestionSelectionActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity
                (context,1,openAppIntent,0);


        //Formirame notifikacija
        Notification notification=new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentTitle("TRIVIA QUIZ")
                .setContentText("Ready for another Trivia Challenge?")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

//        if(notificationManager!=null){
//
//            notificationManager.notify(NOTIFICATION_ID,notification);
//        }

        ///Settings za notifikacijata
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        boolean notify=sharedPreferences.getBoolean(NOTIFICATION_KEY,true);

        if(notify){

            if (notificationManager != null) {
                notificationManager.notify(NOTIFICATION_ID,notification);
            }
        }
    }
}
