package FileSupport;

import contents.Bill;
import contents.TableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class BillFileLoader
{
    private File billsDir;
    private String path;

    //Constructor
    private BillFileLoader()
    {
        path = System.getProperty("user.dir");
        billsDir = new File(path + "\\Bills");

        if(!billsDir.exists()) { billsDir.mkdir(); }
    }

    private static class ClassHolder
    {
        private static final BillFileLoader INSTANCE = new BillFileLoader();
    }

    public static BillFileLoader getInstance()
    {
        return ClassHolder.INSTANCE;
    }

    //Loader methods
    public File[] loadBillsDir()
    {
        File[] listOfFiles = billsDir.listFiles();
        return listOfFiles;
    }

    public ObservableList<Bill> getBillsList()
    {
        ObservableList<TableRow> tableRowList = null;
        double sum = 0;
        String name = null;

        ObservableList<Bill> billsList = FXCollections.observableArrayList();

        File[] listOfFiles = loadBillsDir();
        BufferedReader reader = null;
        String line;

        String[] tab = new String[4];
        ArrayList<String> list;
        int k = 0;
        for(File x : listOfFiles)
        {
            tableRowList = FXCollections.observableArrayList();
            list = new ArrayList<>();
            try
            {
                reader = new BufferedReader(new FileReader(x));
                while((line = reader.readLine()) != null)
                {
                    list.add(line);
                }
                for(int i = 0; i < list.size(); i++)
                {
                    if(list.get(i).equals("")) { k = 0; }
                    else if(i > 2) { tab[k] = list.get(i); k++; }
                    if(k == 4) { tableRowList.add(new TableRow(tab[0], tab[2],
                            Double.parseDouble(tab[3]), Integer.parseInt(tab[1]))); }
                }
                billsList.add(new Bill(tableRowList, Double.parseDouble(list.get(1)), list.get(0)));
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return billsList;
    }

    public ObservableList<String> getBillsNamesList()
    {
        ObservableList<String> filesNameList = FXCollections.observableArrayList();
        String name;
        int lgh;

        File[] listOfFiles = this.loadBillsDir();
        for(File x : listOfFiles)
        {
            name = x.getName();
            lgh = name.length();
            name = name.substring(0, (lgh-4));
            filesNameList.add(name);
        }

        return filesNameList;
    }

    public Bill getSingleBill(String name)
    {
        ObservableList<Bill> billsList = getBillsList();

        for(Bill x : billsList)
        {
            if(x.getName().equals(name))
            {
                return x;
            }
        }
        return null;
    }

    public void removeBillsFiles(ObservableList<String> selectedBills)
    {
        File[] listOfFiles = loadBillsDir();

        for(String x : selectedBills)
        {
            x += ".txt";
            for(File f : listOfFiles)
            {
                if(x.equals(f.getName()))
                {
                    f.delete();
                }
            }
        }
    }
}
