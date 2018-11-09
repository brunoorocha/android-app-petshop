package com.example.brunoorocha.petshop;

import android.content.BroadcastReceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

import com.example.brunoorocha.petshop.view.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class PowerConnectionReceiver extends BroadcastReceiver {
    Boolean charger=false;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Dispositivo sendo recarregado! aproveite e olhe nossos gatos e cachorros", Toast.LENGTH_SHORT).show();
            charger = true;
        }
        else {
            intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
            Toast.makeText(context, "Seu dispositivo não está sendo recarregando, porem ainda pode olhar nossos gatos e cachorros", Toast.LENGTH_SHORT).show();
            charger = false;
        }
        String plug = isConnected(context);
        Toast.makeText(context, ""+plug, Toast.LENGTH_SHORT).show();

        checking(context,charger);
    }

    public static String isConnected(Context context) {
        String status = null;
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if (plugged == BatteryManager.BATTERY_PLUGGED_AC) {
            status="power";
        }
        else if (plugged == BatteryManager.BATTERY_PLUGGED_USB) {
            status="USB";
        }
        else if (plugged == 0) {
            status="OnBattery";
        }
        return status;
    }

    public void checking(Context context,Boolean charger){

        Intent intent = context.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int curLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        Intent notify = new Intent(context, MainActivity.class);
        Notification noti;
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, notify, 0);

        if(curLevel <= 15 && !charger) {
            noti = new Notification.Builder(context).setTicker("Ticker Title")
                    .setContentTitle("Bateria Fraca")
                    .setContentText("Battery " + curLevel + "% Por favor conecte o carregador para continuar vendo nossos gatinhos rsrs")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pIntent).getNotification();
            noti.flags = Notification.FLAG_AUTO_CANCEL;
            NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            nm.notify(0, noti);
        }
        else if(curLevel == 100 && charger){
            noti = new Notification.Builder(context).setTicker("Ticker Title")
                    .setContentTitle("Bateria Carregada")
                    .setContentText("Bateria carregada, economize energia e desconecte o carregador rsrsr")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pIntent).getNotification();
            noti.flags = Notification.FLAG_AUTO_CANCEL;
            NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            nm.notify(0, noti);
        }
    }





}