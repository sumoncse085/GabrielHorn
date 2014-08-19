package com.teckfiesta.horn.fragments;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import com.teckfiesta.horn.MainActivity;
import com.teckfiesta.horn.R;
import com.teckfiesta.horn.activity.HomeActivity;
import com.teckfiesta.horn.adapter.CustomAdapterForOfferDetails;
import com.teckfiesta.horn.model.OfferDetails;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class FragmentOfferList extends Fragment {
	ArrayList<OfferDetails>list;
	//ListView lv_offer;
	PullToRefreshListView list_view_latest_offer;
	ListView listviewforoffer;
	CustomAdapterForOfferDetails adapter;
	OffersTabFragment parent;
	RelativeLayout re_top_top;
	RelativeLayout re_top;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("onCreate", "homeonCreate");

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragmnet_offerlist,
				container, false);
		re_top=(RelativeLayout) v.findViewById(R.id.re_top);
		re_top_top=(RelativeLayout) v.findViewById(R.id.re_top_top);

		
		list=new ArrayList<>();
		loadarrlist();



		list_view_latest_offer=(PullToRefreshListView) v.findViewById(R.id.list_view_latest_offer);
		listviewforoffer=list_view_latest_offer.getRefreshableView();

//		list_view_latest_offer.setOnRefreshListener(new OnRefreshListener<ListView>() {
//			@Override
//			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				loadarrlist();
//			}
//		});


		listviewforoffer.setAdapter(adapter);
		listviewforoffer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				parent.StartSingleOfferFragment(list.get(position));
			}
		});
		listviewforoffer.setOnScrollListener(new OnScrollListener() {
			private int mLastFirstVisibleItem;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
				
				 if(mLastFirstVisibleItem<firstVisibleItem)
		            {
					// Toast.makeText(getActivity(), "up "+firstVisibleItem, 300).show();
		                Log.i("SCROLLING UP","TRUE");
		                if(firstVisibleItem!=0){
							Log.i("Not visible",""+ firstVisibleItem);
						
							
			               Handler handler=new Handler();
			       		handler.postDelayed(new Runnable() {

			       			@Override
			       			public void run() {
			       				re_top.setVisibility(View.GONE);
			       				}
			       		},300);
			       		handler.postDelayed(new Runnable() {

			       			@Override
			       			public void run() {
			       				int xValue = 0;
				                int yValue =- re_top.getHeight();
				               animate(re_top).x(0).y(yValue).setDuration(300);
			       				}
			       		},50);
			              }
		            }
		            if(mLastFirstVisibleItem>firstVisibleItem)
		            {
		            	 if((firstVisibleItem==1)||(firstVisibleItem==0)){
								Log.i("visible",""+ firstVisibleItem);
								re_top.setVisibility(View.VISIBLE);
								 animate(re_top).x(0).y(0);
							}
			              // Toast.makeText(getActivity(), "down "+firstVisibleItem, 300).show();
		            	
		            }
		            mLastFirstVisibleItem=firstVisibleItem;
		

			}
		});

		return v;
	}
	@Override
	public void onResume() {
		super.onResume();
		//		topBar=(RelativeLayout) findViewById(R.id.topBar);
		//		welcome_title=(TextView) findViewById(R.id.welcome_title);
		//		backbuttonoftab=(ImageView) findViewById(R.id.backbuttonoftab);
		//		((HomeActivity)getActivity()).topBar.setVisibility(View.GONE);

	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		//		((HomeActivity)getActivity()).topBar.setVisibility(View.VISIBLE);
		super.onPause();
	}


	public void loadarrlist(){
		OfferDetails offerDetails=new OfferDetails();
		list.add(offerDetails);
		String message="Iphone 5S Camera Samples Untouched IPhone 5s Camera Samples Photos Redmond Pie," +
				" download this wallpaper for free in HD resolution. Iphone 5S Camera Samples Untouched IPhone 5s" +
				" Camera Samples Photos Redmond Pie was posted in November 26, 2013 at 1:18 pm. This HD Wallpaper Iphone " +
				"5S Camera Samples Untouched IPhone 5s Camera Samples Photos Redmond Pie has viewed by 710 users. If you wanna have it" +
				" as yours, please click the wallpaper and you will go to page download, so you just choose the size above the wallpaper that" +
				" you want in Download Size, Click it and download the " +
				"Iphone 5S Camera Samples Untouched IPhone 5s Camera Samples Photos Redmond Pie wallpaper.";
		String url="http://www.lipberry.com//uploaded_content//article//1406.jpg";

		 offerDetails=new OfferDetails("Iphone 5S Camera Samples Untouched I", message, url, false);
		list.add(offerDetails);
		message="In all our business operations, we strive to play an integral role in society, sharing its values and working to create new value to help people around the world have healthier and more fulfilling lives."+

			"This is the essence of the Olympus management philosophy of Social IN. As a member of society, Olympus places the utmost importance on its relationships with individuals. Accordingly, Olympus has adopted Social IN, a management philosophy that embraces social values as an essential part of its business ideology."+

			"Olympus was founded with the declared purpose of manufacturing microscopes that would garner recognition in the global market. The impetus for this drive was a deep sense of duty to help advance medical development through the domestic manufacture of microscopes."+

			"Since then, Olympus confirmed its position as a camera manufacturer and developed gastrocameras. Following this achievement, Olympus made a number of dreams come true through the application of Opto-Digital Technology, an area of core competence that integrates longstanding optical technology and state-of-the-art digital technology."+

			"Your Vision, Our Future embodies our determination to realize dreams in a close bond with society while coexisting in harmony. Based on this corporate slogan, Olympus is pursuing innovation in order to become a company where all stakeholders, including shareholders, clients and employees, are proud of the Olympus brand.";
		url="http://www.lipberry.com//uploaded_content//article//1405.jpg";
		offerDetails=new OfferDetails("E-620: Sample Images | Digital SLR |", message, url, false);
		list.add(offerDetails);
		message="Beginning in 1934 as Japan's pioneering photographic film maker, Fujifilm has leveraged its imaging and information technology to become a global presence known for innovation in healthcare, graphic arts, optical devices, highly functional materials and other high-tech areas. Beginning in 1934 as Japan's pioneering photographic film maker, Fujifilm has leveraged its imaging and information technology to become a global presence known for innovation in healthcare, graphic arts, optical devices, highly functional materials and other high-tech areas.Beginning in 1934 as Japan's pioneering photographic film maker, Fujifilm has leveraged its imaging and information technology to become a global presence known for innovation in healthcare, graphic arts, optical devices, highly functional materials and other high-tech areas.";
		url="http://www.lipberry.com//uploaded_content//article//1402.jpg";
		offerDetails=new OfferDetails("FUJIFILM X-S1 | Sample Images | Fujifilm", message, url, false);
		list.add(offerDetails);
		adapter=new CustomAdapterForOfferDetails(getActivity(), list);
		adapter.notifyDataSetChanged();
		//	list_view_latest_offer.onRefreshComplete();
	}

 
}
