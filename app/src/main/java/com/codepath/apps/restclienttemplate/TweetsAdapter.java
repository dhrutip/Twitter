package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;
    // pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // for each row, inflate the layout
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        // get the data at position
        Tweet tweet = tweets.get(position);
        // bind the tweet with the view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvActualName;
        TextView tvTimeSince;
        TextView tvTwitterHandle;
        ImageView ivUrl;
        TextView tvLikes;
        TextView tvRetweets;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView); // one row in the recycler view
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvActualName = itemView.findViewById(R.id.tvActualName);
            tvTimeSince = itemView.findViewById(R.id.tvTimeSince);
            tvTwitterHandle = itemView.findViewById(R.id.tvTwitterHandle);
            ivUrl = itemView.findViewById(R.id.ivUrl);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvRetweets = itemView.findViewById(R.id.tvRetweets);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvTwitterHandle.setText("@" + tweet.user.twitterHandle);
            tvTimeSince.setText(tweet.timeSince);
            tvActualName.setText(tweet.user.actualName);
            tvRetweets.setText(String.valueOf(tweet.retweets));
            tvLikes.setText(String.valueOf(tweet.likes));
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .transform(new CircleCrop())
                    .into(ivProfileImage);
            ivUrl.setVisibility(View.INVISIBLE);
            if (ivUrl != null) {
                ivUrl.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(tweet.imageUrl)
                        .into(ivUrl);
            }
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }
}
