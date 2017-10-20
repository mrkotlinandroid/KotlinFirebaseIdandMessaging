package android.com.firebaseidandmessaging

//all codes are taken   original documentaion and modified to kotlin langugage

//https://github.com/firebase/quickstart-android

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.firebase.jobdispatcher.*

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.quickstart.fcm.MyJobService

public class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG:String= "MyFirebaseMsgService";

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)
        if (p0 != null) {
            if (p0.getData().size > 0) {
                Log.d(TAG, "Message data payload: " + p0.getData());

                if (/* Check if data needs to be processed by long running job */ true) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    scheduleJob();
                } else {
                    // Handle message within 10 seconds
                }

            }
        }

        // Check if message contains a notification payload.
        if (p0 != null) {
            if (p0.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + p0.getNotification().getBody());
            }
        }

    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private fun scheduleJob():Unit {

        val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))
        val myJob = dispatcher.newJobBuilder()
                .setService(MyJobService::class.java)
                .setTag("my-job-tag")
                .build()
        dispatcher.schedule(myJob)
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private fun handleNow():Unit {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody:String):Unit{
        var intent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        var pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        val channelId:String= getString(R.string.default_notification_channel_id);
        val defaultSoundUri:Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        var notificationBuilder =
                NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}

