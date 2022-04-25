package com.gildstudios.mister;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import static com.gildstudios.mister.Player.PlayerComparator;

public class FirstFragment extends Fragment {

    public static ArrayList<Player> team1 = new ArrayList<>();
    public static ArrayList<Player> team2 = new ArrayList<>();
    public static ArrayList<Player> playerList = new ArrayList<>();
    //public static ArrayList<Player> playerNormalized;

    public static int format = 5;



    int c1 = 2, c2 = 2, c3 = 1, c4 = 1, c5 = 0, c6 = 2, c7 = 2, c8 = 1, c9 = 1, c10 = 0, c11 = 0, c12 = 0, c13 = 0, c14 = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.insert_player, container, false);
    }

    @SuppressLint("RestrictedApi")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /*playerList = new ArrayList<>();
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();*/

        int[] ruoli = new int[]{R.drawable.d, R.drawable.c, R.drawable.a, R.drawable.p};

        final Button calculate = view.findViewById(R.id.button_first2);

        final EditText player1 = view.findViewById(R.id.editTextTextPersonName1);
        final ImageView ruolo1 = view.findViewById(R.id.ruolo1);
        //c1 = 2;
        ruolo1.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c1]));
        ruolo1.setOnClickListener(v -> {
            if(c1 == 0) {
                ruolo1.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c1++;
            } else if(c1 == 1) {
                ruolo1.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c1++;
            } else if(c1 == 2){
                ruolo1.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c1++;
            }
            else if(c1 == 3){
                ruolo1.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c1 = 0;
            }
        });

        final EditText player2 = view.findViewById(R.id.editTextTextPersonName2);
        final ImageView ruolo2 = view.findViewById(R.id.ruolo);
        //c2 = 2;
        ruolo2.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c2]));
        ruolo2.setOnClickListener(v -> {
            if(c2 == 0) {
                ruolo2.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c2++;
            } else if(c2 == 1) {
                ruolo2.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c2++;
            } else  if(c2 == 2){
                ruolo2.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c2++;
            }
            else if(c2 == 3){
                ruolo2.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c2 = 0;
            }
        });

        final EditText player3 = view.findViewById(R.id.editTextTextPersonName3);
        final ImageView ruolo3 = view.findViewById(R.id.ruolo2);
        //c3 = 1;
        ruolo3.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c3]));
        ruolo3.setOnClickListener(v -> {
            if(c3 == 0) {
                ruolo3.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c3++;
            } else if(c3 == 1) {
                ruolo3.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c3++;
            } else  if(c3 == 2){
                ruolo3.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c3++;
            }
            else if(c3 == 3){
                ruolo3.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c3 = 0;
            }
        });

        final EditText player4 = view.findViewById(R.id.editTextTextPersonName4);
        final ImageView ruolo4 = view.findViewById(R.id.ruolo3);
        //c4 = 1;
        ruolo4.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c4]));
        ruolo4.setOnClickListener(v -> {
            if(c4 == 0) {
                ruolo4.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c4++;
            } else if(c4 == 1) {
                ruolo4.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c4++;
            } else  if(c4 == 2){
                ruolo4.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c4++;
            }
            else if(c4 == 3){
                ruolo4.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c4 = 0;
            }
        });

        final EditText player5 = view.findViewById(R.id.editTextTextPersonName5);
        final ImageView ruolo5 = view.findViewById(R.id.ruolo4);
        //c5 = 0;
        ruolo5.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c5]));
        ruolo5.setOnClickListener(v -> {
            if(c5 == 0) {
                ruolo5.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c5++;
            } else if(c5 == 1) {
                ruolo5.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c5++;
            } else  if(c5 == 2){
                ruolo5.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c5++;
            }
            else if(c5 == 3){
                ruolo5.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c5 = 0;
            }
        });

        final EditText player6 = view.findViewById(R.id.editTextTextPersonName7);
        final ImageView ruolo6 = view.findViewById(R.id.ruolo7);
        //c6 = 2;
        ruolo6.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c6]));
        ruolo6.setOnClickListener(v -> {
            if(c6 == 0) {
                ruolo6.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c6++;
            } else if(c6 == 1) {
                ruolo6.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c6++;
            } else  if(c6 == 2){
                ruolo6.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c6++;
            }
            else if(c6 == 3){
                ruolo6.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c6 = 0;
            }
        });

        final EditText player7 = view.findViewById(R.id.editTextTextPersonName8);
        final ImageView ruolo7 = view.findViewById(R.id.ruolo6);
        //c7 = 2;
        ruolo7.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c7]));
        ruolo7.setOnClickListener(v -> {
            if(c7 == 0) {
                ruolo7.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c7++;
            } else if(c7 == 1) {
                ruolo7.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c7++;
            } else  if(c7 == 2){
                ruolo7.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c7++;
            }
            else if(c7 == 3){
                ruolo7.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c7 = 0;
            }
        });

        final EditText player8 = view.findViewById(R.id.editTextTextPersonName9);
        final ImageView ruolo8 = view.findViewById(R.id.ruolo8);
        //c8 = 1;
        ruolo8.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c8]));
        ruolo8.setOnClickListener(v -> {
            if(c8 == 0) {
                ruolo8.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c8++;
            } else if(c8 == 1) {
                ruolo8.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c8++;
            } else  if(c8 == 2){
                ruolo8.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c8++;
            }
            else if(c8 == 3){
                ruolo8.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c8 = 0;
            }
        });

        final EditText player9 = view.findViewById(R.id.editTextTextPersonName10);
        final ImageView ruolo9 = view.findViewById(R.id.ruolo9);
        //c9 = 1;
        ruolo9.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c9]));
        ruolo9.setOnClickListener(v -> {
            if(c9 == 0) {
                ruolo9.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c9++;
            } else if(c9 == 1) {
                ruolo9.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c9++;
            } else  if(c9 == 2){
                ruolo9.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c9++;
            }
            else if(c9 == 3){
                ruolo9.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c9 = 0;
            }
        });

        final EditText player10 = view.findViewById(R.id.editTextTextPersonName11);
        final ImageView ruolo10 = view.findViewById(R.id.ruolo10);
        //c10 = 0;
        ruolo10.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c10]));
        ruolo10.setOnClickListener(v -> {
            if(c10 == 0) {
                ruolo10.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c10++;
            } else if(c10 == 1) {
                ruolo10.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c10++;
            } else  if(c10 == 2){
                ruolo10.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c10++;
            }
            else if(c10 == 3){
                ruolo10.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c10 = 0;
            }
        });

        final EditText player11 = view.findViewById(R.id.editTextTextPersonName6);
        final ImageView ruolo11 = view.findViewById(R.id.ruolo5);
        //c11 = 0;
        ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c11]));
        ruolo11.setOnClickListener(v -> {
            if(c11 == 0) {
                ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c11++;
            } else if(c11 == 1) {
                ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c11++;
            } else  if(c11 == 2){
                ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c11++;
            }
            else if(c11 == 3){
                ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c11 = 0;
            }
        });

        final EditText player12 = view.findViewById(R.id.editTextTextPersonName12);
        final ImageView ruolo12 = view.findViewById(R.id.ruolo11);
        //c12 = 0;
        ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c12]));
        ruolo12.setOnClickListener(v -> {
            if(c12 == 0) {
                ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c12++;
            } else if(c12 == 1) {
                ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c12++;
            } else  if(c12 == 2){
                ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c12++;
            }
            else if(c12 == 3){
                ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c12 = 0;
            }
        });

        final EditText player13 = view.findViewById(R.id.editTextTextPersonName);
        final ImageView ruolo13 = view.findViewById(R.id.ruolo12);
        //c13 = 0;
        ruolo13.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c13]));
        ruolo13.setOnClickListener(v -> {
            if(c13 == 0) {
                ruolo13.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c13++;
            } else if(c13 == 1) {
                ruolo13.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c13++;
            } else  if(c13 == 2){
                ruolo13.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c13++;
            }
            else if(c13 == 3){
                ruolo13.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c13 = 0;
            }
        });

        final EditText player14 = view.findViewById(R.id.editTextTextPersonName13);
        final ImageView ruolo14 = view.findViewById(R.id.ruolo13);
        //c14 = 0;
        ruolo14.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c14]));
        ruolo14.setOnClickListener(v -> {
            if(c14 == 0) {
                ruolo14.setBackground(ContextCompat.getDrawable(getContext(), ruoli[1]));
                c14++;
            } else if(c14 == 1) {
                ruolo14.setBackground(ContextCompat.getDrawable(getContext(), ruoli[2]));
                c14++;
            } else  if(c14 == 2){
                ruolo14.setBackground(ContextCompat.getDrawable(getContext(), ruoli[3]));
                c14++;
            }
            else if(c14 == 3){
                ruolo14.setBackground(ContextCompat.getDrawable(getContext(), ruoli[0]));
                c14 = 0;
            }
        });

        final TextView squadra1 = view.findViewById(R.id.textView17);
        final TextView squadra2 = view.findViewById(R.id.textView18);
        final FloatingActionButton field = view.findViewById(R.id.floatingActionButton);

        final RadioButton calcio5 = view.findViewById(R.id.calcio5);
        final RadioButton calcio6 = view.findViewById(R.id.calcio6);
        final RadioButton calcio7 = view.findViewById(R.id.calcio7);

        calcio5.setChecked(true);
        if(format == 5) {
            player11.setVisibility(View.GONE);
            ruolo11.setVisibility(View.GONE);
            player12.setVisibility(View.GONE);
            ruolo12.setVisibility(View.GONE);
            player13.setVisibility(View.GONE);
            ruolo13.setVisibility(View.GONE);
            player14.setVisibility(View.GONE);
            ruolo14.setVisibility(View.GONE);
        } else if (format == 6) {
            player11.setVisibility(View.VISIBLE);
            ruolo11.setVisibility(View.VISIBLE);
            player12.setVisibility(View.VISIBLE);
            ruolo12.setVisibility(View.VISIBLE);
            player13.setVisibility(View.GONE);
            ruolo13.setVisibility(View.GONE);
            player14.setVisibility(View.GONE);
            ruolo14.setVisibility(View.GONE);
        } else {
            player11.setVisibility(View.VISIBLE);
            ruolo11.setVisibility(View.VISIBLE);
            player12.setVisibility(View.VISIBLE);
            ruolo12.setVisibility(View.VISIBLE);
            player13.setVisibility(View.VISIBLE);
            ruolo13.setVisibility(View.VISIBLE);
            player14.setVisibility(View.VISIBLE);
            ruolo14.setVisibility(View.VISIBLE);
        }



        calcio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calcio6.isChecked()) {
                    calcio5.setChecked(true);
                    calcio6.setChecked(false);
                } else if(calcio7.isChecked()) {
                    calcio5.setChecked(true);
                    calcio7.setChecked(false);
                }

                format = 5;

                player11.setVisibility(View.GONE);
                ruolo11.setVisibility(View.GONE);
                player12.setVisibility(View.GONE);
                ruolo12.setVisibility(View.GONE);
                player13.setVisibility(View.GONE);
                ruolo13.setVisibility(View.GONE);
                player14.setVisibility(View.GONE);
                ruolo14.setVisibility(View.GONE);
            }
        });



        calcio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calcio5.isChecked()) {
                    calcio6.setChecked(true);
                    calcio5.setChecked(false);
                }  else if(calcio7.isChecked()) {
                    calcio6.setChecked(true);
                    calcio7.setChecked(false);
                }

                format = 6;

                player11.setVisibility(View.VISIBLE);
                ruolo11.setVisibility(View.VISIBLE);
                player12.setVisibility(View.VISIBLE);
                ruolo12.setVisibility(View.VISIBLE);
                player13.setVisibility(View.GONE);
                ruolo13.setVisibility(View.GONE);
                player14.setVisibility(View.GONE);
                ruolo14.setVisibility(View.GONE);
            }
        });

        calcio7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calcio5.isChecked()) {
                    calcio7.setChecked(true);
                    calcio5.setChecked(false);
                } else if(calcio6.isChecked()) {
                    calcio7.setChecked(true);
                    calcio6.setChecked(false);
                }

                format = 7;

                player11.setVisibility(View.VISIBLE);
                ruolo11.setVisibility(View.VISIBLE);
                player12.setVisibility(View.VISIBLE);
                ruolo12.setVisibility(View.VISIBLE);
                player13.setVisibility(View.VISIBLE);
                ruolo13.setVisibility(View.VISIBLE);
                player14.setVisibility(View.VISIBLE);
                ruolo14.setVisibility(View.VISIBLE);
            }
        });

        final SeekBar seekBar = view.findViewById(R.id.seekBar);
        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!team1.isEmpty()) {
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                } else
                    Toast.makeText(getContext(), "Devi ancora calcolare le squadre!", Toast.LENGTH_SHORT).show();
            }
        });

        field.setVisibility(View.GONE);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((player1.getText().toString().isEmpty() || player2.getText().toString().isEmpty() || player3.getText().toString().isEmpty() || player4.getText().toString().isEmpty() || player5.getText().toString().isEmpty() || player6.getText().toString().isEmpty() || player7.getText().toString().isEmpty() || player8.getText().toString().isEmpty() || player9.getText().toString().isEmpty() || player10.getText().toString().isEmpty())) {
                    Toast.makeText(getContext(), R.string.missing_fields, Toast.LENGTH_SHORT).show();
                } else if(format == 6 && (player11.getText().toString().isEmpty() || player12.getText().toString().isEmpty())) {
                    Toast.makeText(getContext(), R.string.missing_fields, Toast.LENGTH_SHORT).show();
                } else if (format == 7 && (player11.getText().toString().isEmpty() || player12.getText().toString().isEmpty() || player13.getText().toString().isEmpty() || player14.getText().toString().isEmpty())) {
                    Toast.makeText(getContext(), R.string.missing_fields, Toast.LENGTH_SHORT).show();
                } else {
                    team1.clear();
                    team2.clear();
                    playerList.clear();
                    //    playerNormalized.clear();
                    int tot1;
                    int tot2;

                    String[] p11 = new String[2], p12 = new String[2], p13 = new String[2], p14 = new String[2];
                    String[] p1 = player1.getText().toString().split(" ");
                    String[] p2 = player2.getText().toString().split(" ");
                    String[] p3 = player3.getText().toString().split(" ");
                    String[] p4 = player4.getText().toString().split(" ");
                    String[] p5 = player5.getText().toString().split(" ");
                    String[] p6 = player6.getText().toString().split(" ");
                    String[] p7 = player7.getText().toString().split(" ");
                    String[] p8 = player8.getText().toString().split(" ");
                    String[] p9 = player9.getText().toString().split(" ");
                    String[] p10 = player10.getText().toString().split(" ");
                    if (calcio6.isChecked()) {
                        p11 = player11.getText().toString().split(" ");
                        p12 = player12.getText().toString().split(" ");
                    }
                    if (calcio7.isChecked()) {
                        p11 = player11.getText().toString().split(" ");
                        p12 = player12.getText().toString().split(" ");
                        p13 = player13.getText().toString().split(" ");
                        p14 = player14.getText().toString().split(" ");
                    }

                    if (p1.length < 2 || p2.length < 2 || p3.length < 2 || p4.length < 2 || p5.length < 2 || p6.length < 2 || p7.length < 2 || p8.length < 2 || p9.length < 2 || p10.length < 2) {
                        Toast.makeText(getContext(), R.string.fail_missing_name, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        playerList.add(new Player(p1[0], Integer.parseInt(p1[1]), c1));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p1[0] + ' ' + p1[1], Integer.parseInt(p1[2]), c1));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p2[0], Integer.parseInt(p2[1]), c2));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p2[0] + ' ' + p2[1], Integer.parseInt(p2[2]), c2));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p3[0], Integer.parseInt(p3[1]), c3));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p3[0] + ' ' + p3[1], Integer.parseInt(p3[2]), c3));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p4[0], Integer.parseInt(p4[1]), c4));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p4[0] + ' ' + p4[1], Integer.parseInt(p4[2]), c4));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p5[0], Integer.parseInt(p5[1]), c5));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p5[0] + ' ' + p5[1], Integer.parseInt(p5[2]), c5));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p6[0], Integer.parseInt(p6[1]), c6));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p6[0] + ' ' + p6[1], Integer.parseInt(p6[2]), c6));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p7[0], Integer.parseInt(p7[1]), c7));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p7[0] + ' ' + p7[1], Integer.parseInt(p7[2]), c7));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p8[0], Integer.parseInt(p8[1]), c8));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p8[0] + ' ' + p8[1], Integer.parseInt(p8[2]), c8));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p9[0], Integer.parseInt(p9[1]), c9));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p9[0] + ' ' + p9[1], Integer.parseInt(p9[2]), c9));
                        e.printStackTrace();
                    }

                    try {
                        playerList.add(new Player(p10[0], Integer.parseInt(p10[1]), c10));
                    } catch (NumberFormatException e) {
                        playerList.add(new Player(p10[0] + ' ' + p10[1], Integer.parseInt(p10[2]), c10));
                        e.printStackTrace();
                    }
                    if (calcio6.isChecked()) {
                        try {
                            playerList.add(new Player(p11[0], Integer.parseInt(p11[1]), c11));
                        } catch (NumberFormatException e) {
                            playerList.add(new Player(p11[0] + ' ' + p11[1], Integer.parseInt(p11[2]), c11));
                            e.printStackTrace();
                        }

                        try {
                            playerList.add(new Player(p12[0], Integer.parseInt(p12[1]), c12));
                        } catch (NumberFormatException e) {
                            playerList.add(new Player(p12[0] + ' ' + p12[1], Integer.parseInt(p12[2]), c12));
                            e.printStackTrace();
                        }
                    }
                    if (calcio7.isChecked()) {
                        try {
                            playerList.add(new Player(p11[0], Integer.parseInt(p11[1]), c11));
                        } catch (NumberFormatException e) {
                            playerList.add(new Player(p11[0] + ' ' + p11[1], Integer.parseInt(p11[2]), c11));
                            e.printStackTrace();
                        }

                        try {
                            playerList.add(new Player(p12[0], Integer.parseInt(p12[1]), c12));
                        } catch (NumberFormatException e) {
                            playerList.add(new Player(p12[0] + ' ' + p12[1], Integer.parseInt(p12[2]), c12));
                            e.printStackTrace();
                        }

                        try {
                            playerList.add(new Player(p13[0], Integer.parseInt(p13[1]), c13));
                        } catch (NumberFormatException e) {
                            playerList.add(new Player(p13[0] + ' ' + p13[1], Integer.parseInt(p13[2]), c13));
                            e.printStackTrace();
                        }

                        try {
                            playerList.add(new Player(p14[0], Integer.parseInt(p14[1]), c14));
                        } catch (NumberFormatException e) {
                            playerList.add(new Player(p14[0] + ' ' + p14[1], Integer.parseInt(p14[2]), c14));
                            e.printStackTrace();
                        }
                    }

                    playerList.sort(PlayerComparator);

                    seekBar.setMin(1);
                    seekBar.setMax(10);
                    int tolleranza = seekBar.getProgress();

                    int count = 0;
                    int tentativi;

                    while (true) {
                        count++;

                        Boolean god = new Random().nextBoolean();
                        Log.v("moneta", god.toString());
                        tot1 = 0;
                        tot2 = 0;
                        team1.clear();
                        team2.clear();

                        for (int j = 0; j < playerList.size(); j++) {
                            if (god) {
                                if (team1.size() < playerList.size() / 2) {
                                    team1.add(playerList.get(j));
                                    tot1 += playerList.get(j).getOverall();
                                } else {
                                    team2.add(playerList.get(j));
                                    tot2 += playerList.get(j).getOverall();
                                }
                            } else {
                                if (team2.size() < playerList.size() / 2) {
                                    team2.add(playerList.get(j));
                                    tot2 += playerList.get(j).getOverall();
                                } else {
                                    team1.add(playerList.get(j));
                                    tot1 += playerList.get(j).getOverall();
                                }
                            }

                            god = new Random().nextBoolean();
                        }

                        int nAtt1 = 0;
                        int nCen1 = 0;
                        int nDif1 = 0;
                        int nAtt2 = 0;
                        int nCen2 = 0;
                        int nDif2 = 0;
                        int nPor1 = 0;
                        int nPor2 = 0;

                        for (Player p : team1) {
                            if (p.getRuolo().equals("att"))
                                nAtt1++;
                            else if (p.getRuolo().equals("cen"))
                                nCen1++;
                            else if (p.getRuolo().equals("por"))
                                nPor1++;
                            else
                                nDif1++;
                        }

                        for (Player p : team2) {
                            if (p.getRuolo().equals("att"))
                                nAtt2++;
                            else if (p.getRuolo().equals("cen"))
                                nCen2++;
                            else if (p.getRuolo().equals("por"))
                                nPor2++;
                            else
                                nDif2++;
                        }

                        playerList.sort(PlayerComparator);

                        boolean fortiInsieme = (team1.contains(playerList.get(0)) && team1.contains(playerList.get(1))) || (team2.contains(playerList.get(0)) && team2.contains(playerList.get(1)));
                        boolean scarsiInsieme = (team1.contains(playerList.get(playerList.size() - 1)) && team1.contains(playerList.get(playerList.size() - 2))) || (team2.contains(playerList.get(playerList.size() - 1)) && team2.contains(playerList.get(playerList.size() - 2)));
                        boolean constraintAtt = (nAtt1 - nAtt2) <= 1 && (nAtt1 - nAtt2) >= -1;
                        boolean constraintCen = (nCen1 - nCen2) <= 1 && (nCen1 - nCen2) >= -1;
                        boolean constraintDif = (nDif1 - nDif2) <= 1 && (nDif1 - nDif2) >= -1;
                        boolean constraintPor = (nPor1 - nPor2) <= 1 && (nPor1 - nPor2) >= -1;
                        boolean diff = (tot1 - tot2) <= tolleranza && (tot1 - tot2) >= -tolleranza;

                        if (!fortiInsieme && !scarsiInsieme && constraintAtt && constraintCen && constraintDif && constraintPor && diff) {
                            tentativi = (tolleranza) * 1000 - (1000 - count);
                            Log.v("risultato", "squadra 2: " + tot2 + ", squadra 1: " + tot1 + "; differenza: " + (tot2 - tot1) + ", numero tentativi: " + tentativi);
                            break;
                        }

                        if (tolleranza > 10) {
                            Toast.makeText(getContext(), R.string.fail_non_equilibrate, Toast.LENGTH_SHORT).show();
                            break;
                        }

                        if (count > 1000) {
                            //Log.v("risultato", "Nessuna combinazione trovata");
                            tolleranza++;
                            seekBar.setProgress(tolleranza);
                            count = 0;
                        }
                    }
                    if (tolleranza <= 10) {

                        squadra1.setVisibility(View.VISIBLE);
                        squadra2.setVisibility(View.VISIBLE);

                        team1.sort(PlayerComparator);
                        team2.sort(PlayerComparator);

                        player1.setText(team1.get(0).getName() + " " + team1.get(0).getOverall());
                        switch (team1.get(0).getRuolo()) {
                            case "por":
                                c1 = 3;
                                break;
                            case "att":
                                c1 = 2;
                                break;
                            case "cen":
                                c1 = 1;
                                break;
                            case "dif":
                                c1 = 0;
                                break;
                        }
                        ruolo1.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c1]));

                        player2.setText(team1.get(1).getName() + " " + team1.get(1).getOverall());
                        switch (team1.get(1).getRuolo()) {
                            case "por":
                                c2 = 3;
                                break;
                            case "att":
                                c2 = 2;
                                break;
                            case "cen":
                                c2 = 1;
                                break;
                            case "dif":
                                c2 = 0;
                                break;
                        }
                        ruolo2.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c2]));

                        player3.setText(team1.get(2).getName() + " " + team1.get(2).getOverall());
                        switch (team1.get(2).getRuolo()) {
                            case "por":
                                c3 = 3;
                                break;
                            case "att":
                                c3 = 2;
                                break;
                            case "cen":
                                c3 = 1;
                                break;
                            case "dif":
                                c3 = 0;
                                break;
                        }
                        ruolo3.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c3]));

                        player4.setText(team1.get(3).getName() + " " + team1.get(3).getOverall());
                        switch (team1.get(3).getRuolo()) {
                            case "por":
                                c4 = 3;
                                break;
                            case "att":
                                c4 = 2;
                                break;
                            case "cen":
                                c4 = 1;
                                break;
                            case "dif":
                                c4 = 0;
                                break;
                        }
                        ruolo4.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c4]));

                        player5.setText(team1.get(4).getName() + " " + team1.get(4).getOverall());
                        switch (team1.get(4).getRuolo()) {
                            case "por":
                                c5 = 3;
                                break;
                            case "att":
                                c5 = 2;
                                break;
                            case "cen":
                                c5 = 1;
                                break;
                            case "dif":
                                c5 = 0;
                                break;
                        }
                        ruolo5.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c5]));

                        player6.setText(team2.get(0).getName() + " " + team2.get(0).getOverall());
                        switch (team2.get(0).getRuolo()) {
                            case "por":
                                c6 = 3;
                                break;
                            case "att":
                                c6 = 2;
                                break;
                            case "cen":
                                c6 = 1;
                                break;
                            case "dif":
                                c6 = 0;
                                break;
                        }
                        ruolo6.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c6]));

                        player7.setText(team2.get(1).getName() + " " + team2.get(1).getOverall());
                        switch (team2.get(1).getRuolo()) {
                            case "por":
                                c7 = 3;
                                break;
                            case "att":
                                c7 = 2;
                                break;
                            case "cen":
                                c7 = 1;
                                break;
                            case "dif":
                                c7 = 0;
                                break;
                        }
                        ruolo7.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c7]));

                        player8.setText(team2.get(2).getName() + " " + team2.get(2).getOverall());
                        switch (team2.get(2).getRuolo()) {
                            case "por":
                                c8 = 3;
                                break;
                            case "att":
                                c8 = 2;
                                break;
                            case "cen":
                                c8 = 1;
                                break;
                            case "dif":
                                c8 = 0;
                                break;
                        }
                        ruolo8.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c8]));

                        player9.setText(team2.get(3).getName() + " " + team2.get(3).getOverall());
                        switch (team2.get(3).getRuolo()) {
                            case "por":
                                c9 = 3;
                                break;
                            case "att":
                                c9 = 2;
                                break;
                            case "cen":
                                c9 = 1;
                                break;
                            case "dif":
                                c9 = 0;
                                break;
                        }
                        ruolo9.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c9]));

                        player10.setText(team2.get(4).getName() + " " + team2.get(4).getOverall());
                        switch (team2.get(4).getRuolo()) {
                            case "por":
                                c10 = 3;
                                break;
                            case "att":
                                c10 = 2;
                                break;
                            case "cen":
                                c10 = 1;
                                break;
                            case "dif":
                                c10 = 0;
                                break;
                        }
                        ruolo10.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c10]));

                        if (calcio6.isChecked()) {
                            player11.setText(team1.get(5).getName() + " " + team1.get(5).getOverall());
                            switch (team1.get(5).getRuolo()) {
                                case "por":
                                    c11 = 3;
                                    break;
                                case "att":
                                    c11 = 2;
                                    break;
                                case "cen":
                                    c11 = 1;
                                    break;
                                case "dif":
                                    c11 = 0;
                                    break;
                            }
                            ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c11]));

                            player12.setText(team2.get(5).getName() + " " + team2.get(5).getOverall());
                            switch (team2.get(5).getRuolo()) {
                                case "por":
                                    c12 = 3;
                                    break;
                                case "att":
                                    c12 = 2;
                                    break;
                                case "cen":
                                    c12 = 1;
                                    break;
                                case "dif":
                                    c12 = 0;
                                    break;
                            }
                            ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c12]));
                        }

                        if (calcio7.isChecked()) {
                            player11.setText(team1.get(5).getName() + " " + team1.get(5).getOverall());
                            switch (team1.get(5).getRuolo()) {
                                case "por":
                                    c11 = 3;
                                    break;
                                case "att":
                                    c11 = 2;
                                    break;
                                case "cen":
                                    c11 = 1;
                                    break;
                                case "dif":
                                    c11 = 0;
                                    break;
                            }
                            ruolo11.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c11]));

                            player12.setText(team2.get(5).getName() + " " + team2.get(5).getOverall());
                            switch (team2.get(5).getRuolo()) {
                                case "por":
                                    c12 = 3;
                                    break;
                                case "att":
                                    c12 = 2;
                                    break;
                                case "cen":
                                    c12 = 1;
                                    break;
                                case "dif":
                                    c12 = 0;
                                    break;
                            }
                            ruolo12.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c12]));

                            player13.setText(team1.get(6).getName() + " " + team1.get(6).getOverall());
                            switch (team1.get(6).getRuolo()) {
                                case "por":
                                    c13 = 3;
                                    break;
                                case "att":
                                    c13 = 2;
                                    break;
                                case "cen":
                                    c13 = 1;
                                    break;
                                case "dif":
                                    c13 = 0;
                                    break;
                            }
                            ruolo13.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c13]));

                            player14.setText(team2.get(6).getName() + " " + team2.get(6).getOverall());
                            switch (team2.get(6).getRuolo()) {
                                case "por":
                                    c14 = 3;
                                    break;
                                case "att":
                                    c14 = 2;
                                    break;
                                case "cen":
                                    c14 = 1;
                                    break;
                                case "dif":
                                    c14 = 0;
                                    break;
                            }
                            ruolo14.setBackground(ContextCompat.getDrawable(getContext(), ruoli[c14]));
                        }

                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment);
                    }
                }
            }
        });

    }

}