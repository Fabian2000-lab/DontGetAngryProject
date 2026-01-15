package zgkprojekt.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import javafx.scene.control.TextArea;
import zgkprojekt.enums.EventType;

import javax.swing.*;

public class PlayingField {
    private ArrayList<Field> track;
    private ArrayList<Player> players; // 1, 2, 3, 4
    private ArrayList<Endzone> endzones;
    private ArrayList<Home> homes;
    private Event currentMapEvent;
    private ArrayDeque<Player> activePlayer;
    private TextArea console;

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

    public void setConsole(TextArea console) {
        this.console = console;
    }

    public void log(String message) {
        console.appendText(message + "\n");
    }

    public Event getCurrentMapEvent() {
        return this.currentMapEvent;
    }

    public void setActivePlayerQueue(ArrayDeque<Player> player) {
        this.activePlayer = player;
    }

    public ArrayDeque<Player> getActivePlayerQueue() {
        return this.activePlayer;
    }

    public Player getActivePlayer() {
        return this.activePlayer.peek();
    }

    public void nextPlayer()
    {
        Player tmp = activePlayer.poll();
        activePlayer.add(tmp);
    }


    public void setEndzones(ArrayList<Endzone> endzones) {
        this.endzones = endzones;
    }
}
