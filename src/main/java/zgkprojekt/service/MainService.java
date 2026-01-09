package zgkprojekt.service;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import zgkprojekt.enums.FieldType;
import zgkprojekt.model.*;
import javafx.scene.Scene;

import java.util.ArrayList;

public class MainService {

    private static MainService _instance;

    private PlayingField _playingField;
    private Scene _scene;
    private String[] playerNames;

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
        return;
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

        ArrayList<Endzone> allEndzones = new ArrayList<>();
        ArrayList<Home> allHomes = new ArrayList<>();

        for(int i = 0; i < 4; i++)
        {
            Endzone anEndzone = new Endzone(new ArrayList<Field>());
            Home aHome = new Home(new ArrayList<Field>());

            allEndzones.add(anEndzone);
            allHomes.add(aHome);
        }

        for(int i = 0; i < playerNames.length; i++)
        {
            Player player = new Player(Integer.toString(i+1), playerNames[i], allEndzones.get(i), allHomes.get(i));

            players.add(player);
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
            }
        }
    }
    private void moveTo(Polygon source, Circle dest)
    {
        // Get circle center in parent coordinates
        Integer colIndex = GridPane.getColumnIndex(dest);
        Integer rowIndex = GridPane.getRowIndex(dest);

        GridPane.setColumnIndex(source, colIndex);
        GridPane.setRowIndex(source, rowIndex);
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
