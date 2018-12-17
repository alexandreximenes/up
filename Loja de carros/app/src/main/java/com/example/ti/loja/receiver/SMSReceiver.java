package com.example.ti.loja.receiver;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.dao.EmpresaDAO;

public class SMSReceiver extends BroadcastReceiver {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
            byte[] pdu = (byte[]) pdus[0];
            String formato =(String) intent.getSerializableExtra("format");
            SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

            String telefone = sms.getDisplayOriginatingAddress();

            EmpresaDAO dao = new EmpresaDAO(context);
            boolean ehCadastrado = dao.ehCadastrado(telefone);

            if(ehCadastrado){
                Mensagem.show(context, "SMSReceiver", Toast.LENGTH_SHORT);
                MediaPlayer m = MediaPlayer.create(context, MediaPlayer.MEDIA_ERROR_UNKNOWN);
                m.start();
            }


        }
    }
}
