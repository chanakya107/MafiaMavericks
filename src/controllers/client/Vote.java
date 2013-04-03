package controllers.client;

import java.util.ArrayList;

public class Vote {
    private final ArrayList<String> players;
    private final String selectedName;
    private String[] resultVote;


    public Vote(ArrayList<String> players, String selectedName) {
        this.players = players;
        this.selectedName = selectedName;
    }

    public String[] count() {
        int size = players.size();
        resultVote = new String[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (players.get(i).equals(selectedName)) {
                count = count + 1;
                resultVote[i] = selectedName + " " + count;
            }
        }
        return resultVote;
    }
}
