package example.ahmaabdo.internetchecker.app;

import android.app.Application;

import example.ahmaabdo.internetchecker.receiver.ConnectivityReceiver;

/**
 * Created by Ahmad on Mar 19, 2018.
 * This class will be called whenever app is launched.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    /**
     * setConnectivityListener() method is used to initiate the connectivity listener.
     *
     * @param listener
     */
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
