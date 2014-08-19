package com.teckfiesta.horn.adapter;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.teckfiesta.horn.R;
import com.teckfiesta.horn.model.OfferDetails;



import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.text.Html;


import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapterForOfferDetails extends BaseAdapter {
	Activity activity;
	ArrayList<OfferDetails> list;
	public CustomAdapterForOfferDetails(Activity activity,
			ArrayList<OfferDetails> list) {
		super();
		this.activity=activity;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	private class ViewHolder {
		ImageView img_pic;
		TextView tv_title;
		TextView tv_message;
		View ve_top;
		RelativeLayout re_root_view;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.offer_details,
					null);
			holder = new ViewHolder();
			holder.img_pic=(ImageView) convertView.findViewById(R.id.img_pic);
			holder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_message=(TextView) convertView.findViewById(R.id.tv_message);
			holder.ve_top=convertView.findViewById(R.id.ve_top);
			holder.re_root_view=(RelativeLayout) convertView.findViewById(R.id.re_root_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position==0){
			holder.re_root_view.setVisibility(View.GONE);
			holder.ve_top.setVisibility(View.VISIBLE);

		}
		else{
			holder.re_root_view.setVisibility(View.VISIBLE);
			holder.ve_top.setVisibility(View.GONE);
			holder.tv_title.setText(list.get(position).getTitle());
			holder.tv_message.setText(list.get(position).getMessage());
			ImageLoader.getInstance().displayImage(list.get(position).getImg_url(),holder.img_pic);
			
		}
		
		return convertView;
	}

}
