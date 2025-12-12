package zgkprojekt.service;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import zgkprojekt.enums.FieldType;
import zgkprojekt.model.*;

import java.util.ArrayList;

public class MainService {

    private static MainService _instance;

    private PlayingField _playingField;

    private MainService()
    {
        _playingField = null;
    }

    public static MainService getInstance()
    {
        if(_instance == null)
            _instance = new MainService();

        return _instance;
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

        Player player1 = new Player("1", "Fabian", allEndzones.get(0), allHomes.get(0));
        Player player2 = new Player("2", "Fabian2", allEndzones.get(1), allHomes.get(1));
        Player player3 = new Player("3", "Fabian3", allEndzones.get(2), allHomes.get(2));
        Player player4 = new Player("4", "Fabian4", allEndzones.get(3), allHomes.get(3));

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);


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
            PLAYER2.setFill(Color.CORAL);
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
            PLAYER4.setFill(Color.CORAL);
            PLAYER4.getPoints().addAll(100.0, 50.0, 135.35, 64.64, 150.0, 100.0, 135.35, 135.35, 100.0, 150.0, 64.64, 135.35, 50.0, 100.0, 64.64, 64.64);
            PLAYER4.setScaleX(0.4);
            PLAYER4.setScaleY(0.4);
            PLAYER4.setStroke(Color.BLACK);
            PLAYER4.setStrokeType(StrokeType.INSIDE);
            PLAYER4.setStrokeWidth(3.0);
        }
    }
}
