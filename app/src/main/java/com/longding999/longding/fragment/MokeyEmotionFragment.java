package com.longding999.longding.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.longding999.longding.R;
import com.longding999.longding.listener.GlobalOnItemClickManager;
import com.squareup.picasso.Picasso;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/29 13:38
 * Desc:
 * *****************************************************************
 */
public class MokeyEmotionFragment extends Fragment {


    private Context mContext;
    private int page;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        Bundle bundle = getArguments();
        page = bundle.getInt("page");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.emotion_gird_mokey, null);
        GridView gridView = (GridView) view.findViewById(R.id.grid);
        MokeyEmotionAdapter adapter = new MokeyEmotionAdapter(page,mContext);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(GlobalOnItemClickManager.getInstance().getOnItemClickListener(1,page));
        return view;
    }


    public class MokeyEmotionAdapter extends BaseAdapter {
        private Context mContext;
        private int page;

        public MokeyEmotionAdapter(int page, Context mContext) {
            this.mContext = mContext;
            this.page = page;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.emotion_gird_item_mokey, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String path = "file:///android_asset/ema" + (page * 8 + position) + ".gif";
            Picasso.with(mContext).load(path).into(holder.getImageView());
            return convertView;
        }


        class ViewHolder {
            private ImageView imageView;
            private View view;

            public ViewHolder(View view) {
                this.view = view;
            }

            public ImageView getImageView() {
                if (imageView == null) {
                    imageView = (ImageView) view.findViewById(R.id.image);
                }
                return imageView;
            }
        }
    }


}
