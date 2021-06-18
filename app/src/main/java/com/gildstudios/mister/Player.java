package com.gildstudios.mister;

import java.util.Comparator;

public class Player implements Comparable<Player> {
    private String name;
    private int overall;
    private String ruolo;

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public Player(String name, int overall) {
        this.name = name;
        this.overall = overall;
        if(overall > 100)
            this.ruolo = "att";
        else if(overall > 50)
            this.ruolo = "cen";
        else
            this.ruolo = "dif";
    }

    public Player(String name, int overall, int ruolo) {
        this.name = name;
        this.overall = overall;
        if(ruolo == 0)
            this.ruolo = "dif";
        else if (ruolo == 1)
            this.ruolo = "cen";
        else if (ruolo == 2)
            this.ruolo = "att";

    }


    @Override
    public int compareTo(Player comparePlayer) {

        int compareOverall = comparePlayer.getOverall();

        //ascending order
        //return this.overall - compareOverall;

        //descending order
        return compareOverall - this.overall;

    }



    public static Comparator<Player> PlayerComparator
            = new Comparator<Player>() {

        public int compare(Player p1, Player p2) {

            int player1 = p1.getOverall();
            int player2 = p2.getOverall();

            //ascending order
            //return p2.compareTo(p1);

            //descending order
            return p1.compareTo(p2);
        }

    };


    //Sort per ruolo

   static class PlayerRoleComparator implements Comparator<Player> {
       public int compare(Player p1, Player p2) {
           return p1.getRuolo().compareTo(p2.getRuolo());
       }
   }

}
