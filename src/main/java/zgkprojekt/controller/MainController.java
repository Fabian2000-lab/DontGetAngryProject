package zgkprojekt.controller;

import zgkprojekt.service.MainService;

public class MainController {

    private static MainService _service;

    public static void startGameLoop()
    {
        _service = MainService.getInstance();
        int playerCount = _service.getPlayerCount();
        boolean winner = false;

        while(!winner){

            _service.mapEvent();

            for(int i = 0; i < playerCount; i++){

                _service.useItemOptional();
                _service.useDice();
                _service.makeFigureMove();
                _service.triggerEventFieldCheck();

                if(_service.checkForWinner())
                {
                    winner = true;
                    break;
                }
            }
        }

        _service.saveDataToDataBase();

    }
}
