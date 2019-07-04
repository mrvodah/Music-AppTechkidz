package techkids.vn.module3musicapp.background;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

import techkids.vn.module3musicapp.R;
import techkids.vn.module3musicapp.activities.MainActivity;
import techkids.vn.module3musicapp.databases.TopSongModel;

/**
 * Created by VietVan on 12/1/2018.
 */

public class MusicNotification {

    private static RemoteViews remoteViews;
    private static Notification notification;
    private static Notification.Builder builder;
    private static NotificationManager notificationManager;

    public static void setupNotification(Context context, TopSongModel topSongModel){

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_notification);
        remoteViews.setTextViewText(R.id.tv_song, topSongModel.song);
        remoteViews.setTextViewText(R.id.tv_artist, topSongModel.artist);
        remoteViews.setImageViewResource(R.id.iv_play_pause, R.drawable.ic_pause_black_24dp);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                0
        );

        builder = new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_favorite_white_24dp)
                .setContentIntent(pendingIntent)
                .setContent(remoteViews)
                .setOngoing(true);
        notification = builder.build();

        Picasso.get().load(topSongModel.image)
                .into(remoteViews, R.id.iv_song, 0, notification);

        Intent intentPlay = new Intent(context, NotiService.class);
        PendingIntent pendingIntentPlay = PendingIntent.getService(
                context,
                0,
                intentPlay,
                0);

        remoteViews.setOnClickPendingIntent(R.id.iv_play_pause, pendingIntentPlay);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    public static void updateNoti(boolean isPlaying){
        if(isPlaying){
            remoteViews.setImageViewResource(R.id.iv_play_pause, R.drawable.ic_pause_black_24dp);
            builder.setOngoing(true);
        }
        else{
            remoteViews.setImageViewResource(R.id.iv_play_pause, R.drawable.ic_play_arrow_black_24dp);
            builder.setOngoing(false);
        }
        notificationManager.notify(0, builder.build());
    }

}
