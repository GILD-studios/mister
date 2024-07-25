package com.gildstudios.mister;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import static com.gildstudios.mister.FirstFragment.team1;
import static com.gildstudios.mister.FirstFragment.team2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class SecondFragment extends AwareFragment {

    private final int RC_sharing = 532;
    private ContextWrapper mCw;
    private File mBitmapFh;
    ImageView shareImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCw = new ContextWrapper(
                MainActivity.getInstance()
                        .getApplicationContext());

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.formation, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        team1.sort(new Player.PlayerRoleComparator());
        team2.sort(new Player.PlayerRoleComparator());

        shareImage = view.findViewById(R.id.shareImage);

        final TextView p1 = view.findViewById(R.id.textView2);
        final TextView p2 = view.findViewById(R.id.textView3);
        final TextView p3 = view.findViewById(R.id.textView4);
        final TextView p4 = view.findViewById(R.id.textView5);
        final TextView p5 = view.findViewById(R.id.textView6);
        final TextView p6 = view.findViewById(R.id.textView7);
        final TextView p7 = view.findViewById(R.id.textView8);
        final TextView p8 = view.findViewById(R.id.textView9);
        final TextView p9 = view.findViewById(R.id.textView10);
        final TextView p10 = view.findViewById(R.id.textView11);
        final TextView p11 = view.findViewById(R.id.textView12);
        final TextView p12 = view.findViewById(R.id.textView13);
        final TextView p13 = view.findViewById(R.id.calcio7a);
        final TextView p14 = view.findViewById(R.id.calcio7b);

        final ImageView blue6 = view.findViewById(R.id.imageView6);
        final ImageView blue7 = view.findViewById(R.id.imageView7);
        final ImageView black6 = view.findViewById(R.id.imageView13);
        final ImageView black7 = view.findViewById(R.id.imageView14);

        shareImage.setOnClickListener(v -> {
            shareTeams();
        });

        p1.setVisibility(View.INVISIBLE);
        p7.setVisibility(View.INVISIBLE);
        p13.setVisibility(View.INVISIBLE);
        p14.setVisibility(View.INVISIBLE);
        blue6.setVisibility(View.INVISIBLE);
        blue7.setVisibility(View.INVISIBLE);
        black6.setVisibility(View.INVISIBLE);
        black7.setVisibility(View.INVISIBLE);

        p2.setText(team1.get(4).getName());
        p3.setText(team1.get(3).getName());
        p4.setText(team1.get(2).getName());
        p5.setText(team1.get(1).getName());
        p6.setText(team1.get(0).getName());
        p8.setText(team2.get(4).getName());
        p9.setText(team2.get(3).getName());
        p10.setText(team2.get(2).getName());
        p11.setText(team2.get(1).getName());
        p12.setText(team2.get(0).getName());
        if (FirstFragment.format == 6) {
            p1.setText(team1.get(5).getName());
            p7.setText(team2.get(5).getName());
            p1.setVisibility(View.VISIBLE);
            p7.setVisibility(View.VISIBLE);
            blue6.setVisibility(View.VISIBLE);
            black6.setVisibility(View.VISIBLE);

        } else if (FirstFragment.format == 7) {
            p1.setText(team1.get(6).getName());
            p7.setText(team2.get(6).getName());
            p13.setText(team1.get(5).getName());
            p14.setText(team2.get(5).getName());
            p1.setVisibility(View.VISIBLE);
            p7.setVisibility(View.VISIBLE);
            p13.setVisibility(View.VISIBLE);
            p14.setVisibility(View.VISIBLE);
            blue6.setVisibility(View.VISIBLE);
            blue7.setVisibility(View.VISIBLE);
            black6.setVisibility(View.VISIBLE);
            black7.setVisibility(View.VISIBLE);
        }

    }



    private Bitmap takeScreenshot() {
        return takeScreenshot(false);
    }

    private Bitmap takeScreenshot(boolean statusBar) {
        if (!activityExists())
            return null;

        FragmentActivity fragmentActivity = getActivity();

        View fragmentView = fragmentActivity.getWindow().getDecorView();

        fragmentView.setDrawingCacheEnabled(true);
        fragmentView.buildDrawingCache();

        Bitmap viewBitmap = fragmentView.getDrawingCache();

        if (statusBar)
            return viewBitmap;

        // Remove status bar from the screenshot
        Rect contentFrame = new Rect();


        fragmentActivity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(contentFrame);

        int statusBarH = contentFrame.top;

        // Extract screen sizes
        DisplayMetrics displayMetrics = new DisplayMetrics();
        fragmentActivity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);

        int screenW = displayMetrics.widthPixels;
        int screenH = displayMetrics.heightPixels;

        viewBitmap = Bitmap.createBitmap(viewBitmap, 0,
                statusBarH, screenW, screenH);

        fragmentView.setDrawingCacheEnabled(false);

        fragmentView.destroyDrawingCache();

        return viewBitmap;
    }

    private File saveScreenshot(Bitmap viewBitmap) {
        File appDirectory = mCw.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES);

        String bitmapId = UUID.randomUUID().toString();

        File imageFh = new File(appDirectory,
                bitmapId + ".png");

        if (imageFh.exists())
            return null;

        FileOutputStream fOut;

        try {
            fOut = new FileOutputStream(imageFh);
            viewBitmap.compress(Bitmap.CompressFormat.PNG,
                    100, fOut);

            fOut.flush();
            fOut.close();

            return imageFh;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean deleteScreenshot() {
        if (!activityExists()) {
            return false;
        }

        if (mBitmapFh == null)
            return false;

        if (mBitmapFh.exists()) {
            return mBitmapFh.delete();
        }

        return false;
    }

    private void shareTeams() {
        shareImage.setVisibility(View.INVISIBLE);
        Bitmap viewBitmap = takeScreenshot();

        if (viewBitmap == null) {
            showUnknownError();
            return;
        }

        mBitmapFh = saveScreenshot(viewBitmap);

        if (mBitmapFh == null) {
            showUnknownError();
            return;
        }

        Uri uriToImage = FileProvider.getUriForFile(
                getActivity(),
                BuildConfig.APPLICATION_ID
                        + ".provider",
                mBitmapFh);

        Intent shareIntent = new Intent();

        String mimeType = "image/png";
        String[] mimeTypeArray = new String[] { mimeType };

        shareIntent.setType(mimeType);

        // Add the URI as a ClipData
        shareIntent.setClipData(new ClipData(
                getString(R.string.share_message)
                        + getString(R.string.app_name),
                mimeTypeArray,
                new ClipData.Item(uriToImage)
        ));

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        String shareBody = getString(R.string.share_message)
                + ' ' + getString(R.string.app_name) + " ⚽: " + getString(R.string.play_store_link);

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.extra_subject));
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);

        shareIntent.setType("image/png");

        startActivityForResult(Intent.createChooser(shareIntent,
                getResources().getText(R.string.send_to)), RC_sharing);

        shareImage.setVisibility(View.VISIBLE);
    }

}

