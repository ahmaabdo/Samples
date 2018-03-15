package ahmaabdo.realm.db;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Ahmad on Mar 15, 2018.
 * Realm database class
 * Must be added to AndroidManifest.xml at application: android:name=".db.MyDatabase"
 */

public class MyDatabase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
