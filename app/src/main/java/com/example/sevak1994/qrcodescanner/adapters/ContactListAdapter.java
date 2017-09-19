package com.example.sevak1994.qrcodescanner.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.interfaces.ActionModeListener;
import com.example.sevak1994.qrcodescanner.models.ContactInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sevak1994 on 9/17/2017.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactsViewHolder> implements View.OnLongClickListener, CompoundButton.OnCheckedChangeListener {

    static class ContactsViewHolder extends RecyclerView.ViewHolder {

        private ImageView pictureIV;
        private CheckBox checkBox;
        private TextView nameTV;
        private TextView jobTV;

        ContactsViewHolder(View itemView) {
            super(itemView);
            pictureIV = itemView.findViewById(R.id.profile_image);
            nameTV = itemView.findViewById(R.id.name);
            jobTV = itemView.findViewById(R.id.job);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

    private Context mContext;
    private List<ContactInfoModel> contactInfoModelList = new ArrayList<>();
    private ActionModeListener actionModeListener;

    public ContactListAdapter(Context mContext, List<ContactInfoModel> contactInfoModelList, ActionModeListener actionModeListener) {
        this.mContext = mContext;
        this.contactInfoModelList = contactInfoModelList;
        this.actionModeListener = actionModeListener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_info_row, parent, false);

        view.setOnLongClickListener(this);

        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, int position) {
        holder.nameTV.setText(contactInfoModelList.get(position).getName());
        holder.jobTV.setText(contactInfoModelList.get(position).getJob());

        holder.checkBox.setOnCheckedChangeListener(this);

        SimpleTarget simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.pictureIV.setImageBitmap(resource);
            }
        };

        Glide.with(mContext)
                .load(contactInfoModelList.get(position).getImageUrl())
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.default_photo)
                .into(simpleTarget);

        if (!((HomeActivity) mContext).isInActionMode()) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return contactInfoModelList.size();
    }

    @Override
    public boolean onLongClick(View view) {
        if (actionModeListener != null) {
            actionModeListener.inActionMode();
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.isPressed()) {
            if (compoundButton.isChecked() && actionModeListener != null) {
                actionModeListener.moreItemSelected();
            } else if (!compoundButton.isChecked() && actionModeListener != null) {
                actionModeListener.lessItemSelected();
            }
        }
    }
}
