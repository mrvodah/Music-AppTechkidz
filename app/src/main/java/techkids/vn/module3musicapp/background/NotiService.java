package techkids.vn.module3musicapp.background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import techkids.vn.module3musicapp.utils.MusicHandle;

public class NotiService extends Service {
    public NotiService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MusicHandle.playPause();
        return super.onStartCommand(intent, flags, startId);
    }
}
