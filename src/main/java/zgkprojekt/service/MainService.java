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
}
