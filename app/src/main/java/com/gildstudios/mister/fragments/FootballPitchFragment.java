package com.gildstudios.mister.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import com.gildstudios.mister.BuildConfig;
import com.gildstudios.mister.MisterApplication;
import com.gildstudios.mister.R;
import com.gildstudios.mister.abstracts.AwareFragment;
import com.gildstudios.mister.enums.Position;
import com.gildstudios.mister.models.Player;
import com.gildstudios.mister.models.Team;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FootballPitchFragment extends AwareFragment {

    private final int RC_sharing = 532;

    private int n;
    private double alpha;
    private double mBalance;
    private ArrayList<Team> mTeams;

    private ContextWrapper mCw;
    private Toolbar mToolbar;
    private File mBitmapFh;

    ArrayList<ImageView> ivShirts;
    ArrayList<TextView> tvNames;
    ArrayList<TextView> tvRatings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCw = new ContextWrapper(
                MisterApplication.getInstance()
                        .getApplicationContext());

        mBalance = -1.0;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState ) {
        View fragmentView = onPersistentView(inflater, viewGroup,
                R.layout.fragment_football_pitch);

        setHasOptionsMenu(true);
        return fragmentView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!activityExists())
            return;

        // Setup the toolbar
        setupToolbar(view);

        if (mViewExisted)
            return;

        Bundle bundle = getArguments();

        if (bundle == null) {
            showUnknownError();
            getActivity().onBackPressed();
        }

        try {
            mBalance = bundle.getDouble("balance");
            getActivity().invalidateOptionsMenu();

            n = bundle.getInt("n");
            alpha = bundle.getDouble("alpha");

            JSONArray teamsJson = new JSONArray(
                    bundle.getString("teams"));

            mTeams = Team.fromJson(teamsJson, alpha);
        } catch (Exception e) {
            showUnknownError();
        }

        reference_tvNames(view);
        reference_tvRatings(view);
        reference_ivShirts(view);

        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_football_pitch, menu);

        MenuItem itemBalance    = menu.findItem(R.id.no_action_balance);
        MenuItem itemShowRating = menu.findItem(R.id.action_show_rating);
        MenuItem itemHideRating = menu.findItem(R.id.action_hide_rating);
        MenuItem itemShare      = menu.findItem(R.id.action_share);

        if (mBalance != -1.0) {
            itemBalance.setTitle(
                    getString(R.string.no_action_balance,
                              mBalance*100));

            itemBalance.setVisible(true);

        }

        itemShowRating.setOnMenuItemClickListener(menuItem -> {
            showRatingsWidgets();

            itemShowRating.setVisible(false);
            itemHideRating.setVisible(true);

            return true;
        });

        itemHideRating.setOnMenuItemClickListener(menuItem -> {
            hideRatingsWidgets();

            itemHideRating.setVisible(false);
            itemShowRating.setVisible(true);

            return true;
        });

        itemShare.setOnMenuItemClickListener(menuItem -> {
            shareTeams();
            return true;
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode,
                                  @Nullable Intent _noData) {
        if (resultCode == Activity.RESULT_OK
                && requestCode == RC_sharing) {
            if (!deleteScreenshot())
                showUnknownError();
        }
    }

    private void reference_tvNames(@NonNull View view) {
        tvNames = new ArrayList<>();

        tvNames.add(view.findViewById(R.id.tvPlayer_0_0));
        tvNames.add(view.findViewById(R.id.tvPlayer_0_1));
        tvNames.add(view.findViewById(R.id.tvPlayer_0_2));
        tvNames.add(view.findViewById(R.id.tvPlayer_0_3));
        tvNames.add(view.findViewById(R.id.tvPlayer_0_4));
        tvNames.add(view.findViewById(R.id.tvPlayer_0_5));
        tvNames.add(view.findViewById(R.id.tvPlayer_0_6));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_0));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_1));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_2));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_3));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_4));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_5));
        tvNames.add(view.findViewById(R.id.tvPlayer_1_6));
    }

    private void reference_tvRatings(@NonNull View view) {
        tvRatings = new ArrayList<>();

        tvRatings.add(view.findViewById(R.id.tvRating_0_0));
        tvRatings.add(view.findViewById(R.id.tvRating_0_1));
        tvRatings.add(view.findViewById(R.id.tvRating_0_2));
        tvRatings.add(view.findViewById(R.id.tvRating_0_3));
        tvRatings.add(view.findViewById(R.id.tvRating_0_4));
        tvRatings.add(view.findViewById(R.id.tvRating_0_5));
        tvRatings.add(view.findViewById(R.id.tvRating_0_6));
        tvRatings.add(view.findViewById(R.id.tvRating_1_0));
        tvRatings.add(view.findViewById(R.id.tvRating_1_1));
        tvRatings.add(view.findViewById(R.id.tvRating_1_2));
        tvRatings.add(view.findViewById(R.id.tvRating_1_3));
        tvRatings.add(view.findViewById(R.id.tvRating_1_4));
        tvRatings.add(view.findViewById(R.id.tvRating_1_5));
        tvRatings.add(view.findViewById(R.id.tvRating_1_6));
    }

    private void reference_ivShirts(@NonNull View view) {
        ivShirts = new ArrayList<>();

        ivShirts.add(view.findViewById(R.id.ivPlayer_0_0));
        ivShirts.add(view.findViewById(R.id.ivPlayer_0_1));
        ivShirts.add(view.findViewById(R.id.ivPlayer_0_2));
        ivShirts.add(view.findViewById(R.id.ivPlayer_0_3));
        ivShirts.add(view.findViewById(R.id.ivPlayer_0_4));
        ivShirts.add(view.findViewById(R.id.ivPlayer_0_5));
        ivShirts.add(view.findViewById(R.id.ivPlayer_0_6));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_0));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_1));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_2));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_3));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_4));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_5));
        ivShirts.add(view.findViewById(R.id.ivPlayer_1_6));
    }

    private void setupToolbar(View parentView) {
        mToolbar = parentView.findViewById(R.id.toolbar);

        if(!activityExists(
                AppCompatActivity.class))
            return;

        if(!mViewExisted) {
            ((AppCompatActivity) getActivity())
                    .setSupportActionBar(mToolbar);

            mToolbar.setNavigationIcon(R.drawable.ic_menu_back);

            ((AppCompatActivity) getActivity())
                    .getSupportActionBar()
                    .setTitle(getString(R.string.app_name));

            mToolbar.setTitleTextColor(Color.WHITE);
        }

        mToolbar.setNavigationOnClickListener(v ->
                 getActivity().onBackPressed());
    }

    private void updateUI() {
        int[] goneIdxs = getGoneWidgets();
        updateWidgets(goneIdxs);
    }

    private int[] getGoneWidgets() {
        if (n == 5) {
            return new int[]{0, 6, 7, 13};
        } else if (n == 6) {
            return new int[]{0, 7};
        } else {
            return new int[]{};
        }
    }

    private void updateWidgets(int[] goneIdxs) {
        for (int i : goneIdxs) {
            tvNames.get(i).setVisibility(View.INVISIBLE);
            tvRatings.get(i).setVisibility(View.INVISIBLE);
            ivShirts.get(i).setVisibility(View.INVISIBLE);
        }

        displayPlayers(goneIdxs);
    }

    private void hideRatingsWidgets() {
        for (int i = 0; i < tvRatings.size(); i++) {
            tvRatings.get(i).setVisibility(View.INVISIBLE);
        }
    }

    private void showRatingsWidgets() {
        int[] goneIdxs = getGoneWidgets();

        for (int i = 0; i < tvRatings.size(); i++) {
            int _i = i;

            if (IntStream.of(goneIdxs)
                    .noneMatch(j -> j == _i))
                tvRatings.get(i).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Display all the players on the pitch team-by-team. For each team first identify all
     * the defenders, D, then all the midfielders, M, and finally all the forwards, F.
     */
    private void displayPlayers(int[] goneIdxs) {
        Set<Integer> goneIdxsSet = IntStream.of(goneIdxs)
                .boxed().collect(Collectors.toSet());

        int nTeams = mTeams.size();

        if (nTeams < 1) {
            showUnknownError();
            return;
        }

        for (int i = 0; i < nTeams; i++) {
            Set<Integer> teamIdxsSet = IntStream.range(7*i, 7*(i + 1))
                    .boxed().collect(Collectors.toSet());

            teamIdxsSet.removeAll(goneIdxsSet);

            if (teamIdxsSet.size() != n) {
                showUnknownError();
                return;
            }

            Set<Player> playersSet = new HashSet<>(mTeams.get(i).getPlayers());

            teamIdxsSet.forEach(j -> {
                TextView tvName = tvNames.get(j);
                TextView tvRating = tvRatings.get(j);

                Optional<Player> playerOpt = playersSet.stream()
                        .parallel().filter(p ->
                                p.position == Position.D)
                        .findFirst();

                if (!playerOpt.isPresent()) {
                    playerOpt = playersSet.stream()
                            .parallel().filter(p ->
                                    p.position == Position.M)
                            .findFirst();

                    if (!playerOpt.isPresent()) {
                        playerOpt = playersSet.stream()
                                .parallel().filter(p ->
                                        p.position == Position.F)
                                .findFirst();

                        if (!playerOpt.isPresent()) {
                            showUnknownError();
                            return;
                        }
                    }
                }

                Player player = playerOpt.get();
                playersSet.remove(player);

                tvRating.setText(String.valueOf(player.rating));
                tvName.setText(player.name);
            });
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
                statusBarH, screenW, screenH - statusBarH);

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
                "Guarda i team che ho appena creato su "
                        + getString(R.string.app_name),
                mimeTypeArray,
                new ClipData.Item(uriToImage)
        ));

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        String shareBody = "Guarda i team che ho appena creato su "
                + getString(R.string.app_name);

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "I team di oggi");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);

        shareIntent.setType("image/png");

        startActivityForResult(Intent.createChooser(shareIntent,
                getResources().getText(R.string.send_to)), RC_sharing);
    }

}