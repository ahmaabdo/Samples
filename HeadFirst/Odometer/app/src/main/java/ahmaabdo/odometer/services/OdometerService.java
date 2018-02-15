package ahmaabdo.odometer.services;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import java.util.Random;

public class OdometerService extends Service {

    private final IBinder binder = new OdometerBinder();
    private final Random random = new Random();

    private LocationListener listener;
    private LocationManager locManager;

    private static double distanceInMeters;
    private static Location lastLocation = null;

    @Override
    public void onCreate() {
        super.onCreate();

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                //Set the user's starting location
                if (lastLocation == null) lastLocation = location;
                //Update the distance traveled and the user's last location
                distanceInMeters += location.distanceTo(lastLocation);
                lastLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Checking permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Get the most accurate location provider
            String provider = locManager.getBestProvider(new Criteria(), true);
            //Request updates from the location manager
            if (provider != null)
                locManager.requestLocationUpdates(provider, 1000, 1, listener);
        }
    }

    public class OdometerBinder extends Binder {
        public OdometerService getOdometer() {
            return OdometerService.this;
        }
    }

    public OdometerService() {
    }

    public double getDistance() {
        //return random.nextDouble();

        //This converts the distance traveled in meters into miles. We could make this
        //calculation more precise if we wanted to, but it’s accurate enough for our purposes
        return this.distanceInMeters / 1609.344;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locManager.removeUpdates(listener);
        }
        locManager = null;
        listener = null;
    }

    /**
     * IBinder is an interface that’s used to bind your service to the activity
     * Gets called when a component, such as an activity, wants to bind to the service.
     *
     * @param intent The Intent that was used to bind to this service
     * @return an IBinder object.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
