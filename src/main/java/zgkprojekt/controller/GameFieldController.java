package zgkprojekt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import zgkprojekt.service.MainService;

public class GameFieldController {

    private static MainService _service;
    @FXML
    private Button moveButton;

    @FXML
    private Label moveLabel;

    @FXML
    public void handleMoveButtonClick()
    {
        MainController.startGameLoop();
    }
}
