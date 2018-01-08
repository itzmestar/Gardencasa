package com.gardencasa.gardencasa.alarm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.AlarmManager;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.gardencasa.gardencasa.MyPlantsActivity;
import com.gardencasa.gardencasa.R;


/**
 * Created by TARIQUE on 05-05-2017.
 */

/*public class SchedulingService extends IntentService {

    public static final String TAG = "SchedulingService";
    // An ID used to post the notification.
    public static final int NOTIFICATION_ID = 1;
    // The string the app searches for in the Google home page content. If the app finds
    // the string, it indicates the presence of a doodle.
    //public static final String SEARCH_STRING = "doodle";
    // The Google home page URL from which the app fetches content.
    // You can find a list of other Google domains with possible doodles here:
    // http://en.wikipedia.org/wiki/List_of_Google_domains
    public static final String URL = "http://www.google.com";
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    *//**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     *//*
    public SchedulingService() {
        super("SchedulingService");
    }

    public SchedulingService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //todo actual work
        sendNotification("its water time baby!");
        AlarmReceiver.completeWakefulIntent(intent);
    }

    // Post a notification indicating Alarm.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MyPlantsActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.gc)
                        .setContentTitle("Gardencasa")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}*/

public class SchedulingService extends IntentService {
    public static final String CREATE = "CREATE";
    public static final String CANCEL = "CANCEL";

    private IntentFilter matcher;

    public SchedulingService() {
        super("SchedulingService");
        matcher = new IntentFilter();
        matcher.addAction(CREATE);
        matcher.addAction(CANCEL);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        String notificationId = intent.getStringExtra("notificationId");

        if (matcher.matchAction(action)) {
            execute(action, notificationId);
        }
    }

    private void execute(String action, String notificationId) {
/*        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Cursor c = RemindMe.db.query(Notification.TABLE_NAME, null, "_id = ?",
                new String[]{notificationId}, null, null, null);

        if (c.moveToFirst()) {
            Intent i = new Intent(this, AlarmReceiver.class);
            i.putExtra("id", c.getLong(c.getColumnIndex(Notification.COL_ID)));
            i.putExtra("msg", c.getString(c.getColumnIndex(Notification.COL_MSG)));

            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            long time = c.getLong(c.getColumnIndex(Notification.COL_DATETIME));
            if (CREATE.equals(action)) {
                am.set(AlarmManager.RTC_WAKEUP, time, pi);

            } else if (CANCEL.equals(action)) {
                am.cancel(pi);
            }
        }
        c.close();*/
    }
}