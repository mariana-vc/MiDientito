package mx.itesm.ladiesnight;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Utils {

    public static NotificationManager mManager;

    @SuppressWarnings("static-access")
    public static void generateNotification(Context context){

        mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,Login.class);
        Notification notification = new Notification(R.mipmap.ic_launcher,"¡Revisa tus recordatorios!", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(context, "Recordatorio de MiDientito", "¡Revisa tus recordatorios!", pendingNotificationIntent);
        mManager.notify(0, notification);
    }
}