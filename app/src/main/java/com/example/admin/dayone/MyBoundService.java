package com.example.admin.dayone;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * we need bound service when we want to have a synchronous connection
 * b/w activity and service
 */
public class MyBoundService extends Service {

    //binder is a pipe b/w activity and service
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();



    public class LocalBinder extends Binder {
        MyBoundService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyBoundService.this;
        }
    }
    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        //step 2
        return mBinder;
    }

    public int getRandomNo(){
        //step 4
        return  mGenerator.nextInt(100);
    }
}
