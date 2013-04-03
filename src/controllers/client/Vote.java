package controllers.client;

import java.util.List;

public class Vote {
    private final List<String> players;
    private final String selectedName;

    public Vote(List<String> players, String selectedName) {
        this.players = players;
        this.selectedName = selectedName;
    }

    public int count(){
        int size = players.size();
        String[] resultVote = new String[size];
        int count = 0;
        for (int i = 0; i < size;i++){
            if (players.get(i).equals(selectedName)){
                count=count+1;
                resultVote[i] = selectedName + " " + count;
                System.out.println(resultVote[i]);
            }
        }
        return 0;
    }
}
