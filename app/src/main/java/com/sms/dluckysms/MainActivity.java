package com.sms.dluckysms;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

import id.ionbit.ionalert.IonAlert;

public class MainActivity extends AppCompatActivity {

    DluckySmsReceiver dluckySmsReceiver = new DluckySmsReceiver();

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "balKey";
    public static final String Amount = "amountKey";

    SharedPreferences sharedpreferences;
    private static final int PERMISSION_REQUEST_CODE = 1;

    private SmsTestListener listener = null;

    public void sendSmsTest(String number, String command, SmsTestListener listener,int type)
    {

        this.listener = listener;
        if(type == 1){
            String currentBal= sharedpreferences.getString(Name, null);


            Random random = new Random();
            int randomNumber = random.nextInt(45 - 28) + 1000;

            SmsManager.getDefault().sendTextMessage(number, null, "D3RBAL " +randomNumber, null, null);
            if(currentBal.equalsIgnoreCase("D3RBAL")){
                currentBal = "D3RBAL ";
            }
            else{
                currentBal = "D3RBAL";
            }


            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Name, currentBal);
            Log.d("tag",currentBal);
            editor.commit();
        }else if(type==2){
            SmsManager.getDefault().sendTextMessage(number, null, command, null, null);

        }

    }


    private void notifyIncoming(String message)
    {
        listener.onSmsTestResponse(message);
    }


    private BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // Standard decoding for SMS
            Object[] pdus = (Object[])intent.getExtras().get("pdus");
            StringBuilder message = new StringBuilder();
            SmsMessage messageSegment;
            Log.d("tag","send");

            for (int i=0;i < pdus.length;i++)
            {

                messageSegment = SmsMessage.createFromPdu((byte[]) pdus[i]);
                message.append(messageSegment.getDisplayMessageBody());
            }


            String[] splited = message.toString().split("\\s+");


            String sample  =  "f";



            if(splited[0].equalsIgnoreCase("Avail.") || splited[0].equalsIgnoreCase("Possible") ){


                if (isMessageValid(message.toString()))
                {

                    String remsample = splited[3].replaceAll("[,.]", "");
                    boolean check = remsample.matches("\\d+(?:\\.\\d+)?");
                    Log.d("check", check+ " - " + remsample);

                    String valueAmount = null;

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    if(check){
                        editor.putString(Amount,splited[3]);
                        Log.d("tag",splited[3]);
                        editor.commit();
                        valueAmount = splited[3];
                    }else{

                        valueAmount = sharedpreferences.getString(Amount, null);
                    }
                    notifyIncoming(valueAmount);
                }

            }else if(splited[0].equalsIgnoreCase("Topup-Subscriber")){

//                new IonAlert(context, IonAlert.SUCCESS_TYPE)
//                        .setTitleText("Your Registration is Successful!")
//                        .setContentText(message.toString())
//                        .show();


                new IonAlert(context, IonAlert.SUCCESS_TYPE)
                        .setTitleText("Your Top-up is Completed!")
                        .setContentText(message.toString()  )
                        .setConfirmClickListener(new IonAlert.ClickListener() {
                            @Override
                            public void onClick(IonAlert sDialog) {

                                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                String remsampleCheck = splited[11].replaceAll("[,.]", "");
                                boolean checkIfNumber = remsampleCheck.matches("\\d+(?:\\.\\d+)?");
                                if(checkIfNumber){
                                    editor.putString(Amount,splited[11]);
                                    Log.d("tag",splited[11]);
                                    editor.commit();
                                    notifyIncoming(splited[11]);

                                }else{

                                     String valueAmount = sharedpreferences.getString(Amount, null);

                                    notifyIncoming(valueAmount);
                                }

                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

            }else{
                new IonAlert(context, IonAlert.ERROR_TYPE)
                        .setTitleText("An unexpected error has occured.")
                        .setContentText("Please try again later.")
                        .setConfirmClickListener(new IonAlert.ClickListener() {
                            @Override
                            public void onClick(IonAlert sDialog) {



                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();


            }


        }

        private boolean isMessageValid(String message)
        {
            // Check that the message is valid
            // Here, I've listed the message text for
            // the check, but you could also use the
            // originating number, etc.
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECEIVE_SMS}, 0);
        } else {
            // register sms receiver
            IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
            registerReceiver(receiver, filter);
        }





        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        String currentBal = sharedpreferences.getString(Name, null);
        if(currentBal == null){

            editor.putString(Name, "D3RBAL");
            editor.putString(Amount, " ");
            editor.commit();

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    protected void onStart(){
        super.onStart();
//        IntentFilter smsFilter = new IntentFilter();
//        smsFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        registerReceiver(dluckySmsReceiver, smsFilter);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        String valueAmount = sharedpreferences.getString(Amount, null);
        notifyIncoming(valueAmount);

        Log.d("taws","eqw");
    }

    protected  void onResume(){

        super.onResume();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        String valueAmount = sharedpreferences.getString(Amount, null);
        notifyIncoming(valueAmount);

    }
}