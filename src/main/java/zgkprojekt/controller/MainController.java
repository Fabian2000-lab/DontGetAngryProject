package zgkprojekt.controller;

import zgkprojekt.service.MainService;

public class MainController {

    private static MainService _service;

    public static void startGameLoop()
    {
        _service = MainService.getInstance();
        boolean winner = false;

        while(!winner){

            _service.mapEvent();
            _service.useItemOptional();
            _service.useDice();
            _service.makeFigureMove();
            _service.triggerEventFieldCheck();
            winner = _service.checkForWinner();

        }

        _service.saveDataToDataBase();

    }
}
