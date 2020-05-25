package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class EditingController {
    @FXML
    private void switchToCreateView() throws IOException{
        App.setRoot("createView");
    }
}
