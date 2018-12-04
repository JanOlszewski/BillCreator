package controllers;

import java.util.function.UnaryOperator;

import Exceptions.ButtonNotFoundException;
import FileSupport.ProductFileLoader;
import FileSupport.ProductFileWriter;
import contents.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NewProductScreenController
{
    @FXML
    private AnchorPane newProductScreen;
    @FXML
    private TextArea nameTextArea;
    @FXML
    private TextArea priceTextArea;
    @FXML
    private TextArea unitTextArea;
    @FXML
    private Button clearButton;
    @FXML
    private Button applyButton;

    private Product pproduct;



    @FXML
    public void onMouseEntered(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getTarget().equals(applyButton)) { applyButton.setStyle("-fx-background-color: #00d400"); }
        else if(e.getTarget().equals(clearButton)) { clearButton.setStyle("-fx-background-color: #a1a1a1"); }
        else { throw new ButtonNotFoundException(); }
    }

    @FXML
    public void onMouseExited(MouseEvent e)
    {
        applyButton.setStyle("-fx-background-color: #00eb00");
        clearButton.setStyle("-fx-background-color: #b3b3b3");
    }

    @FXML
    public void onMouseClicked(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getSource().equals(applyButton))
        {
            boolean b = true;
            if(nameTextArea.getText().equals("") || nameTextArea.getText().equals(null)) { b = false; }
            if(unitTextArea.getText().equals("") || unitTextArea.getText().equals(null)) { b = false; }
            if(priceTextArea.getText().equals("") || priceTextArea.getText().equals(null)) { b = false; }

            if(b == true)
            {
                if(!(pproduct == null))
                {
                    ObservableList<String> tlist = FXCollections.observableArrayList();
                    tlist.add(pproduct.getName());
                    ProductFileLoader.getInstance().removeProductFiles(tlist);
                    pproduct = null;
                }

                Product product;
                product = new Product(nameTextArea.getText(), unitTextArea.getText(),
                        Double.parseDouble(priceTextArea.getText()));

                ProductFileWriter.getInstance().writeProduct(product);
                clear();
            }
        }
        else if(e.getSource().equals(clearButton))
        {
            clear();
            pproduct = null;
        }
        else
        {
            throw new ButtonNotFoundException();
        }
    }





    public void initialize()
    {
        UnaryOperator<Change> doubleFilter = change ->
        {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9-.]*")) { return change; }
            return null;
        };

        UnaryOperator<Change> characterFilter = change ->
        {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Zóśćżźęął ]*")) { return change; }
            return null;
        };


        priceTextArea.setTextFormatter(new TextFormatter<Double>(doubleFilter));
        unitTextArea.setTextFormatter(new TextFormatter<String>(characterFilter));
    }





    private MainScreenController mainScreenController;

    public void setVisible(boolean b)
    {
        newProductScreen.setVisible(b);
    }

    public void setMainController(MainScreenController mainScreenController)
    {
        this.mainScreenController = mainScreenController;
    }

    public void setTextOnFields(String name, String price, String unit)
    {
        nameTextArea.setText(name);
        priceTextArea.setText(price);
        unitTextArea.setText(unit);
    }

    public void setProduct(Product pproduct)
    {
        this.pproduct = pproduct;
    }

    public void clear()
    {
        nameTextArea.setText("");
        unitTextArea.setText("");
        priceTextArea.setText("");
    }
}
