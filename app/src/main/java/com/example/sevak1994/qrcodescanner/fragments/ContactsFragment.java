package com.example.sevak1994.qrcodescanner.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sevak1994.qrcodescanner.LooperThread;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.adapters.ContactListAdapter;
import com.example.sevak1994.qrcodescanner.adapters.ProfileListAdapter;
import com.example.sevak1994.qrcodescanner.interfaces.ActionModeListener;
import com.example.sevak1994.qrcodescanner.interfaces.ItemClickListener;
import com.example.sevak1994.qrcodescanner.models.ContactInfoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 9/7/2017.
 */

public class ContactsFragment extends Fragment implements ActionModeListener, ItemClickListener {

    private View fragmentRootView;
    private HomeActivity activity;
    private List<ContactInfoModel> contactInfoModelList = new ArrayList<>();
    private ContactListAdapter contactListAdapter;

    private ActionModeListener actionModeListener;
    private List<Integer> checkedItems = new ArrayList<>();

    private SnapHelper snapHelper = new PagerSnapHelper();
    private RecyclerView profilesListView;
    private ProfileListAdapter profileListAdapter;

    private LooperThread looperThread;

    public ContactsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        looperThread = new LooperThread("LooperThread");
        looperThread.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        return fragmentRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (HomeActivity) getActivity();

        activity.inNormalMode(false);
        activity.setToolbarTitle(getResources().getString(R.string.title_contacts));

        initRecyclerView();
        initProfilesListView();
    }

    @Override
    public void onItemClick(int position) {
        profilesListView.scrollToPosition(position);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showRevealAnimation();
        } else {
            profilesListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCloseBtnClick() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            hideRevealAnimation();
        } else {
            profilesListView.setVisibility(View.INVISIBLE);
        }
    }

    public void setActionModeListener(ActionModeListener actionModeListener) {
        this.actionModeListener = actionModeListener;
    }

    private void initProfilesListView() {
        profilesListView = fragmentRootView.findViewById(R.id.profile_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        profilesListView.setLayoutManager(linearLayoutManager);
        profilesListView.setHasFixedSize(true);

        profileListAdapter = new ProfileListAdapter(activity, contactInfoModelList, this, looperThread);
        profilesListView.setAdapter(profileListAdapter);

        snapHelper.attachToRecyclerView(profilesListView);
    }

    private void initRecyclerView() {
        RecyclerView contactsListView = fragmentRootView.findViewById(R.id.contacts_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        contactsListView.setLayoutManager(linearLayoutManager);
        contactsListView.setHasFixedSize(true);

        generateFakeData();
        contactListAdapter = new ContactListAdapter(activity, contactInfoModelList, this, this);
        contactsListView.setAdapter(contactListAdapter);
    }

    private void showRevealAnimation() {
        int cx = profilesListView.getWidth() / 2;
        int cy = profilesListView.getHeight() / 2;

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(profilesListView, cx, cy, 0, finalRadius);

        profilesListView.setVisibility(View.VISIBLE);
        anim.setDuration(300).start();
    }

    private void hideRevealAnimation() {
        int cx = profilesListView.getWidth() / 2;
        int cy = profilesListView.getHeight() / 2;

        float initialRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(profilesListView, cx, cy, initialRadius, 0);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                profilesListView.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(200).start();
    }


    private void generateFakeData() {
        ContactInfoModel contactInfoModel = new ContactInfoModel("Sevag Mardirossian", "Android developer at GG", R.drawable.profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Gevorgyan Rob", "Video Production Expert at Zangi", R.drawable.rob_profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Parurian Mher", "Full Stack Developer at Zangi", R.drawable.mher_profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Adamyan Max", "IOS Developer at GG", R.drawable.max_profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Avanesyan Hayk", "Android developer at GG", R.drawable.hayk_profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Sevag Mardirossian", "Android developer at GG", R.drawable.profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Sevag Mardirossian", "Android developer at GG", R.drawable.profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Sevag Mardirossian", "Android developer at GG", R.drawable.profile);
        contactInfoModelList.add(contactInfoModel);

        contactInfoModel = new ContactInfoModel("Sevag Mardirossian", "Android developer at GG", R.drawable.profile);
        contactInfoModelList.add(contactInfoModel);
    }

    @Override
    public void inActionMode() {
        if (actionModeListener != null) {
            actionModeListener.inActionMode();
        }
        contactListAdapter.notifyDataSetChanged();
    }

    @Override
    public void inNormalMode(boolean deletedItems) {
        if (actionModeListener != null) {
            actionModeListener.inNormalMode(deletedItems);
        }
        checkedItems.clear();
    }

    @Override
    public void moreItemSelected(int position) {
        if (actionModeListener != null) {
            actionModeListener.moreItemSelected(position);
        }
        checkedItems.add(position);
    }

    @Override
    public void lessItemSelected(int position) {
        if (actionModeListener != null) {
            actionModeListener.lessItemSelected(position);
        }
        checkedItems.remove(Integer.valueOf(position));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                inNormalMode(false);
                contactListAdapter.notifyDataSetChanged();
                break;
            case R.id.delete:
                if (checkedItems.size() > 0) {
                    removeCheckedDataFromAdapter();
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.no_selected_items), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeCheckedDataFromAdapter() {
        Collections.sort(checkedItems);
        for (int i = checkedItems.size() - 1; i >= 0; --i) {
            contactInfoModelList.remove(contactInfoModelList.get(checkedItems.get(i)));
        }
        contactListAdapter.notifyDataSetChanged();
        profileListAdapter.notifyDataSetChanged();

        inNormalMode(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        looperThread.nonUIHandler.getLooper().quit();
    }
}
