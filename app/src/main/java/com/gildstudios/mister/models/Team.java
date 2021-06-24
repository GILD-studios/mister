package com.gildstudios.mister.models;


import com.gildstudios.mister.enums.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Team {

    private final int mId;
    private final ArrayList<Player> mPlayers;

    public Team(int id) {
        this.mId = id;
        this.mPlayers = new ArrayList<>();
    }

    public void add(Player player) {
        this.mPlayers.add(player);
    }

    public static ArrayList<Team> fromJson(JSONArray teamsArray) {
        return fromJson(teamsArray, 1.0);
    }

    public static ArrayList<Team> fromJson(JSONArray teamsArray, double alpha) {
        ArrayList<Team> teams = new ArrayList<>();

        try {
            for (int i = 0; i < teamsArray.length(); i++) {
                JSONObject teamObj = teamsArray.getJSONObject(i);

                int id = teamObj.getInt("id");
                JSONArray playersArray = teamObj.getJSONArray("players");

                Team team = new Team(id);

                for (int j = 0; j < playersArray.length(); j++) {
                    JSONObject playerObj = playersArray.getJSONObject(j);

                    String name = playerObj.getString("name");
                    int rating  = (int) Math.round(playerObj
                            .getInt("rating") / alpha);
                    Position position = Position.valueOf(playerObj
                            .getString("position"));

                    team.add(Player.with(name,
                                         position,
                                         rating));
                }

                teams.add(team);
            }
            return teams;
        } catch (JSONException e) {
            return null;
        }
    }

    public int getId() {
        return mId;
    }

    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }

}
