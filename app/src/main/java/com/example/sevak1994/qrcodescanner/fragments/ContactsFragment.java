package com.example.sevak1994.qrcodescanner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.adapters.ContactListAdapter;
import com.example.sevak1994.qrcodescanner.interfaces.ActionModeListener;
import com.example.sevak1994.qrcodescanner.models.ContactInfoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 9/7/2017.
 */

public class ContactsFragment extends Fragment implements ActionModeListener {

    private View fragmentRootView;
    private HomeActivity activity;
    private RecyclerView contactsListView;
    private List<ContactInfoModel> contactInfoModelList = new ArrayList<>();
    private ContactListAdapter contactListAdapter;

    private ActionModeListener actionModeListener;
    private List<Integer> checkedItems = new ArrayList<>();

    public ContactsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        activity.inNormalMode();
        activity.setToolbarTitle(getResources().getString(R.string.title_contacts));
        initRecyclerView();
    }

    public void setActionModeListener(ActionModeListener actionModeListener) {
        this.actionModeListener = actionModeListener;
    }

    private void initRecyclerView() {
        contactsListView = fragmentRootView.findViewById(R.id.contacts_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        contactsListView.setLayoutManager(linearLayoutManager);
        contactsListView.setHasFixedSize(true);

        generateFakeData();
        contactListAdapter = new ContactListAdapter(activity, contactInfoModelList, this);
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

    @Override
    public void inActionMode() {
        if (actionModeListener != null) {
            actionModeListener.inActionMode();
        }
        contactListAdapter.notifyDataSetChanged();
    }

    @Override
    public void inNormalMode() {
        if (actionModeListener != null) {
            actionModeListener.inNormalMode();
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
                inNormalMode();
                contactListAdapter.notifyDataSetChanged();
                break;
            case R.id.delete:
                removeCheckedDataFromAdapter();
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
        inNormalMode();
    }
}
