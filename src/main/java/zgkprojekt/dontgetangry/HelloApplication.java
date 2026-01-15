package zgkprojekt.dontgetangry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zgkprojekt.controller.MainController;
import zgkprojekt.service.MainService;

import java.io.IOException;

public class HelloApplication extends Application {

    private MainService _service;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/zgkprojekt/view/network-select.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        MainService _service = MainService.getInstance();
        _service.setScene(scene);

        stage.setTitle("DontGetAngry™");
        stage.setScene(scene);
        stage.show();
    }
}
