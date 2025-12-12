package zgkprojekt.controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import zgkprojekt.service.MainService;

import java.util.ArrayList;

public class GameFieldController {

    private static MainService _service;

    @FXML
    private GridPane mainPane;

    @FXML
    private Button moveButton;

    @FXML
    private Label moveLabel;

    @FXML
    public void handleMoveButtonClick()
    {
        _service.fillPlayingBoard(mainPane);

    }
}
