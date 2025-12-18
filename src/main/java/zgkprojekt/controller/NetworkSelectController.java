package zgkprojekt.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import zgkprojekt.dontgetangry.HelloApplication;
import zgkprojekt.service.MainService;

import java.io.IOException;

public class NetworkSelectController {

    private MainService _service;

    @FXML
    public void handleStartLocalGame() throws IOException {

        _service = MainService.getInstance();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/zgkprojekt/view/add-players.fxml")
        );

        Parent newRoot = loader.load();

        _service.getScene().setRoot(newRoot);
    }
}
