package auxesisapp.anjanproduction.com.auxesis;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by HP on 02-08-2018.
 */

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "Android News App";
    private ArrayList<notoficationModel> list;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //It is optional
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        list=getTasksFromSP(MyFirebaseMessagingService.this);
        if(list==null){
            list=new ArrayList<>();
        }
        notoficationModel model=new notoficationModel(remoteMessage.getData().get("title").toString(),remoteMessage.getData().get("body").toString());
        list.add(model);
        saveTasksToSP(MyFirebaseMessagingService.this,list);

        //Calling method to generate notification
        sendNotification(remoteMessage.getData().get("title").toString(),remoteMessage.getData().get("body").toString());
    }

    //This method is only generating push notification
    private void sendNotification(String title, String messageBody) {


        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title",title);
        intent.putExtra("body",messageBody);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.auxe)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    public void saveTasksToSP(Context context, ArrayList<notoficationModel> fav){

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(fav);
        editor.putString("NOT",json);
        editor.commit();

    }

    public ArrayList<notoficationModel> getTasksFromSP(Context context){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson=new Gson();
        String json=preferences.getString("NOT","");
        list=gson.fromJson(json,new TypeToken<ArrayList<notoficationModel>>(){}.getType());
        return list;
    }



}
