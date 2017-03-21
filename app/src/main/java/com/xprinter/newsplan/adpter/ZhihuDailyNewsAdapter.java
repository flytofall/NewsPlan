package com.xprinter.newsplan.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xprinter.newsplan.R;
import com.xprinter.newsplan.bean.ZhihuDailyNews;
import com.xprinter.newsplan.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Charlie on 2017/3/15.
 * 知乎日报适配器
 *
 */

public class ZhihuDailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private  final LayoutInflater inflater;
    private List<ZhihuDailyNews.Question> list=new ArrayList<ZhihuDailyNews.Question>();
    private OnRecyclerViewOnClickListener mlistener;

    // 文字 + 图片
    private static final int TYPE_NORMAL = 0;
    // footer，加载更多
    private static final int TYPE_FOOTER = 1;

    public ZhihuDailyNewsAdapter(Context context, List<ZhihuDailyNews.Question> list) {
        this.context = context;
        this.list = list;
        this.inflater= LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //根据不同的view布局
        switch (viewType){
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout,parent,false),mlistener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //对不同的viewholder处理
        if(holder instanceof NormalViewHolder){
            ZhihuDailyNews.Question item=list.get(position);
            if (item.getImages().get(0)==null){
                ((NormalViewHolder)holder).itemImg.setImageResource(R.drawable.placeholder);
            }else {
                Glide.with(context).load(item.getImages().get(0))
                        .asBitmap().placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((NormalViewHolder)holder).itemImg);
                ((NormalViewHolder) holder).tvLatestNewsTitle.setText(item.getTitle());
            }
        }

    }

    @Override
    public int getItemCount() {
        //因为有footer返回值需要+1
        return list.size()+1;
    }
    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mlistener=listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private ImageView itemImg;
        private TextView tvLatestNewsTitle;
        OnRecyclerViewOnClickListener listener;



        public NormalViewHolder(View itemView,OnRecyclerViewOnClickListener listener) {
            super(itemView);
            itemImg= (ImageView) itemView.findViewById(R.id.imageViewCover);
            tvLatestNewsTitle= (TextView) itemView.findViewById(R.id.textViewTitle);
            this.listener=listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (listener!=null){
                listener.onItemClick(view,getLayoutPosition());
            }
        }
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

    }
}
