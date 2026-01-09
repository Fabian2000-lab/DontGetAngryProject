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
        return this.players == null ? new ArrayList<Player>(4) : this.players;
    }

    public ArrayList<Field> getTrack() {
        return this.track;
    }

    public ArrayList<Endzone> getEndzones() {
        return this.endzones;
    }
    
    public ArrayList<Home> getHomes() {
        return this.homes;
    }

    public void setHomes(ArrayList<Home> homes) {
        this.homes = homes;
    }

    public void setCurrentMapEvent(EventType eventType) {
        this.currentMapEvent = new Event(eventType, this.players.size());
    }

    public Event getCurrentMapEvent() {
        return this.currentMapEvent;
    }

    public void setActivePlayer(Player player) {
        this.activePlayer = player;
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }


    public void setEndzones(ArrayList<Endzone> endzones) {
        this.endzones = endzones;
    }
}
