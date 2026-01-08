package zgkprojekt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import zgkprojekt.service.MainService;

import java.io.IOException;

public class AddPlayersController {

    MainService _service;

    private int currentPlayers = 2;

    @FXML
    public Pane mainPane;

    @FXML
    public void handleStartGameButton(ActionEvent actionEvent) throws IOException {

        _service = MainService.getInstance();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/zgkprojekt/view/board.fxml")
        );

        Parent newRoot = loader.load();

        _service.getScene().setRoot(newRoot);
    }

    @FXML
    public void handleAddPlayer(ActionEvent actionEvent) throws IOException {

        if(currentPlayers + 1 > 4)
            return;

        _service = MainService.getInstance();

        _service.playerRegistrationAddPlayer(mainPane, currentPlayers + 1);
        currentPlayers++;
    }

    @FXML
    public void handleRemovePlayer(ActionEvent actionEvent) throws IOException {

        if(currentPlayers - 1 < 2)
            return;

        _service = MainService.getInstance();

        _service.playerRegistrationRemovePlayer(mainPane);
        currentPlayers--;
    }
}
