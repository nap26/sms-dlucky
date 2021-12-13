package com.sms.dluckysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class DluckySmsReceiver  extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

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

//        if (isMessageValid(message.toString()))
//        {
//            notifyIncoming(message.toString());
//        }

    }

    private boolean isMessageValid(String message)
    {
        // Check that the message is valid
        // Here, I've listed the message text for
        // the check, but you could also use the
        // originating number, etc.
        return true;
    }
}
