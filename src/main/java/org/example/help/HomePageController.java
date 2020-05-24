package org.example.help;

import javafx.fxml.FXML;
import java.io.IOException;

public class HomePageController {

    @FXML
    private void switchToPlay() throws IOException {
        App.setRoot("play");
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("create");
    }
}
