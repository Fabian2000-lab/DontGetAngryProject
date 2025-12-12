package zgkprojekt.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ButtonEventHandlerController {
    @FXML
    private Button moveButton;

    @FXML
    private Label moveLabel;

    @FXML
    public void handleMoveButtonClick() {
        moveLabel.setText("Move button clicked");
    }
}
