package com.example.sevak1994.qrcodescanner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.adapters.ContactListAdapter;
import com.example.sevak1994.qrcodescanner.models.ContactInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/7/2017.
 */

public class ContactsFragment extends Fragment {

    private View fragmentRootView;
    private HomeActivity activity;
    private RecyclerView contactsListView;
    private List<ContactInfoModel> contactInfoModelList = new ArrayList<>();

    public ContactsFragment() {
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

        activity.setToolbarTitle(getResources().getString(R.string.title_contacts));
        initRecyclerView();
    }

    private void initRecyclerView() {
        contactsListView = fragmentRootView.findViewById(R.id.contacts_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        contactsListView.setLayoutManager(linearLayoutManager);
        contactsListView.setHasFixedSize(true);

        generateFakeData();
        ContactListAdapter contactListAdapter = new ContactListAdapter(activity, contactInfoModelList);
        contactsListView.setAdapter(contactListAdapter);
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
}
