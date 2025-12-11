package zgkprojekt.service;

import zgkprojekt.model.PlayingField;

public class MainService {

    private static MainService _instance;

    private PlayingField _playingField;

    private MainService()
    {
        _playingField = new PlayingField();
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
    }

    public void useItemOptional() {
    }

    public void useDice() {
    }

    public void makeFigureMove() {
    }

    public void triggerEventFieldCheck() {
    }

    public boolean checkForWinner() {
        return false;
    }

    public void saveDataToDataBase() {
    }
}
