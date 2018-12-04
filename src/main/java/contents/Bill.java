package contents;

import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Bill
{
    ObservableList<TableRow> tableRowsList;
    String name;
    double sum;

    //Constructors
    public Bill(ObservableList<TableRow> tableRowsList, double sum)
    {
        this.tableRowsList = tableRowsList;
        this.sum = sum;

        LocalTime locTime = LocalTime.now();
        LocalDate locDate = LocalDate.now();
        this.name = locDate.getDayOfMonth() + "." + locDate.getMonth() + "." + locDate.getYear() + "   " +
                locTime.getHour() + "." + locTime.getMinute() + "." + locTime.getSecond();
    }

    public Bill(ObservableList<TableRow> tableRowsList, double sum, String name)
    {
        this.tableRowsList = tableRowsList;
        this.sum = sum;
        this.name = name;
    }

    //Geters
    public ObservableList<TableRow> getTableRowsList()
    {
        return tableRowsList;
    }

    public String getName()
    {
        return name;
    }

    public double getSum()
    {
        return sum;
    }

    //Returns String array. individual objects are separated by an empty line.
    public String[] getData()
    {
        ArrayList<String> list = new ArrayList<>();

        list.add(name);
        list.add(Double.toString(sum));
        list.add("");

        for(TableRow x : tableRowsList)
        {
            list.add(x.getName());
            list.add(x.getUnit());
            list.add(Double.toString(x.getPrice()));
            list.add(Integer.toString(x.getQuantity()));
            list.add("");
        }

        String[] tab = new String[list.size()];
        tab = list.toArray(tab);

        return tab;
    }
}
