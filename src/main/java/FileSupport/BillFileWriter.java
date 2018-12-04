package FileSupport;

import contents.Bill;
import contents.TableRow;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BillFileWriter
{
    private File billsDir;
    private String path;

    //Constructor
    private BillFileWriter()
    {
        path = System.getProperty("user.dir");
        billsDir = new File(path + "\\Bills");

        if(!billsDir.exists()) { billsDir.mkdir(); }
    }

    private static class ClassHolder
    {
        private static final BillFileWriter INSTANCE = new BillFileWriter();
    }

    public static BillFileWriter getInstance()
    {
        return ClassHolder.INSTANCE;
    }

    //Methods for writting
    public void writeBill(Bill bill)
    {
        try
        {
            PrintWriter writer = new PrintWriter(path + "\\Bills" + "\\" + bill.getName() + ".txt");
            ObservableList<TableRow> list = bill.getTableRowsList();

            writer.println(bill.getName());
            writer.println(bill.getSum());
            writer.println("");

            for(TableRow x : list)
            {
                writer.println(x.getName());
                writer.println(x.getQuantity());
                writer.println(x.getUnit());
                writer.println(x.getPrice());
                writer.println("");
            }
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}