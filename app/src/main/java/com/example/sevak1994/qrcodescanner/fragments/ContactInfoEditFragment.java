package com.example.sevak1994.qrcodescanner.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.BitmapUtils;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sevak1994 on 9/17/2017.
 */

public class ContactInfoEditFragment extends Fragment implements BackKeyListener, View.OnClickListener {

    private static final int REQUEST_IMAGE_CAR_CAPTURE = 1;
    private static final int FROM_GALLERY = 0;
    private static final int FROM_CAMERA = 1;

    private View fragmentRootView;
    private FragmentActivity activity;

    private LinearLayout addPhotoLayout;
    private Bitmap photoBitmap;

    public ContactInfoEditFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_contact_info_edit, container, false);

        addPhotoLayout = fragmentRootView.findViewById(R.id.add_photo_layout);
        addPhotoLayout.setOnClickListener(this);

        return fragmentRootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_photo_layout:
                chooseAndGetPhoto();
                break;
        }
    }

    private void chooseAndGetPhoto() {
        final CharSequence[] options = {"Gallery", "Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                switch (position) {
                    case FROM_GALLERY:
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent, REQUEST_IMAGE_CAR_CAPTURE);
                        break;
                    case FROM_CAMERA:
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAR_CAPTURE);
                        break;
                }
            }
        });

        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAR_CAPTURE:
                    photoBitmap = null;

                    if (data != null) {
                        try {
                            if (data.getExtras() != null) {
                                photoBitmap = (Bitmap) data.getExtras().get("data");
                            }

                            if (photoBitmap == null) {
                                Uri imageUri = data.getData();
                                photoBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
                            }

                            if (photoBitmap == null) {
                                InputStream inputStream = activity.getContentResolver().openInputStream(data.getData());
                                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                                photoBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                            }

                            if (photoBitmap == null) {
                                return;
                            }

                            photoBitmap = BitmapUtils.resizeBitmap(photoBitmap, 250);

                            ((ImageView) fragmentRootView.findViewById(R.id.profile_image)).setImageBitmap(photoBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = getActivity();
        if (((AppCompatActivity) activity).getSupportActionBar() != null) {
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ((HomeActivity) activity).setBackKeyListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_info_done_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                //TODO make server request
                break;
            case android.R.id.home:
                FragmentManager.getInstance().startContactInfoFragment(activity, R.anim.enter_from_right, R.anim.exit_to_left);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager.getInstance().startContactInfoFragment(activity, R.anim.enter_from_right, R.anim.exit_to_left);
    }
}
