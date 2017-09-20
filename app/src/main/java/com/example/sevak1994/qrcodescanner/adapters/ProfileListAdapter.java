package com.example.sevak1994.qrcodescanner.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.interfaces.ItemClickListener;
import com.example.sevak1994.qrcodescanner.models.ContactInfoModel;

import java.util.List;

/**
 * Created by Sevak1994 on 9/21/2017.
 */

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfilesViewHolder> {

    public static class ProfilesViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileIV;
        private ImageView closeIV;
        private TextView nameTV;
        private TextView jobTV;

        public ProfilesViewHolder(View itemView) {
            super(itemView);
            profileIV = itemView.findViewById(R.id.profile_image);
            closeIV = itemView.findViewById(R.id.profile_close_iv);
            nameTV = itemView.findViewById(R.id.name_tv);
            jobTV = itemView.findViewById(R.id.job_tv);
        }
    }

    private Context mContext;
    private List<ContactInfoModel> contactInfoModelList;
    private ItemClickListener itemClickListener;

    public ProfileListAdapter(Context mContext, List<ContactInfoModel> contactInfoModelList, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.contactInfoModelList = contactInfoModelList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ProfilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_item_row, parent, false);

        view.findViewById(R.id.profile_close_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onCloseBtnClick();
                }
            }
        });
        return new ProfilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProfilesViewHolder holder, int position) {
        ContactInfoModel contactInfoModel = contactInfoModelList.get(position);

        holder.nameTV.setText(contactInfoModel.getName());
        holder.jobTV.setText(contactInfoModel.getJob());
        holder.closeIV.setVisibility(View.VISIBLE);

        SimpleTarget simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.profileIV.setImageBitmap(resource);
            }
        };

        Glide.with(mContext)
                .load(contactInfoModel.getImageUrl())
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.default_photo)
                .into(simpleTarget);
    }

    @Override
    public int getItemCount() {
        return contactInfoModelList.size();
    }
}
