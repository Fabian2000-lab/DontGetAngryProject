package zgkprojekt.model;

import java.util.ArrayList;

import zgkprojekt.enums.EventType;

public class PlayingField {
    private ArrayList<Field> track;
    private ArrayList<Endzone> endzones;
    private ArrayList<Home> homes;
    private Event currentMapEvent;
    private ArrayList<Player> players;
    private Player activePlayer;

}
