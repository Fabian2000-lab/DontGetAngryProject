package zgkprojekt.service;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import kotlin.Pair;
import zgkprojekt.enums.FieldType;
import zgkprojekt.model.*;
import javafx.scene.Scene;

import java.util.*;
import java.util.stream.Collectors;

public class MainService {

    private static MainService _instance;

    private PlayingField _playingField;
    private Scene _scene;
    private String[] playerNames;
    private HashMap<Player, Integer> orderMap = new HashMap<>();

    private MainService()
    {
        _playingField = null;
        _scene = null;
    }

    public static MainService getInstance()
    {
        if(_instance == null)
            _instance = new MainService();

        return _instance;
    }

    public Scene getScene(){
        return _scene;
    }

    public void setScene(Scene scene){
        _scene = scene;
    }

    public int getPlayerCount()
    {
        return _playingField.getPlayers().size();
    }

    public void mapEvent() {
        return;
    }

    public void useItemOptional() {
        return;
    }

    public void useDice() {
        Dice.roll();
    }

    public void makeFigureMove() {
        return;
    }

    public void triggerEventFieldCheck() {
        return;
    }

    public boolean checkForWinner() {
        return false;
    }

    public void saveDataToDataBase() {
        return;
    }

    public void fillPlayingBoard(GridPane mainPane) {

        ArrayList<Field> fields = new ArrayList<Field>();
        ArrayList<Player> players = new ArrayList<Player>();
        _playingField = new PlayingField(fields, players);
        _playingField.setActivePlayerQueue(new ArrayDeque<Player>());

        ArrayList<Endzone> allEndzones = new ArrayList<>();
        ArrayList<Home> allHomes = new ArrayList<>();

        for(int i = 0; i < 4; i++)
        {
            Endzone anEndzone = new Endzone(new ArrayList<Field>());
            Home aHome = new Home(new ArrayList<Field>());

            allEndzones.add(anEndzone);
            allHomes.add(aHome);
        }



        for(var a : mainPane.getChildren()){
            if(a instanceof Circle b){
                Field tmp = null;
                String valueOfCircle = b.idProperty().getValue();

                if(valueOfCircle == null)
                    continue;

                int id = Integer.parseInt(valueOfCircle);

                //Trackfields, id 0-39
                if(id >= 0 && id <= 39)
                {
                    if(id % 10 == 0)
                        tmp = new Field(id, FieldType.PLAYER_1_START, b);
                    else
                        tmp = new Field(id, FieldType.STANDARD, b);

                    fields.add(tmp);
                }

                //Endzones, id 210 - 244
                if((id >= 210 && id <= 213) || (id >= 220 && id <= 223) || (id >= 230 && id <= 233) || (id >= 240 && id <= 243))
                {
                    tmp = new Field(id, FieldType.ENDZONE, b);
                    int indexEndzone = (id - 200) / 10;

                    allEndzones.get(indexEndzone - 1).getEndzones().add(tmp);
                }

                //Homes, id 110 - 144
                if((id >= 110 && id <= 113) || (id >= 120 && id <= 123) || (id >= 130 && id <= 133) || (id >= 140 && id <= 143))
                {
                    tmp = new Field(id, FieldType.HOME, b);
                    int indexHome = (id - 100) / 10;

                    allHomes.get(indexHome - 1).getHomeFields().add(tmp);
                }

            }
        }

        fields.sort(Comparator.comparingInt(Field::getId));

        for(int i = 0; i < playerNames.length; i++)
        {
            Player player = new Player(Integer.toString(i+1), playerNames[i], allEndzones.get(i), allHomes.get(i), fields.get(i * 10));

            players.add(player);
        }

        for(int i = 0; i < _playingField.getPlayers().size(); i++){

            PlayerFigure[] figures = _playingField.getPlayers().get(i).getPlayerFigures();
            ArrayList<Field> homes = allHomes.get(i).getHomeFields();

            for(int j = 0; j < 4; j++){

                figures[j].setPosition(homes.get(j));
                homes.get(j).setPlayer(figures[j]);
            }
        }
        _playingField.setHomes(allHomes);
        _playingField.setEndzones(allEndzones);

        int playerCount = getPlayerCount();

        for(int i = 0; i < playerCount; i++)
        {
            Home playerHome = _playingField.getPlayers().get(i).getHome();
            PlayerFigure[] playerFigures = _playingField.getPlayers().get(i).getPlayerFigures();
            Polygon definition = null;

            if(i == 0)
                definition = FigureDefinitions.PLAYER1;
            if(i == 1)
                definition = FigureDefinitions.PLAYER2;
            if(i == 2)
                definition = FigureDefinitions.PLAYER3;
            if(i == 3)
                definition = FigureDefinitions.PLAYER4;

            for(int j = 0; j < 4; j++)
            {
                Polygon tmp = new Polygon();

                tmp.setOnMouseClicked(this::handlePolygonClick);

                tmp.setFill(definition.getFill());
                tmp.setScaleX(definition.getScaleX());
                tmp.setScaleY(definition.getScaleY());
                tmp.setStrokeType(definition.getStrokeType());
                tmp.setStroke(definition.getStroke());
                tmp.setStrokeWidth(definition.getStrokeWidth());
                tmp.getPoints().addAll(definition.getPoints());

                //Hover effect
                tmp.setOnMouseEntered(e -> {
                    tmp.setEffect(new DropShadow(27,10,10,Color.web("#006666")));
                });
                tmp.setOnMouseExited(e -> {
                    tmp.setEffect(null);
                });

                Circle circleInfo = playerHome.getHomeFields().get(j).getCircle();

                Integer colIndex = GridPane.getColumnIndex(circleInfo);
                Integer rowIndex = GridPane.getRowIndex(circleInfo);

                HPos hpos = GridPane.getHalignment(circleInfo);
                VPos vpos = GridPane.getValignment(circleInfo);

                GridPane.setHalignment(tmp, hpos);
                GridPane.setValignment(tmp, vpos);

                if(colIndex == null)
                    colIndex = 0;
                if(rowIndex == null)
                    rowIndex = 0;

                playerFigures[j].setPolygon(tmp);
                mainPane.add(tmp, colIndex, rowIndex);

                playerFigures[j].setOwner(_playingField.getPlayers().get(i));
            }
        }

        System.out.printf("%s würfeln um eine Reinfolge zu bestimmen.%n", _playingField.getPlayers().get(0).getName());

    }

    private void handlePolygonClick(MouseEvent mouseEvent) {
        Polygon polygon = (Polygon) mouseEvent.getSource();

        PlayerFigure player = findFigureViaPolygon(polygon);
        Field field = player.getPosition();

        if(_playingField.getActivePlayer() != player.getOwner())
            return;

        Field newPosition = null;
        boolean validMove = false;
        PlayerFigure collisionObject = null;

        int currentFieldId = field.getId();


        if(Dice.getCurrentDiceRoll() == 6 && ((currentFieldId >= 110 && currentFieldId <= 113) || (currentFieldId >= 120 && currentFieldId <= 123) || (currentFieldId >= 130 && currentFieldId <= 133) || (currentFieldId >= 140 && currentFieldId <= 143)))
        {
            newPosition = _playingField.getActivePlayer().getStartField();

        }else if((currentFieldId >= 210 && currentFieldId <= 213) || (currentFieldId >= 220 && currentFieldId <= 223) || (currentFieldId >= 230 && currentFieldId <= 233) || (currentFieldId >= 240 && currentFieldId <= 243)){

            var endzone = _playingField.getActivePlayer().getEnzone().getEndzones();

            for(int i = 0; i < endzone.size(); i++){
                if(endzone.get(i).getPlayer() == player)
                    {
                    int newIndexPosition = Dice.getCurrentDiceRoll() + i;

                    //if(newIndexPosition < 4)
                        newPosition = endzone.get(i + 1);
                break;
                }
            }
        }
        else if (currentFieldId < 40) {

            //Check if player goes into Endzone
            boolean hasBeenInEndzoneCheck = false;
            var endzone = _playingField.getActivePlayer().getEnzone().getEndzones();

            for(int i = 1; i <= Dice.getCurrentDiceRoll(); i++){

                if(_playingField.getActivePlayer().isEntranceToEndone(_playingField.getTrack().get((field.getId() + i ) % 40))){

                    //Now check for OutOfBounds
                    int postitionCheckInEndzone = Dice.getCurrentDiceRoll() - i;

                    if(postitionCheckInEndzone < 4 )
                        newPosition = endzone.get(postitionCheckInEndzone);

                    hasBeenInEndzoneCheck = true;
                }

            }
            if(!hasBeenInEndzoneCheck)
                newPosition = _playingField.getTrack().get((currentFieldId + Dice.getCurrentDiceRoll()) % 40);
        }

        Pair<Boolean, PlayerFigure> pair = null;

        if(newPosition == null)
        {
            System.out.println("OutOfBounds");
            return;
        }


        pair = collisionCheck(newPosition);

        if(pair.getFirst())
            System.out.println("COLISSION!!");

        if(!pair.getFirst() || (pair.getFirst() && _playingField.getActivePlayer() != pair.getSecond().getOwner()))
            validMove = true;

        if(validMove)
        {
            moveTo(player, newPosition);
            _playingField.nextPlayer();

            System.out.printf("%s ist an der Reihe.%n", _playingField.getActivePlayer().getName());
        }
    }

    private Pair<Boolean, PlayerFigure> collisionCheck(Field field) {
        //return true if there is a collision and with which PlayerFigure

        Pair<Boolean, PlayerFigure> newPair = new Pair<Boolean, PlayerFigure>(false, null);

        if(field.getPlayer() != null)
            newPair = new Pair<Boolean, PlayerFigure>(true, field.getPlayer());

        return newPair;
    }

    private PlayerFigure findFigureViaPolygon(Polygon polygon) {

        PlayerFigure player = null;

        var track = _playingField.getTrack();
        var homes = _playingField.getHomes();
        var endzones = _playingField.getEndzones();

        for(var trackField : track)
        {
            if(trackField.getPlayer() != null && trackField.getPlayer().getPolygon() == polygon)
            {
                player = trackField.getPlayer();
            }
        }

        for(var home : homes)
        {
            for(var homeField : home.getHomeFields()){

                if(homeField.getPlayer() != null && homeField.getPlayer().getPolygon() == polygon)
                {
                    player = homeField.getPlayer();
                }
            }
        }
        for(var endzone : endzones)
        {
            for(var endzoneField : endzone.getEndzones()){

                if(endzoneField.getPlayer() != null && endzoneField.getPlayer().getPolygon() == polygon)
                {
                    player = endzoneField.getPlayer();
                }
            }
        }

        return player;
    }

    private void moveTo(PlayerFigure source, Field dest)
    {
        // Get circle center in parent coordinates
        Integer colIndex = GridPane.getColumnIndex(dest.getCircle());
        Integer rowIndex = GridPane.getRowIndex(dest.getCircle());

        GridPane.setColumnIndex(source.getPolygon(), colIndex);
        GridPane.setRowIndex(source.getPolygon(), rowIndex);

        Field fieldToDeletePlayer = source.getPosition();

        source.setPosition(dest);
        fieldToDeletePlayer.setPlayer(null);

        dest.setPlayer(source);
    }

    public void playerRegistrationAddPlayer(Pane mainPane, int player) {

        TextField newPlayerTextField  = new TextField();
        Font font = new Font("Constantia", 20);
        TextField textFieldInfo = new TextField();


        //move Buttons a little down
        for(var a : mainPane.getChildren()){
            if(a instanceof Button b){
                b.setLayoutY( b.getLayoutY() + 75 );
            }
            if(a instanceof TextField b){
                textFieldInfo = b;
            }
        }

        newPlayerTextField.setFont(textFieldInfo.getFont());
        newPlayerTextField.setLayoutX(textFieldInfo.getLayoutX());
        newPlayerTextField.setLayoutY(textFieldInfo.getLayoutY() + 75);
        newPlayerTextField.setPrefHeight(textFieldInfo.getPrefHeight());
        newPlayerTextField.setPrefWidth(textFieldInfo.getPrefWidth());
        newPlayerTextField.setPromptText(String.format("Enter player %d name", player));


        mainPane.getChildren().add(newPlayerTextField);

    }

    public void playerRegistrationRemovePlayer(Pane mainPane) {

        int index = 0;
        int indexToRemove = 0;

        //move Buttons a little down
        for(var a : mainPane.getChildren()){
            if(a instanceof Button b){
                b.setLayoutY( b.getLayoutY() - 75 );
            }
            if(a instanceof TextField b){
                indexToRemove = index;
            }
            index++;
        }

        mainPane.getChildren().remove(indexToRemove);
    }

    public boolean finalizePlayerNames(Pane mainPane, int playerCount) {

        String[] playerNamestmp = new String[playerCount];
        int idx = 0;

        for(var a : mainPane.getChildren()){
            if(a instanceof TextField b){
                if(b.getText().isEmpty())
                    return false;

                playerNamestmp[idx] = b.getText();
                idx++;
            }
        }

        playerNames = playerNamestmp;

        return true;
    }

    public void decidePlayingOrder() {

        _playingField.getPlayers();


    }

    public void diceButton() {

        if(_playingField.getPlayers().size() == orderMap.size()){
            Dice.roll();
            System.out.printf("%s rolled a %d%n",_playingField.getActivePlayer().getName(), Dice.getCurrentDiceRoll());
        }
        //Game is still deciding the order of the players
        else
        {
            do {
                Dice.roll();
            } while (orderMap.containsValue(Dice.getCurrentDiceRoll()));

            System.out.printf("%s rolled a %d%n",_playingField.getPlayers().get(orderMap.size()).getName(), Dice.getCurrentDiceRoll());

            if(_playingField.getPlayers().size() > orderMap.size() + 1)
                System.out.printf("Als nächstes würfelt %s%n", _playingField.getPlayers().get(orderMap.size() + 1).getName());

            orderMap.put(_playingField.getPlayers().get(orderMap.size()), Dice.getCurrentDiceRoll());

            if(orderMap.size() == _playingField.getPlayers().size())
            {
                //Erstellt eine queue aus der Map, sortiert nach Integer Value in der Hashmap.
                ArrayDeque<Player> queue =
                        orderMap.entrySet()
                                .stream()
                                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed())
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toCollection(ArrayDeque::new));

                _playingField.setActivePlayerQueue(queue);

                for(int i = 0; i < _playingField.getPlayers().size(); i++) {
                    System.out.printf("Reinfolge: %d. %s%n", i+1, _playingField.getActivePlayer().getName());
                    _playingField.nextPlayer();
                }

                System.out.printf("%n%s Beginnt!%n",_playingField.getActivePlayer().getName());

            }
        }


    }

    public static class FigureDefinitions{
        public static final Polygon PLAYER1;
        public static final Polygon PLAYER2;
        public static final Polygon PLAYER3;
        public static final Polygon PLAYER4;

        static
        {
            PLAYER1 = new Polygon();
            PLAYER1.setFill(Color.LIGHTBLUE);
            PLAYER1.getPoints().addAll(100.0, 50.0, 135.35, 64.64, 150.0, 100.0, 135.35, 135.35, 100.0, 150.0, 64.64, 135.35, 50.0, 100.0, 64.64, 64.64);
            PLAYER1.setScaleX(0.4);
            PLAYER1.setScaleY(0.4);
            PLAYER1.setStroke(Color.BLACK);
            PLAYER1.setStrokeType(StrokeType.INSIDE);
            PLAYER1.setStrokeWidth(3.0);

            PLAYER2 = new Polygon();
            PLAYER2.setFill(Color.rgb(255,255,102));
            PLAYER2.getPoints().addAll(100.0, 50.0, 135.35, 64.64, 150.0, 100.0, 135.35, 135.35, 100.0, 150.0, 64.64, 135.35, 50.0, 100.0, 64.64, 64.64);
            PLAYER2.setScaleX(0.4);
            PLAYER2.setScaleY(0.4);
            PLAYER2.setStroke(Color.BLACK);
            PLAYER2.setStrokeType(StrokeType.INSIDE);
            PLAYER2.setStrokeWidth(3.0);

            PLAYER3 = new Polygon();
            PLAYER3.setFill(Color.CORAL);
            PLAYER3.getPoints().addAll(100.0, 50.0, 135.35, 64.64, 150.0, 100.0, 135.35, 135.35, 100.0, 150.0, 64.64, 135.35, 50.0, 100.0, 64.64, 64.64);
            PLAYER3.setScaleX(0.4);
            PLAYER3.setScaleY(0.4);
            PLAYER3.setStroke(Color.BLACK);
            PLAYER3.setStrokeType(StrokeType.INSIDE);
            PLAYER3.setStrokeWidth(3.0);

            PLAYER4 = new Polygon();
            PLAYER4.setFill(Color.rgb(255,110,180));
            PLAYER4.getPoints().addAll(100.0, 50.0, 135.35, 64.64, 150.0, 100.0, 135.35, 135.35, 100.0, 150.0, 64.64, 135.35, 50.0, 100.0, 64.64, 64.64);
            PLAYER4.setScaleX(0.4);
            PLAYER4.setScaleY(0.4);
            PLAYER4.setStroke(Color.BLACK);
            PLAYER4.setStrokeType(StrokeType.INSIDE);
            PLAYER4.setStrokeWidth(3.0);
        }
    }
}
