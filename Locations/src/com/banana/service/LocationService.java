package com.banana.service;

import java.util.List;

import com.banana.locations.Main_Activity;
import com.banana.locations.Notifyalert_Activity;
import com.banana.messagedatabase.RecordOperations;
import com.banana.messagedatabase.RecordText;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service {
public static final String BROADCAST_ACTION = "Hello World";
//private static final int TWO_MINUTES = 1000 * 60 * 2;
public LocationManager locationManager;
public MyLocationListener listener;
public Location previousBestLocation = null;
//public static final int radius=1500;
Intent intent;
int counter = 0;
private NotificationManager nm;
int a=0;
@Override
public void onCreate() {

    super.onCreate();
    System.out.println("Create........");
    intent = new Intent(BROADCAST_ACTION);   
   // nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
}

@Override
public void onStart(Intent intent, int startId) {
	counter++;
	if(counter<2){
	System.out.println("Start........");
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    listener = new MyLocationListener();        
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Main_Activity.TWO_MINUTES, 0, listener);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Main_Activity.TWO_MINUTES, 0, listener);
	}
}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
    if (currentBestLocation == null) {
        // A new location is always better than no location
        return true;
    }

    // Check whether the new location fix is newer or older
    long timeDelta = location.getTime() - currentBestLocation.getTime();
    boolean isSignificantlyNewer = timeDelta > Main_Activity.TWO_MINUTES;
    boolean isSignificantlyOlder = timeDelta < -Main_Activity.TWO_MINUTES;
    boolean isNewer = timeDelta > 0;

    // If it's been more than two minutes since the current location, use the new location
    // because the user has likely moved
    if (isSignificantlyNewer) {
        return true;
    // If the new location is more than two minutes older, it must be worse
    } else if (isSignificantlyOlder) {
        return false;
    }

    // Check whether the new location fix is more or less accurate
    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
    boolean isLessAccurate = accuracyDelta > 0;
    boolean isMoreAccurate = accuracyDelta < 0;
    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

    // Check if the old and new location are from the same provider
    boolean isFromSameProvider = isSameProvider(location.getProvider(),
            currentBestLocation.getProvider());

    // Determine location quality using a combination of timeliness and accuracy
    if (isMoreAccurate) {
        return true;
    } else if (isNewer && !isLessAccurate) {
        return true;
    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
        return true;
    }
    return false;
}

/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
    return provider1.equals(provider2);
	}

	@Override
	public void onDestroy() {       
		// handler.removeCallbacks(sendUpdatesToUI);  
		System.out.println("Destroy........");
		super.onDestroy();
		Log.v("STOP_SERVICE", "DONE");
		locationManager.removeUpdates(listener);        
	}   

public static Thread performOnBackgroundThread(final Runnable runnable) {
    final Thread t = new Thread() {
        @Override
        public void run() {
            try {
                runnable.run();
            } finally {

            }
        }
    };
    t.start();
    return t;
}

@SuppressWarnings("deprecation")
private void showNotification(String message,String search) {
	 nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	 Intent intent = new Intent(this, Notifyalert_Activity.class);
	 intent.putExtra("delete",search);
    @SuppressWarnings("deprecation")
	Notification notification = new Notification(R.drawable.ic_dialog_map, "Hatırlatma",//yukarıda gözüken
            System.currentTimeMillis());
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
   
    
    notification.setLatestEventInfo(this, "Hatırlatma",	message , contentIntent);//,içerideki başlık tam içerik
    nm.notify("servis aktif beyler", a, notification);
    a++;
}


public class MyLocationListener implements LocationListener
{
	public  double function (double x){
		return x*Math.PI/180;
	} 
	
	public  double getdistance(double p1lat,double p1long,double p2lat,double p2long){
		double R=6378137;
		double dlat= function(p2lat-p1lat);
		double dlong=function(p2long-p1long);
		double a = Math.sin(dlat/2)*Math.sin(dlat/2) + Math.cos(function(p1lat)) * Math.cos(function(p2lat))* Math.sin(dlong / 2) * Math.sin(dlong / 2);
		 double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		 double d = R * c;
		  return d;	
	}

    @Override
	public void onLocationChanged(final Location loc)
    {
        System.out.println("Location changed........");
        if(isBetterLocation(loc, previousBestLocation)) {
            loc.getLatitude();
            loc.getLongitude();
            Toast.makeText(LocationService.this, loc.getLatitude()+"----"+loc.getLongitude()+"------"+loc.getProvider(), Toast.LENGTH_SHORT).show();
            System.out.println(loc.getLatitude()+"----"+loc.getLongitude());
            intent.putExtra("Latitude", loc.getLatitude());
            intent.putExtra("Longitude", loc.getLongitude());     
            intent.putExtra("Provider", loc.getProvider());                 
            sendBroadcast(intent);
            RecordOperations op = new RecordOperations(getBaseContext());
            op.open();
            /*
             41.072497, 29.027798 market
			 41.084975, 29.042331 eczane
			 41.077354, 29.027353 akmerkez
			 41.085064, 29.044199 atm
			 41.085351, 29.043591 akbil
			 41.086014, 29.044674 kitap
			 */
            
            List<RecordText> record=op.getAllPuan();
            for (RecordText recordText : record) {
	        	System.out.println(recordText.getReminderTicket()+ " " +" "+recordText.getReminderTextRecord());
	        	if(recordText.getReminderTicket().equals("shop")){
	        		System.out.println("shop");
	        		int distance =(int) getdistance(41.072497, 29.027798,loc.getLatitude(), loc.getLongitude());
	        		System.out.println("market distance"+distance);
	        		if(distance<=Main_Activity.radius){
	        			System.out.println("notify me");
	        			showNotification("Yakınınızda market tespit edildi unutmayın.("+recordText.getReminderTextRecord()+")",recordText.getReminderTextRecord());
	        		}
	        	}
	        	if(recordText.getReminderTicket().equals("shop1")){
	        		System.out.println("shop1");
	        		int distance =(int) getdistance(41.077354, 29.027353,loc.getLatitude(), loc.getLongitude());
	        		System.out.println("avm distance"+distance);
	        		if(distance<=Main_Activity.radius){
	        			System.out.println("notify me");
	        			showNotification("Yakınınızda avm tespit edildi unutmayın.("+recordText.getReminderTextRecord()+")",recordText.getReminderTextRecord());
	        		}
	        	}
	        	if(recordText.getReminderTicket().equals("atm")){
	        		System.out.println("atm");
	        		int distance =(int) getdistance(41.085064, 29.044199,loc.getLatitude(), loc.getLongitude());
	        		System.out.println("atm distance"+distance);
	        		if(distance<=Main_Activity.radius){
	        			System.out.println("notify me");
	        			showNotification("Yakınınızda atm tespit edildi unutmayın.("+recordText.getReminderTextRecord()+")",recordText.getReminderTextRecord());
	        		}
	        	}
	        	if(recordText.getReminderTicket().equals("book")){
	        		System.out.println("book");
	        		int distance =(int) getdistance(41.086014, 29.044674,loc.getLatitude(), loc.getLongitude());
	        		System.out.println("book distance"+distance);
	        		if(distance<=Main_Activity.radius){
	        			System.out.println("notify me");
	        			showNotification("Yakınınızda kitapçı tespit edildi unutmayın.("+recordText.getReminderTextRecord()+")",recordText.getReminderTextRecord());
	        			
	        		}
	        	}
	        	if(recordText.getReminderTicket().equals("event")){
	        		System.out.println("event");
	        		int distance =(int) getdistance(41.085351, 29.043591,loc.getLatitude(), loc.getLongitude());
	        		System.out.println("akbil distance"+distance);
	        		if(distance<=Main_Activity.radius){
	        			System.out.println("notify me");
	        			showNotification("Yakınınızda akbil yükleme merkezi tespit edildi unutmayın.("+recordText.getReminderTextRecord()+")",recordText.getReminderTextRecord());
	        		}
	        	}
	        	if(recordText.getReminderTicket().equals("pharmacy")){
	        		System.out.println("pharmacy");
	        		int distance =(int) getdistance(41.084975, 29.042331,loc.getLatitude(), loc.getLongitude());
	        		System.out.println("pharmacy distance"+distance);
	        		if(distance<=Main_Activity.radius){
	        			System.out.println("notify me");
	        			showNotification("Yakınınızda eczane tespit edildi unutmayın.("+recordText.getReminderTextRecord()+")",recordText.getReminderTextRecord());
	        		}
	        	}
	        	
            }
            
        	
        
        }                               
    }

    @Override
	public void onProviderDisabled(String provider)
    {
        Toast.makeText( getApplicationContext(), "* Gps Disabled *", Toast.LENGTH_SHORT ).show();
    }


    @Override
	public void onProviderEnabled(String provider)
    {
        Toast.makeText( getApplicationContext(), "* Gps Enabled *", Toast.LENGTH_SHORT).show();
    }


    @Override
	public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }
}
}