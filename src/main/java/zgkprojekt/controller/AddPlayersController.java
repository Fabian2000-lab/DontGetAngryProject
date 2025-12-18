package zgkprojekt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import zgkprojekt.service.MainService;

import java.io.IOException;

public class AddPlayersController {

    MainService _service;

    @FXML
    public void handleStartGameButton(ActionEvent actionEvent) throws IOException {

        _service = MainService.getInstance();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/zgkprojekt/view/board.fxml")
        );

        Parent newRoot = loader.load();

        _service.getScene().setRoot(newRoot);



    }
}
