package com.example.maruf.e_pharma.Reminder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.example.shuvo.medicare.MainActivity;
import com.example.shuvo.medicare.R;

/**
 * Created by shuvo on 10/20/17.
 */

public class MyAlarm extends BroadcastReceiver {
    View view;
    Context context;
    public static MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {

        mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
        this.context = context;
        sendNotification("It's time to take your medicine");
    }
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this.context, MainActivity.class);
        intent.putExtra("fromNotification", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "Medicine";
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this.context,channelId)
                        .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                        .setContentTitle("Medicine Reminder")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
    boolean stop()
    {
        mediaPlayer.stop();
        return true;
    }


}
