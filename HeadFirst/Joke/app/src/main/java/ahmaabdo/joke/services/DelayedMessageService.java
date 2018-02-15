package ahmaabdo.joke.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import ahmaabdo.joke.MainActivity;
import ahmaabdo.joke.R;

public class DelayedMessageService extends IntentService {

    public static final String EXTRA_MSG = "message";
    public static final int NOTIFICATION_ID = 1850;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MSG);
        showText(text);
    }

    private void showText(final String text) {
        //Create an action for the notification click
        PendingIntent actionIntent =
                PendingIntent.getActivity(this,
                        0,
                        new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        //Create a notification builder
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setContentTitle(getString(R.string.question))
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setVibrate(new long[]{0, 100})
                        .setContentIntent(actionIntent)
                        .setAutoCancel(true);

        //Issue the notification
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

}
