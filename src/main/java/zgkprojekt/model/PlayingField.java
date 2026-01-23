package zgkprojekt.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import zgkprojekt.enums.EventType;

import javax.swing.*;

public class PlayingField {
    private ArrayList<Field> track;
    private ArrayList<Player> players; // 1, 2, 3, 4
    private ArrayList<Endzone> endzones;
    private ArrayList<Home> homes;
    private Event currentMapEvent;
    private ArrayDeque<Player> activePlayer;
    // private TextArea console;
    private TextFlow textFlow;
    private ScrollPane scrollPane;

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

    //public void setConsole(TextArea console) {
    //    this.console = console;
    // }

    // public void log(String message) {
    //    console.appendText(message + "\n");
    // }

    public void setTextFlow(TextFlow textFlow, ScrollPane scrollPane) {
        this.textFlow = textFlow;
        this.scrollPane = scrollPane;
    }

    public void playerLog(String message, String name, Color nColor, Color mColor) {
        Text nameText = new Text(name);
        nameText.setFill(nColor);
        nameText.setFont(Font.font("Constantia Bold", FontWeight.BOLD, 15));

        Text messageText = new Text(message);
        messageText.setFill(mColor);
        messageText.setFont(Font.font("Constantia Bold", 15));

        Text newLine = new Text("\n");

        textFlow.getChildren().addAll(nameText, messageText, newLine);

        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }

    public void log(String message,  Color color) {
        Text text = new Text(message + "\n");
        text.setFill(color);

        text.setFont(Font.font("Constantia Bold", 15));
        textFlow.getChildren().add(text);

        scrollPane.layout();
        scrollPane.setVvalue(1.0);

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
