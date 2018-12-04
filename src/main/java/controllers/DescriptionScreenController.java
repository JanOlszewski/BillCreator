package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DescriptionScreenController
{
    @FXML
    private AnchorPane descriptionScreen;

    private MainScreenController mainScreenController;

    public void setVisible(boolean b)
    {
        descriptionScreen.setVisible(b);
    }

    public void setMainController(MainScreenController mainScreenController)
    {
        this.mainScreenController = mainScreenController;
    }
}
