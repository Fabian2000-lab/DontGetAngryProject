package zgkprojekt.service;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
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
        players.add(player1);


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
                        tmp = new Field(id, FieldType.PLAYER_START, b);
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
    }
}
