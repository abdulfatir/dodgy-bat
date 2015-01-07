package com.af.dodgybat;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class DodgyBatActivity extends AndroidApplication implements com.af.dodgybat.utils.SocialNetworkSharer {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
		initialize(new DodgyBat(this), config);
    }

	@Override
	public void shareOnSocialNetworks(final String appname, final int score) {
		/*
		final DodgyBatActivity me = this;
		this.runOnUiThread(new Runnable(){public void run(){
		List<Intent> targetedShareIntents = new ArrayList<Intent>();
	    Intent share = new Intent(android.content.Intent.ACTION_SEND);
	    share.setType("text/plain");
	    List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);
	    if (!resInfo.isEmpty()){
	        for (ResolveInfo info : resInfo) {
	            Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
	            targetedShare.setType("text/plain");
	            if (info.activityInfo.packageName.toLowerCase().contains(appname) || 
	                    info.activityInfo.name.toLowerCase().contains(appname)) {
	                targetedShare.putExtra(Intent.EXTRA_TEXT,"Hey friends I'm playing Dodgy Bat. Download it from Nokia App Store.");
	                targetedShare.setPackage(info.activityInfo.packageName);
	                targetedShareIntents.add(targetedShare);
	            }
	        }
	        if(targetedShareIntents.size()>0)
	        { 
	        Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Select app to share");
	        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
	        startActivity(chooserIntent);
	        }
	        else
	        {
	        
	        AlertDialog.Builder builder1 = new AlertDialog.Builder(me);
	        builder1.setTitle("No App Found!");
            builder1.setMessage("No installed application found which can share on " + appname+".");
            builder1.setCancelable(true);
            builder1.setNeutralButton("OK",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            AlertDialog warn = builder1.create();
            warn.show();
	        	
	        }
	    }
	}});*/
		
		if(appname.equals("facebook"))
		{
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sharer/sharer.php?u=https://play.google.com/store/apps/details?id=com.af.dodgybat"));
			startActivity(browserIntent);
		}
		else if(appname.equals("twitter"))
		{
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/home?status=I%20am%20playing%20Dodgy%20Bat.%20I%20got%20a%20score%20of%20"+score+".%20It's%20awesome.%20Download%20it%20from:%20http://goo.gl/ynzIIw"));
			startActivity(browserIntent);
		}
	}
}