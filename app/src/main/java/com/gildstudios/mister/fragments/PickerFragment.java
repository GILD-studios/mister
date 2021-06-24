package com.gildstudios.mister.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gildstudios.mister.BuildConfig;
import com.gildstudios.mister.MisterApplication;
import com.gildstudios.mister.R;
import com.gildstudios.mister.abstracts.AwareFragment;
import com.gildstudios.mister.enums.Position;
import com.gildstudios.mister.models.Player;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gildstudios.mister.utils.Generics.RATING_maxApi;
import static com.gildstudios.mister.utils.Generics.capitalize;


public class PickerFragment extends AwareFragment {

    private final int RATING_max = 50;
    private final int RATING_min = 0;

    private final double alpha = (1.0*RATING_maxApi) / RATING_max;

    private int n;

    private final static String TAG = PickerFragment.class.getSimpleName();

    private ArrayList<EditText> etPlayers;
    private ArrayList<ImageView> ivPositions;
    private ArrayList<Player> mPlayers;

    private Button btnCreateTeams;

    private ProgressBar pbarCreateTeams;

    private RadioButton rbCalcio5;
    private RadioButton rbCalcio6;
    private RadioButton rbCalcio7;

    private Spinner spinnerFormations;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        return onPersistentView(inflater, viewGroup,
                R.layout.fragment_picker);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!activityExists())
            return;

        reference_etPlayers(view);
        reference_ivPositions(view);

        model_mPlayers();

        for (int i = 0; i < etPlayers.size(); i++) {
            ImageView ivPosition = ivPositions.get(i);

            if (!mViewExisted) {
                int resourceId = mPlayers.get(i).position
                                          .toResource();

                ivPosition.setBackground(ContextCompat.getDrawable(
                        getActivity(), resourceId));
                ivPosition.setTag(resourceId);

                if (BuildConfig.BUILD_TYPE.equals("debug")) {
                    EditText etPlayer = etPlayers.get(i);
                    CharSequence s = etPlayer.getHint();

                    // Remove unnecessary prefix
                    etPlayer.setText(s.toString().trim()
                            .replace("eg. ", ""));
                }
            }

            int _i = i; // To avoid IntelliJ errors

            // On image click the position PNG
            // should toggle forward by 1
            ivPosition.setOnClickListener(v -> {
                mPlayers.get(_i).position = mPlayers.get(_i)
                                                    .position.next();

                int resourceId = mPlayers.get(_i).position
                                         .toResource();

                ivPosition.setBackground(ContextCompat.getDrawable(
                        getActivity(), resourceId));
                ivPosition.setTag(resourceId);
            });
        }

        setup_spinnerFormations(view);
        setup_pbarCreateTeams(view);
        setup_rbTeamSizes(view);

        btnCreateTeams = view.findViewById(R.id.btnCreateTeams);
        btnCreateTeams.setOnClickListener(v -> {
            disableUX();

            Pair<Boolean, int[]> _validInput = validInput();

            if (!_validInput.first) {
                showToast("Inserisci tutti i " +
                          "giocatori mancanti!");

                enableUX();
                return;
            }

            createTeams(_validInput.second);
        });

    }

    private void setup_rbTeamSizes(@NonNull View view) {
        ArrayList<RadioButton> rbTeamSizes = new ArrayList<>();

        rbCalcio5 = view.findViewById(R.id.rbtnTeamSize_5);
        rbCalcio6 = view.findViewById(R.id.rbtnTeamSize_6);
        rbCalcio7 = view.findViewById(R.id.rbtnTeamSize_7);

        rbTeamSizes.add(rbCalcio5);
        rbTeamSizes.add(rbCalcio6);
        rbTeamSizes.add(rbCalcio7);

        for (RadioButton rb : rbTeamSizes) {
            rb.setOnCheckedChangeListener((buttonView, bChecked) -> {
                if (!bChecked)
                    return;

                for (RadioButton _rb : rbTeamSizes) {
                    if (_rb != rb)
                        _rb.setChecked(false);
                }

                if (rbCalcio5.isChecked()) {
                    updateUI(5);
                } else if (rbCalcio6.isChecked()) {
                    updateUI(6);
                } else {
                    updateUI(7);
                }
            });
        }

        if (!mViewExisted)
            rbCalcio5.setChecked(true);
    }

    private void setup_pbarCreateTeams(@NonNull View view) {
        pbarCreateTeams = view.findViewById(R.id.pbarCreateTeams);
        pbarCreateTeams.setVisibility(View.INVISIBLE);
    }

    private void setup_spinnerFormations(@NonNull View view) {
        spinnerFormations = view.findViewById(R.id.spinnerFormations);
        spinnerFormations.setEnabled(false);

        ArrayAdapter<CharSequence> adapterFormations = ArrayAdapter
                .createFromResource(getActivity(), R.array.formations,
                        android.R.layout.simple_spinner_item);
        adapterFormations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFormations.setAdapter(adapterFormations);
    }

    private void reference_etPlayers(@NonNull View view) {
        etPlayers = new ArrayList<>();

        etPlayers.add(view.findViewById(R.id.etPlayer_0_0));
        etPlayers.add(view.findViewById(R.id.etPlayer_0_1));
        etPlayers.add(view.findViewById(R.id.etPlayer_0_2));
        etPlayers.add(view.findViewById(R.id.etPlayer_0_3));
        etPlayers.add(view.findViewById(R.id.etPlayer_0_4));
        etPlayers.add(view.findViewById(R.id.etPlayer_0_5));
        etPlayers.add(view.findViewById(R.id.etPlayer_0_6));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_0));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_1));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_2));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_3));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_4));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_5));
        etPlayers.add(view.findViewById(R.id.etPlayer_1_6));
    }

    private void reference_ivPositions(@NonNull View view) {
        ivPositions = new ArrayList<>();

        ivPositions.add(view.findViewById(R.id.ivRole_0_0));
        ivPositions.add(view.findViewById(R.id.ivRole_0_1));
        ivPositions.add(view.findViewById(R.id.ivRole_0_2));
        ivPositions.add(view.findViewById(R.id.ivRole_0_3));
        ivPositions.add(view.findViewById(R.id.ivRole_0_4));
        ivPositions.add(view.findViewById(R.id.ivRole_0_5));
        ivPositions.add(view.findViewById(R.id.ivRole_0_6));
        ivPositions.add(view.findViewById(R.id.ivRole_1_0));
        ivPositions.add(view.findViewById(R.id.ivRole_1_1));
        ivPositions.add(view.findViewById(R.id.ivRole_1_2));
        ivPositions.add(view.findViewById(R.id.ivRole_1_3));
        ivPositions.add(view.findViewById(R.id.ivRole_1_4));
        ivPositions.add(view.findViewById(R.id.ivRole_1_5));
        ivPositions.add(view.findViewById(R.id.ivRole_1_6));
    }

    private void model_mPlayers() {
        ArrayList<Position> initPositions = mViewExisted
                ? restorePositions()
                : defaultPositions();

        mPlayers = new ArrayList<>();

        for (int i = 0; i < etPlayers.size(); i++)
            mPlayers.add((new Player()).position(
                    initPositions.get(i)));
    }

    private ArrayList<Position> defaultPositions() {
        ArrayList<Position> defaultPositions = new ArrayList<>();

        defaultPositions.add(Position.D);
        defaultPositions.add(Position.D);
        defaultPositions.add(Position.M);
        defaultPositions.add(Position.M);
        defaultPositions.add(Position.F);
        defaultPositions.add(Position.F);
        defaultPositions.add(Position.D);
        defaultPositions.add(Position.D);
        defaultPositions.add(Position.D);
        defaultPositions.add(Position.M);
        defaultPositions.add(Position.M);
        defaultPositions.add(Position.F);
        defaultPositions.add(Position.F);
        defaultPositions.add(Position.D);

        return defaultPositions;
    }

    private ArrayList<Position> restorePositions() {
        return ivPositions.stream()
                   .map(ivPosition -> {
                       int resourceId = (Integer) ivPosition.getTag();
                       return Position.fromResource(resourceId);
                   }).collect(Collectors.toCollection(
                                ArrayList::new));
    }

    private void updateUI(int _n) {
        n = _n;

        if (n == 5) {
            updateUI_pitch5();
        } else if (n == 6) {
            updateUI_pitch6();
        } else {
            updateUI_pitch7();
        }
    }

    private void updateUI_pitch5() {
        int[] visibleIdxs = {};
        int[] goneIdxs    = {5, 6, 12, 13};

        spinnerFormations.setSelection(0);

        updatePlayers(visibleIdxs, goneIdxs);
    }

    private void updateUI_pitch6() {
        int[] visibleIdxs = {5, 12};
        int[] goneIdxs    = {6, 13};

        spinnerFormations.setSelection(1);

        updatePlayers(visibleIdxs, goneIdxs);
    }

    private void updateUI_pitch7() {
        int[] visibleIdxs = {5, 6, 12, 13};
        int[] goneIdxs    = {};

        spinnerFormations.setSelection(2);

        updatePlayers(visibleIdxs, goneIdxs);
    }

    private void updatePlayers(int[] visibleIdxs, int[] goneIdxs) {
        for (int i : visibleIdxs) {
            etPlayers.get(i).setVisibility(View.VISIBLE);
            ivPositions.get(i).setVisibility(View.VISIBLE);
        }

        for (int i : goneIdxs) {
            etPlayers.get(i).setVisibility(View.GONE);
            ivPositions.get(i).setVisibility(View.GONE);
        }
    }

    private void disableUX() {
        btnCreateTeams.setEnabled(false);
        pbarCreateTeams.setVisibility(View.VISIBLE);

        for (int i = 0; i < etPlayers.size(); i++) {
            etPlayers.get(i).setEnabled(false);
            ivPositions.get(i).setEnabled(false);
        }
    }

    private void enableUX() {
        btnCreateTeams.setEnabled(true);
        pbarCreateTeams.setVisibility(View.INVISIBLE);

        for (int i = 0; i < etPlayers.size(); i++) {
            etPlayers.get(i).setEnabled(true);
            ivPositions.get(i).setEnabled(true);
        }
    }

    private Pair<Boolean, int[]> validInput() {
        int[] goneIdxs;

        if (n == 5) {
            goneIdxs = new int[]{5, 6, 12, 13};
        } else if (n == 6) {
            goneIdxs = new int[]{6, 13};
        } else {
            goneIdxs = new int[]{};
        }

        int[] visibleIdxs = IntStream.range(0, etPlayers.size())
                .filter(i -> Arrays.stream(goneIdxs)
                        .noneMatch(x -> x == i))
                .toArray();

        for (int i : visibleIdxs) {
            if (etPlayers.get(i).getText()
                    .toString().isEmpty())
                return Pair.create(false, null);
        }

        return Pair.create(true, visibleIdxs);
    }

    private boolean validRating(int rating) {
        return RATING_min <= rating
                && rating <= RATING_max;
    }

    private List<Player> filterPlayers(int[] visibleIdxs) {
        List<Integer> _visibleIdxs = Arrays.stream(visibleIdxs).boxed()
                                           .collect(Collectors.toList());

        return IntStream.range(0, mPlayers.size())
                        .filter(_visibleIdxs::contains)
                        .mapToObj(mPlayers::get)
                        .collect(Collectors.toList());
    }

    private void createTeams(int[] visibleIdxs) {
        for (int i: visibleIdxs) {
            String[] playerInfo = etPlayers.get(i)
                    .getText().toString()
                    .trim().split(" ");

            String name = playerInfo[0];
            int rating  = Integer.parseInt(playerInfo[1]);

            if (!validRating(rating)) {
                showToast("Invalid rating for " + capitalize(name) +
                          ": " + rating + " must be between " +
                          RATING_min + " and " + RATING_max);
                enableUX();
                return;
            }

            mPlayers.get(i).name   = name;
            mPlayers.get(i).rating = (int) Math.round(rating*alpha);
        }

        int nTeams = 2;

        String formation = spinnerFormations
                .getSelectedItem().toString();

        List<Player> visiblePlayers = filterPlayers(visibleIdxs);

        makeApiRequest(visiblePlayers, n,
                       formation, nTeams);
    }

    private void makeApiRequest(List<Player> players, int n,
                                String formation, int nTeams) {
        JSONObject jsonBody = new JSONObject();

        try {
//            // Make chars UTF-8 compliant
//            jsonBody.put("formation", formation
//                    .replaceAll("\\p{Pd}", "-"));

            jsonBody.put("n", n);
            jsonBody.put("nteams", nTeams);
            jsonBody.put("optimal", "False");

            // Serialize all the players as JSON
            jsonBody.put("players", new JSONArray(
                    (new Gson()).toJson(players)));
        } catch (JSONException e) {
            showUnknownError();
            enableUX();
            return;
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                BuildConfig.API_Mister,
                jsonBody,
                    response -> {
                        try {
                            double balance  = response.getDouble("balance");
                            JSONArray teams = response.getJSONArray("teams");

                            Bundle bundle = new Bundle();

                            bundle.putInt("n", n);
                            bundle.putDouble("alpha", alpha);
                            bundle.putDouble("balance", balance);
                            bundle.putString("teams", teams.toString());

                            enableUX();

                            NavHostFragment.findNavController(PickerFragment.this)
                                           .navigate(R.id.action_PickerFragment_to_FootballPitchFragment, bundle);
                        } catch (JSONException e) {
                            showUnknownError();
                            enableUX();
                        }
                    }, error -> {
                        if (error.getMessage() != null)
                            Log.e(TAG, error.getMessage());

                        NetworkResponse response = error.networkResponse;

                        if(response != null
                                && response.data != null) {
                            try {
                                JSONObject obj = new JSONObject(
                                        new String(response.data));

                                String message = obj.getString("error");
                                showToast(message);
                            } catch(JSONException e){
                                showUnknownError();
                            }
                        }

                        enableUX();
                    }
                );

        // Add request to queue
        MisterApplication.getInstance()
                .addToRequestQueue(jsonRequest,
                               TAG + "/make-teams");
    }

}