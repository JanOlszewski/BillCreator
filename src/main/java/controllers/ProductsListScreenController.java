package controllers;

import Exceptions.ButtonNotFoundException;
import FileSupport.ProductFileLoader;
import contents.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductsListScreenController
{
    @FXML
    private AnchorPane productsListScreen;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    private MainScreenController mainScreenController;





    @FXML
    public void onMouseClicked(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getSource().equals(deleteButton))
        {
            ObservableList<String> selectedProducts;
            selectedProducts = listView.getSelectionModel().getSelectedItems();
            ProductFileLoader.getInstance().removeProductFiles(selectedProducts);
            loadProductsToListView();
        }
        else if(e.getSource().equals(editButton))
        {
            String selectedProduct = listView.getSelectionModel().getSelectedItem();
            Product product = ProductFileLoader.getInstance().getSingleProduct(selectedProduct);
            mainScreenController.getNewProductScreenController().setProduct(product);
            mainScreenController.getNewProductScreenController().setTextOnFields(product.getName(),
                    Double.toString(product.getPrice()), product.getUnit());
            mainScreenController.getNewProductScreenController().setVisible(true);
            setVisible(false);
        }
        else { throw new ButtonNotFoundException(); }
    }

    @FXML
    public void onMouseEntered(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getTarget().equals(deleteButton)) { deleteButton.setStyle("-fx-background-color: #a1a1a1"); }
        else if(e.getTarget().equals(editButton)) { editButton.setStyle("-fx-background-color: #a1a1a1"); }
        else { throw new ButtonNotFoundException(); }
    }

    @FXML
    public void onMouseExited(MouseEvent e)
    {
        deleteButton.setStyle("-fx-background-color: #b3b3b3");
        editButton.setStyle("-fx-background-color: #b3b3b3");
    }





    public void setVisible(boolean b)
    {
        productsListScreen.setVisible(b);
    }

    public void setMainController(MainScreenController mainScreenController)
    {
        this.mainScreenController = mainScreenController;
    }

    public void loadProductsToListView()
    {
        listView.getItems().clear();
        listView.getItems().addAll(ProductFileLoader.getInstance().getProductsNamesList());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
