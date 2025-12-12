package zgkprojekt.model;

import java.util.ArrayList;

import zgkprojekt.enums.EventType;

public class PlayingField {
    private ArrayList<Field> track;
    private ArrayList<Player> players; // 1, 2, 3, 4
    private ArrayList<Endzone> endzones;
    private ArrayList<Home> homes;
    private Event currentMapEvent;
    private Player activePlayer;

    public PlayingField(ArrayList<Field> track, ArrayList<Player> players) {
        this.track = track;
        this.players = players;
    }

    


    public ArrayList<Player> getPlayers()
    {
        return this.players == null ? new ArrayList<Player>() : this.players;
    }
}
