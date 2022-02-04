package com.example.redrock.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlayMusicService extends Service {
    public PlayMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}