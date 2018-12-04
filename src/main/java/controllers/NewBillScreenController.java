package controllers;

import java.util.function.UnaryOperator;

import Exceptions.ButtonNotFoundException;
import FileSupport.BillFileWriter;
import FileSupport.ProductFileLoader;
import contents.Bill;
import contents.Product;
import contents.TableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NewBillScreenController
{
    @FXML
    private AnchorPane newBillScreen;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textField;
    @FXML
    private Label unitLabel;
    @FXML
    private Label sumLabel;


    @FXML
    private TableView<TableRow> tableView;
    @FXML
    private TableColumn<TableRow, String> nameColumn;
    @FXML
    private TableColumn<TableRow, Integer> quantityColumn;
    @FXML
    private TableColumn<TableRow, String> unitColumn;
    @FXML
    private TableColumn<TableRow, Integer> totalCostColumn;
    @FXML
    private TableColumn<TableRow, Integer> priceColumn;


    @FXML
    private Button applyButton;
    @FXML
    private Button addToHistoryButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button deleteButton;





    @FXML
    public void onMouseEntered(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getTarget().equals(applyButton)) { applyButton.setStyle("-fx-background-color: #00d400"); }
        else if(e.getTarget().equals(clearButton)) { clearButton.setStyle("-fx-background-color: #a1a1a1"); }
        else if(e.getTarget().equals(deleteButton)) { deleteButton.setStyle("-fx-background-color: #d90000"); }
        else if(e.getTarget().equals(addToHistoryButton)) { addToHistoryButton.setStyle("-fx-background-color: #00d400"); }
        else { throw new ButtonNotFoundException(); }
    }
    @FXML
    public void onMouseExited(MouseEvent e)
    {
        applyButton.setStyle("-fx-background-color: #00eb00");
        clearButton.setStyle("-fx-background-color: #b3b3b3");
        deleteButton.setStyle("-fx-background-color: #f20000");
        addToHistoryButton.setStyle("-fx-background-color: #00eb00");
    }
    @FXML
    public void onActionComboBox(ActionEvent e)
    {
        loadDataToUnitLabel();
    }
    @FXML
    public void onMouseClicked(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getSource().equals(applyButton))
        {
            if(textField.getText().equals(null) || textField.getText().equals("")) {}
            else
            {
                loadDataToListOfTableRows();
                loadDataToTableView();
                comboBox.setValue("");
                unitLabel.setText("");
                textField.setText("");
                loadDataToSumLabel();
            }
        }
        else if(e.getSource().equals(clearButton))
        {
            clear();
        }
        else if(e.getSource().equals(deleteButton))
        {
            TableRow tableRow = tableView.getSelectionModel().getSelectedItem();
            listOfTableRows.remove(tableRow);
            loadDataToSumLabel();
        }
        else if(e.getSource().equals(addToHistoryButton))
        {
            Bill bill = new Bill(listOfTableRows, Double.parseDouble(sumLabel.getText()));
            BillFileWriter.getInstance().writeBill(bill);

            clear();
        }
        else { throw new ButtonNotFoundException(); }
    }



    private ObservableList<TableRow> listOfTableRows;


    public void initialize()
    {
        UnaryOperator<Change> integerFilter = change ->
        {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { return change; }
            return null;
        };

        textField.setTextFormatter(new TextFormatter<Integer>(integerFilter));

        nameColumn.setCellValueFactory(new PropertyValueFactory<TableRow, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<TableRow, String>("unit"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("totalCost"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("price"));

        listOfTableRows = FXCollections.observableArrayList();
    }

    private MainScreenController mainScreenController;

    public void setVisible(boolean b)
    {
        newBillScreen.setVisible(b);
    }

    public void setMainController(MainScreenController mainScreenController)
    {
        this.mainScreenController = mainScreenController;
    }

    public void setSumLabel(String sum)
    {
        sumLabel.setText(sum);
    }

    public void clear()
    {
        tableView.getItems().clear();
        loadDataToSumLabel();
        comboBox.setValue("");
        unitLabel.setText("");
        textField.setText("");
    }





    public void loadDataToComboBox()
    {
        comboBox.getItems().clear();
        comboBox.setPromptText("Select Product");
        comboBox.getItems().addAll(ProductFileLoader.getInstance().getProductsNamesList());
    }

    public void loadDataToUnitLabel()
    {
        if(!comboBox.getValue().equals("Select Product") && !comboBox.getValue().equals(""))
        {
            Product product = ProductFileLoader.getInstance().getSingleProduct((String) comboBox.getValue());
            unitLabel.setText(product.getUnit());
        }
    }

    public void loadDataToListOfTableRows()
    {
        Product product = ProductFileLoader.getInstance().getSingleProduct((String) comboBox.getValue());

        if(textField.getText().equals(null) || textField.getText().equals("")) {}
        else
        {
            Integer quantity =  Integer.parseInt(textField.getText());
            listOfTableRows.add(new TableRow(product, quantity));
        }
    }

    public void loadDataToTableView()
    {
        tableView.setItems(listOfTableRows);
    }

    public void loadDataToTableView(ObservableList<TableRow> list)
    {
        listOfTableRows = list;
        tableView.setItems(listOfTableRows);
    }

    public void loadDataToSumLabel()
    {
        Double sum = 0.0;
        ObservableList<TableRow> list = tableView.getItems();
        for(TableRow x : list)
        {
            sum += x.getTotalCost();
        }
        sumLabel.setText(sum.toString());
    }


}
