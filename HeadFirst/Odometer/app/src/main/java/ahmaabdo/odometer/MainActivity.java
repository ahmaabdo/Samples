package ahmaabdo.odometer;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

import ahmaabdo.odometer.services.OdometerService;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_REQUEST_CODE = 698;

    private OdometerService odometer;
    private boolean bound = false;

    //Create a Service connection object
    private ServiceConnection connection = new ServiceConnection() {
        /** Runs when a connection is established between the activity and the service
         *
         * @param name The concrete component name of the service that has been connected.
         * @param binder The IBinder of the Service's communication channel,
         *               which you can now make calls on.
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

            OdometerService.OdometerBinder odometerBinder =
                    (OdometerService.OdometerBinder) binder;
            //Use the IBinder to get a reference to the service
            odometer = odometerBinder.getOdometer();
            //The activity is bound to the service, so set the bound variable to true
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Set bound to false, as MainActivity is no longer bound to OdometerService.
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDistance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
            bindService(new Intent(this, OdometerService.class), connection, BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    bindService(new Intent(this, OdometerService.class),
                            connection, BIND_AUTO_CREATE);
                } else {
                    NotificationManager manager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(123,
                            new NotificationCompat.Builder(this)
                                    .setSmallIcon(android.R.drawable.ic_menu_compass)
                                    .setContentTitle(getResources().getString(R.string.app_name))
                                    .setContentText(getResources().getString(R.string.permission_denied))
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setVibrate(new long[]{1000, 1000})
                                    .setContentIntent(PendingIntent.getActivity(
                                            this,
                                            0,
                                            new Intent(this, MainActivity.class),
                                            PendingIntent.FLAG_UPDATE_CURRENT))
                                    .setAutoCancel(true).build());
                }

            }
        }
    }

    private void displayDistance() {
        final TextView distanceView = findViewById(R.id.distance);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if (bound && odometer != null) {
                    distance = odometer.getDistance();
                }
                String disanceStr = String.format(Locale.getDefault(),
                        "%1$,.2f miles", distance);
                distanceView.setText(disanceStr);
                handler.postDelayed(this, 1000);
            }
        });
    }
}
