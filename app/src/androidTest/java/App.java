import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.flashapps.humorfinder.BuildConfig;
import com.orhanobut.logger.Logger;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by jordyseynaeve on 08/04/15.
 */
public class App extends Application {


    private static App instance;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();


        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build());

        mContext=getApplicationContext();
        instance=this;
        try {
            Realm realm = Realm.getInstance(this);
            Logger.d("Realm db path: " + realm.getPath());
            realm.close();
        } catch (RealmMigrationNeededException ex) {
            ex.printStackTrace();
            Logger.d("Realm Migration needed, deleting old Db");
            Realm realm = Realm.getInstance(this);
            Realm.deleteRealm(realm.getConfiguration()); // Delete old realm
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

    public static App getInstance() {
        return instance;
    }
}
