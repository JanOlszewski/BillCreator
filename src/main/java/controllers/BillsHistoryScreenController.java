package controllers;

import Exceptions.ButtonNotFoundException;
import FileSupport.BillFileLoader;
import contents.Bill;
import contents.TableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BillsHistoryScreenController
{
    @FXML
    private AnchorPane billsHistoryScreen;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;



    private MainScreenController mainScreenController;

    public void setVisible(boolean b)
    {
        billsHistoryScreen.setVisible(b);
    }

    public void setMainController(MainScreenController mainScreenController)
    {
        this.mainScreenController = mainScreenController;
    }

    public void loadBillsToListView()
    {
        listView.getItems().clear();
        listView.getItems().addAll(BillFileLoader.getInstance().getBillsNamesList());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }






    @FXML
    public void onMouseClicked(MouseEvent e) throws ButtonNotFoundException
    {
        if(e.getSource().equals(deleteButton))
        {
            ObservableList<String> selectedBills;
            selectedBills = listView.getSelectionModel().getSelectedItems();
            BillFileLoader.getInstance().removeBillsFiles(selectedBills);
            this.loadBillsToListView();
        }
        else if(e.getSource().equals(editButton))
        {
            String name = listView.getSelectionModel().getSelectedItem();

            if(!name.equals("") && !name.equals(null))
            {
                ObservableList<TableRow> list = FXCollections.observableArrayList();

                Bill bill = BillFileLoader.getInstance().getSingleBill(name);
                mainScreenController.getNewBillScreenController().loadDataToTableView(bill.getTableRowsList());
                mainScreenController.getNewBillScreenController().setSumLabel(Double.toString(bill.getSum()));
                mainScreenController.getNewBillScreenController().loadDataToComboBox();

                setVisible(false);
                mainScreenController.getNewBillScreenController().setVisible(true);
            }
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
}
