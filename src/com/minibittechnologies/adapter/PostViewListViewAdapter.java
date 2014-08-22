package com.minibittechnologies.adapter;

import java.text.Format;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minibittechnologies.R;
import com.minibittechnologies.model.Post;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class PostViewListViewAdapter extends ParseQueryAdapter<Post> {

	// private final String[] mPostsObjectIds;
	private static Context mContext;

	public PostViewListViewAdapter(final Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Post>() {

			@Override
			public ParseQuery<Post> create() {
				mContext = context;
				ParseQuery<Post> query = Post.getQuery();
				// query.include("User");

				// query.whereContainedIn("objectId",
				// Arrays.asList(postsObjectIds));
				return query;
			}
		});

	}

	@Override
	public View getItemView(Post post, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.row_post, null);
		}

		super.getItemView(post, v, parent);

		ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.iv_post_image);
		ParseFile imageFile = post.getParseFile("image");
		if (imageFile != null) {
			// Log.e(">>>>>>>>", "todoImage width = " + todoImage.getWidth());
			todoImage.setParseFile(imageFile);
			todoImage.loadInBackground(new GetDataCallback() {

				@Override
				public void done(byte[] data, ParseException e) {
					// TODO Auto-generated method stub

				}
			});
		}

		final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
		final TextView tvContent = (TextView) v.findViewById(R.id.tv_content);

		tvTitle.setText(post.getTitle());
		tvContent.setText(post.getContent());

		final TextView tvExpired = (TextView) v.findViewById(R.id.tv_expired);
		long currentTimeInMillis = System.currentTimeMillis();
		long expiredTime = post.getExpired().getTime();
		if (expiredTime > currentTimeInMillis) {
			tvExpired.setVisibility(View.GONE);
		} else {
			tvExpired.setVisibility(View.VISIBLE);
		}

		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(post.getExpired());
		Log.e(">>>>>>>", "date in Date = " + post.getExpired() + "   AND date in String = " + date);

		return v;
	}
}
