package controllers;

import javafx.fxml.FXML;

public class MainScreenController
{
    // CONTROLLERS
    @FXML
    private NewBillScreenController newBillScreenController;
    @FXML
    private BillsHistoryScreenController billsHistoryScreenController;
    @FXML
    private NewProductScreenController newProductScreenController;
    @FXML
    private ProductsListScreenController productsListScreenController;
    @FXML
    private DescriptionScreenController descriptionScreenController;
    @FXML
    private MenuScreenController menuScreenController;




    // GETTERS
    public NewBillScreenController getNewBillScreenController() { return newBillScreenController; }

    public BillsHistoryScreenController getBillsHistoryScreenController()
    {
        return billsHistoryScreenController;
    }

    public NewProductScreenController getNewProductScreenController()
    {
        return newProductScreenController;
    }

    public ProductsListScreenController getProductsListScreenController()
    {
        return productsListScreenController;
    }

    public DescriptionScreenController getDescriptionScreenController()
    {
        return descriptionScreenController;
    }

    public MenuScreenController getMenuScreenController()
    {
        return  menuScreenController;
    }




    @FXML
    private void initialize()
    {
        menuScreenController.setMainController(this);
        newBillScreenController.setMainController(this);
        billsHistoryScreenController.setMainController(this);
        descriptionScreenController.setMainController(this);
        newProductScreenController.setMainController(this);
        productsListScreenController.setMainController(this);

        descriptionScreenController.setVisible(true);
        menuScreenController.setVisible(true);
        newBillScreenController.setVisible(false);
        billsHistoryScreenController.setVisible(false);
        newProductScreenController.setVisible(false);
        productsListScreenController.setVisible(false);
    }
}
