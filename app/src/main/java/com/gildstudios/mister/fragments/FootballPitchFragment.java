package com.gildstudios.mister.fragments;

import android.graphics.Color;
import android.os.Bundle;
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

import com.gildstudios.mister.R;
import com.gildstudios.mister.abstracts.AwareFragment;
import com.gildstudios.mister.enums.Position;
import com.gildstudios.mister.models.Player;
import com.gildstudios.mister.models.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FootballPitchFragment extends AwareFragment {

    private int N;
    private double mBalance;
    private ArrayList<Team> mTeams;

    private Toolbar mToolbar;

    ArrayList<ImageView> ivShirts;
    ArrayList<TextView> tvNames;
    ArrayList<TextView> tvRatings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

            N = bundle.getInt("N");

            JSONArray teamsJson = new JSONArray(
                    bundle.getString("teams"));

            mTeams = Team.fromJson(teamsJson);
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
        MenuItem balanceItem = menu.findItem(R.id.no_action_balance);

        if (mBalance != -1.0) {
            balanceItem.setTitle(
                    getString(R.string.no_action_balance,
                              mBalance*100));

            balanceItem.setVisible(true);

        }

        super.onCreateOptionsMenu(menu, inflater);
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
        if (N == 5) {
            updateUI_pitch5();
        } else if (N == 6) {
            updateUI_pitch6();
        } else {
            updateUI_pitch7();
        }
    }

    private void updateUI_pitch5() {
        int[] goneIdxs = {0, 6, 7, 13};

        updateWidgets(goneIdxs);
    }

    private void updateUI_pitch6() {
        int[] goneIdxs = {0, 7};

        updateWidgets(goneIdxs);
    }

    private void updateUI_pitch7() {
        int[] goneIdxs = {};

        updateWidgets(goneIdxs);
    }

    private void updateWidgets(int[] goneIdxs) {
        for (int i : goneIdxs) {
            tvNames.get(i).setVisibility(View.INVISIBLE);
            tvRatings.get(i).setVisibility(View.INVISIBLE);
            ivShirts.get(i).setVisibility(View.INVISIBLE);
        }

        displayPlayers(goneIdxs);
    }

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

            if (teamIdxsSet.size() != N) {
                showUnknownError();
                return;
            }

            Set<Player> playersSet = new HashSet<>(mTeams.get(i).getPlayers());

            teamIdxsSet.forEach(j -> {
                TextView tvName = tvNames.get(j);
                TextView tvRating = tvRatings.get(j);

                Position position = getPosition(j);
                Optional<Player> playerOpt = playersSet.stream()
                        .parallel().filter(p
                                -> p.position == position)
                        .findFirst();

                if (!playerOpt.isPresent()) {
                    showUnknownError();
                    return;
                }

                Player player = playerOpt.get();
                playersSet.remove(player);

                tvName.setText(player.name);
                tvRating.setText(String.valueOf(
                        player.rating));
            });
        }
    }

    private Position getPosition(int widgetIdx) {
        int normIdx = widgetIdx % 7;

        if (normIdx < 3) {
            return Position.D;
        } else if (normIdx == 3
                || normIdx == 5) {
            return Position.M;
        } else {
            return Position.F;
        }
    }

}