package com.minibittechnologies.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.minibittechnologies.MainActivity;
import com.minibittechnologies.R;
import com.minibittechnologies.activity.HomeActivity;
import com.minibittechnologies.adapter.CustomAdapterForOfferDetails;
import com.minibittechnologies.model.OfferDetails;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("NewApi")
public class FragmentSingleOffer extends Fragment {
	
	OfferDetails singleofferDetails;
	OffersTabFragment parent;
	TextView tv_title,tv_message;
	ImageView img_pic;
	ImageView backbuttonoftab;
	TextView welcome_title;
	RelativeLayout topBar;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	public FragmentSingleOffer(OfferDetails singleofferDetails){
		this.singleofferDetails=singleofferDetails;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_single_offer,
				container, false);
		tv_title=(TextView) v.findViewById(R.id.tv_title);
		tv_message=(TextView) v.findViewById(R.id.tv_message);
		img_pic=(ImageView) v.findViewById(R.id.img_pic);
		topBar=(RelativeLayout) v.findViewById(R.id.topBar);
		welcome_title=(TextView) v.findViewById(R.id.welcome_title);
		backbuttonoftab=(ImageView) v.findViewById(R.id.backbuttonoftab);
		backbuttonoftab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				parent.onBackPressed();
			}
		});
		welcome_title.setText("Post Details");
		tv_title.setText(singleofferDetails.getTitle());
		tv_message.setText(singleofferDetails.getMessage());
		ImageLoader.getInstance().displayImage(singleofferDetails.getImg_url(),img_pic);
		return v;
	}
	public void onResume() {
		super.onResume();
//		topBar=(RelativeLayout) findViewById(R.id.topBar);
//		welcome_title=(TextView) findViewById(R.id.welcome_title);
//		backbuttonoftab=(ImageView) findViewById(R.id.backbuttonoftab);
//		((HomeActivity)getActivity()).backbuttonoftab.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				parent.onBackPressed();
//			}
//		});
//		((HomeActivity)getActivity()).welcome_title.setText("Post Details");

	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
		super.onPause();
//		((HomeActivity)getActivity()).welcome_title.setText("Gebriel Horn");
	}
	
}
