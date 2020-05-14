package com.ingenicomovement.rvexpand;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;

    public RecyclerAdapter(Context context, Animation animationUp, Animation animationDown) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
    }


    @NonNull
    @Override
    public ReyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);

        return new ReyclerViewHolder(item);
       // return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReyclerViewHolder holder, int position) {

        if (position % 3 == 0) {
            holder.image.setImageResource(R.drawable.ic_child_care_black_24dp);
        } else if (position % 3 == 1) {
            holder.image.setImageResource(R.drawable.ic_child_care_black_24dp);
        } else {
            holder.image.setImageResource(R.drawable.ic_child_care_black_24dp);
        }

        holder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contentLayout.isShown()) {
                    holder.contentLayout.startAnimation(animationUp);

                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            holder.contentLayout.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();

                    holder.showMore.setText(context.getString(R.string.show));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0);
                } else {
                    holder.contentLayout.setVisibility(View.VISIBLE);
                    holder.contentLayout.startAnimation(animationDown);

                    holder.showMore.setText(context.getString(R.string.hide));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_black_24dp, 0);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ReyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView showMore;
        private TextView contentLayout;


        public ReyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            contentLayout = (TextView) itemView.findViewById(R.id.content);
            showMore = (TextView) itemView.findViewById(R.id.show_more);
        }
    }
}
