package com.teckfiesta.horn.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.teckfiesta.horn.R;
import com.teckfiesta.horn.fragments.FragmentAccount;
import com.teckfiesta.horn.fragments.FragmentMore;
import com.teckfiesta.horn.fragments.FragmentOfferList;
import com.teckfiesta.horn.fragments.MoreTabFragments;
import com.teckfiesta.horn.fragments.OffersTabFragment;
import com.teckfiesta.horn.fragments.RewardsTabFragments;
import com.teckfiesta.horn.fragments.ShareTabFragments;
import com.teckfiesta.horn.fragments.TabFragment;
import com.teckfiesta.horn.pageradapter.PagerAdapter;
import com.teckfiesta.horn.utility.tabhostanimationlisenar.AnimatedTabHostListener;
import com.teckfiesta.horn.utility.tabhostanimationlisenar.MyAnimTabHost;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

@SuppressLint("CutPasteId")
public class HomeActivity extends FragmentActivity {
	public static Typeface tp;
	public  String photofromcamera;
	public  TabHost mTabHost;
	public  String drectory;
	public  String drectorythumb;
	private static final String LIST_STATE = "listState";
	private Parcelable mListState = null;
	ProgressDialog pd;
	public TextView text_notification_no_fromactivity;
	public ImageView img_cat_icon;
	File photo;
	public TabFragment activeFragment;
	FrameLayout tabcontent;
	public ViewGroup mTabsPlaceHoler;
	int count=0;
	public ListView ProductList;
	int currentTab=0;
	AsyncTask<Void, Void, Void> mRegisterTask;
	public TextView tv_tab_title;
	TextView text_notification_no;
	boolean galary;
	String a="normal";
	Timer myTimer ;
	String kkr;
	int k=4;
	TabWidget tabs;



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		mTabHost=(TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		

		 TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
				
				@Override
				public void onTabChanged(String tabId) {
					android.support.v4.app.FragmentManager fm =   getSupportFragmentManager();
					RewardsTabFragments accountFragment = (RewardsTabFragments) fm.findFragmentByTag("RewardsTabFragments");
					MoreTabFragments moreFragment = (MoreTabFragments) fm.findFragmentByTag("MoreTabFragments");
					OffersTabFragment offerFragment = (OffersTabFragment) fm.findFragmentByTag("OffersTabFragment");
					ShareTabFragments shareFragment = (ShareTabFragments) fm.findFragmentByTag("ShareTabFragments");
					android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
					
//					/** Detaches the androidfragment if exists */
//					if(androidFragment!=null){
//						//ft.detach(androidFragment);
//						
//						Toast.makeText(MainActivity.this, "1", 200).show();
//					}
//						
//					
//					
//					if(appleFragment!=null){
//						//ft.detach(appleFragment);
//						Toast.makeText(MainActivity.this, " 2", 200).show();
//					}
						
					
					/** If current tab is android */
					if(tabId.equalsIgnoreCase("RewardsTabFragments")){
						ft.setCustomAnimations(
							R.anim.slide_in_right_toleft,R.anim.slide_out_leftright);
					ft.replace(R.id.realtabcontent,new RewardsTabFragments(), "RewardsTabFragments");						
						
						
					}
					else if(tabId.equalsIgnoreCase("MoreTabFragments")){	
						ft.setCustomAnimations(
								R.anim.slide_in_left_to_right,R.anim.slide_out_leftright);
						ft.replace(R.id.realtabcontent,new MoreTabFragments(), "MoreTabFragments");

					}
					else if(tabId.equalsIgnoreCase("OffersTabFragment")){	
						ft.setCustomAnimations(
								R.anim.slide_in_left_to_right,R.anim.slide_out_leftright);
						ft.replace(R.id.realtabcontent,new OffersTabFragment(), "OffersTabFragment");

					}
					
					else if(tabId.equalsIgnoreCase("ShareTabFragments")){	
						ft.setCustomAnimations(
								R.anim.slide_in_left_to_right,R.anim.slide_out_leftright);
						ft.replace(R.id.realtabcontent,new ShareTabFragments(), "ShareTabFragments");

					}
					
					
					
					ft.commit();				
				}
			};


		

		mTabHost.setOnTabChangedListener(tabChangeListener);

		/** Defining tab builder for Andriod tab */
		Log.e("tag", "1");
//		mTabsPlaceHoler = (TabWidget) findViewById(android.R.id.tabs);
//		View tabIndicator = LayoutInflater.from(this).inflate(
//				R.layout.tab_indicator, mTabsPlaceHoler, false);
//		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		
		
		
		TabHost.TabSpec tSpecAndroid = mTabHost.newTabSpec("OffersTabFragment");
		Log.e("tag", "2");
		//icon.setImageResource(R.drawable.rewards_tab);
		//tSpecAndroid.setIndicator(tabIndicator);
		tSpecAndroid.setIndicator("Offers",getResources().getDrawable(R.drawable.offer_tab));    
		Log.e("tag", "3");
		//tSpecAndroid.setContent(new DummyTabContent(HomeActivity.this));    
		tSpecAndroid.setContent(new DummyTabContent(getBaseContext())); 
		//Log.e("tag"," msg "+tabIndicator+" "+ tSpecAndroid.setIndicator(tabIndicator));
		mTabHost.addTab(tSpecAndroid);
		Log.e("tag", "4");


		tSpecAndroid = mTabHost.newTabSpec("RewardsTabFragments");
		//icon.setImageResource(R.drawable.offer_tab);
		//tSpecAndroid.setIndicator(tabIndicator);
		tSpecAndroid.setIndicator("Rewards",getResources().getDrawable(R.drawable.rewards_tab));        
		tSpecAndroid.setContent(new DummyTabContent(getBaseContext())); 
		mTabHost.addTab(tSpecAndroid);
		
		
		tSpecAndroid = mTabHost.newTabSpec("ShareTabFragments");
//		icon.setImageResource(R.drawable.share_tab);
//		tSpecAndroid.setIndicator(tabIndicator);
		tSpecAndroid.setIndicator("Share",getResources().getDrawable(R.drawable.share_tab));        
		tSpecAndroid.setContent(new DummyTabContent(getBaseContext()));  
		mTabHost.addTab(tSpecAndroid);
		
		tSpecAndroid = mTabHost.newTabSpec("MoreTabFragments");
//		icon.setImageResource(R.drawable.more_tab);
//		tSpecAndroid.setIndicator(tabIndicator);
		tSpecAndroid.setIndicator("More",getResources().getDrawable(R.drawable.more_tab));        
		tSpecAndroid.setContent(new DummyTabContent(getBaseContext()));      
		mTabHost.addTab(tSpecAndroid);



	}




//
//	private void setTabs() {
//		mTabHost=(MyAnimTabHost) findViewById(android.R.id.tabhost);
//		tabcontent=(FrameLayout) findViewById(android.R.id.tabcontent);
//		tabs =tabs =(TabWidget) findViewById(android.R.id.tabs);
//		mTabHost.setup(this, getSupportFragmentManager(),
//				android.R.id.tabcontent);
//		mTabsPlaceHoler = (TabWidget) findViewById(android.R.id.tabs);
//		addTab("Offers", R.drawable.offer_tab, FragmentOfferList.class);
//		addTab("Rewards", R.drawable.rewards_tab,FragmentAccount.class);
//		addTab("Share", R.drawable.share_tab, ShareTabFragments.class);
//		addTab("More", R.drawable.more_tab, FragmentMore.class);
//
//		tabs.getChildAt(2).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent sendIntent = new Intent();
//				sendIntent.setAction(Intent.ACTION_SEND);
//				sendIntent.putExtra(Intent.EXTRA_TEXT,
//						"Hey I am using SeatUnity. This is a nice app to store boarding pass and to communicate with seatmates.");
//				sendIntent.setType("text/plain");
//				startActivity(Intent.createChooser(sendIntent, "Share Via"));
//
//			}
//		});
		//		
		//			tabs.getChildAt(0).setOnClickListener(new OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				
		//        		
		//			}
		//		});
		//		tabs.getChildAt(1).setOnClickListener(new OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				
		//			}
		//		});
		//		
		//		tabs.getChildAt(2).setOnClickListener(new OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				
		//        		
		//			}
		//		});
		//		
		//		  

	//}

//
//	private void addTab(String labelId, int drawableId, Class<?> c) {
//		FragmentTabHost.TabSpec spec = mTabHost.newTabSpec(labelId);
//
//		View tabIndicator = LayoutInflater.from(this).inflate(
//				R.layout.tab_indicator, mTabsPlaceHoler, false);
//		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
//		icon.setImageResource(drawableId);
//		spec.setIndicator(tabIndicator);
//		spec.setIndicator(tabIndicator);
//		mTabHost.addTab(spec, c, null);
//	}
//


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//myTimer.cancel();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


	public void setTabSelection() {
		// TODO Auto-generated method stub
		mTabHost.setCurrentTab(1);
	}

	public void setTabSelection0() {
		// TODO Auto-generated method stub

	}
	@Override
	public void onBackPressed() {
		activeFragment.onBackPressed();
	}

	public void close(){
		finish();
	}


}

