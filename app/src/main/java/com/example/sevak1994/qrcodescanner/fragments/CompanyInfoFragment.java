package com.example.sevak1994.qrcodescanner.fragments;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

public class CompanyInfoFragment extends Fragment implements BackKeyListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private OnFragmentInteractionListener mListener;

    FragmentActivity activity;

    public CompanyInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_info, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = getActivity();
        if (((AppCompatActivity) activity).getSupportActionBar() != null) {
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ((HomeActivity) activity).setToolbarTitle(getResources().getString(R.string.company_info));
        ((HomeActivity) activity).setBackKeyListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_info_edit_menu, menu);

        menu.findItem(R.id.edit_contacts).getIcon().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_contacts:
                //FragmentManager.getInstance().startContactInfoEditFragment(activity, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case android.R.id.home:
                FragmentManager.getInstance().startSettingsFragment(activity, R.anim.enter_from_right, R.anim.exit_to_left);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager.getInstance().startSettingsFragment(activity, R.anim.enter_from_right, R.anim.exit_to_left);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
