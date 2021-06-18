package com.gildstudios.mister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.gildstudios.mister.FirstFragment.team1;
import static com.gildstudios.mister.FirstFragment.team2;

public class SecondFragment extends Fragment {

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
        if(FirstFragment.format == 6) {
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
}