package controllers;

import Exceptions.ButtonNotFoundException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuScreenController
{
    @FXML
    private AnchorPane menuScreen;
    @FXML
    private Button newBillButton;
    @FXML
    private Button newProductButton;
    @FXML
    private Button productListButton;
    @FXML
    private Button billsHistoryButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button descriptionButton;



    @FXML
    public void onMouseEntered(MouseEvent e) throws ButtonNotFoundException
    {
        checkButton(e).setStyle("-fx-background-color: #2eb8e6");
    }

    @FXML
    public void onMouseExited(MouseEvent e) throws ButtonNotFoundException
    {
        checkButton(e).setStyle("-fx-background-color: #33ccff");
    }

    @FXML
    public void onMouseClicked(MouseEvent e) throws ButtonNotFoundException
    {
        mainScreenController.getDescriptionScreenController().setVisible(false);
        mainScreenController.getNewBillScreenController().setVisible(false);
        mainScreenController.getBillsHistoryScreenController().setVisible(false);
        mainScreenController.getNewProductScreenController().setVisible(false);
        mainScreenController.getProductsListScreenController().setVisible(false);

        checkScreen(e);
    }





    private MainScreenController mainScreenController;

    public void setMainController(MainScreenController mainScreenController)
    {
        this.mainScreenController = mainScreenController;
    }

    public void setVisible(boolean b)
    {
        menuScreen.setVisible(b);
    }




    private Button checkButton(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getTarget().equals(newBillButton)) { return newBillButton; }
        else if(e.getTarget().equals(productListButton)) { return productListButton; }
        else if(e.getTarget().equals(newProductButton)) { return newProductButton; }
        else if(e.getTarget().equals(billsHistoryButton)) { return billsHistoryButton; }
        else if(e.getTarget().equals(exitButton)) { return exitButton; }
        else if(e.getTarget().equals(descriptionButton)) { return descriptionButton; }
        else { throw new ButtonNotFoundException(); }
    }

    private void checkScreen(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getSource().equals(newBillButton))
        {
            mainScreenController.getNewBillScreenController().clear();
            mainScreenController.getNewBillScreenController().loadDataToComboBox();
            mainScreenController.getNewBillScreenController().setVisible(true);
        }
        else if(e.getSource().equals(productListButton))
        {
            mainScreenController.getProductsListScreenController().loadProductsToListView();
            mainScreenController.getProductsListScreenController().setVisible(true);
        }
        else if(e.getSource().equals(newProductButton))
        {
            mainScreenController.getNewProductScreenController().clear();
            mainScreenController.getNewProductScreenController().setVisible(true);
        }
        else if(e.getSource().equals(billsHistoryButton))
        {
            mainScreenController.getBillsHistoryScreenController().loadBillsToListView();
            mainScreenController.getBillsHistoryScreenController().setVisible(true);
        }
        else if(e.getSource().equals(descriptionButton))
        {
            mainScreenController.getDescriptionScreenController().setVisible(true);
        }
        else if(e.getSource().equals(exitButton)) { Platform.exit(); System.exit(0); }
        else { throw new ButtonNotFoundException(); }
    }
}
