package com.ct.fitness.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;

public class MessageReciever extends BroadcastReceiver 
{
	private static final String TAG = "MessageReciever";
	private Context cX;
	public String rate;
	public TextView tv;
	
	public MessageReciever()
	{
		
	}
	
	@Override
	public void onReceive(Context context, Intent i)
	{
		
		rate = Double.toString(i.getDoubleExtra("rate", 0));
		Log.i(TAG, "recieved message " + rate);
		//Sample4Mixed s = Sample4Mixed.instance();
		
//		if(s != null)
//			s.setRateText("Heart Rate: " + rate);
	}
}
