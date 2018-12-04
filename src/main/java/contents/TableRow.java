package contents;

public class TableRow
{
    private String name;
    private String unit;
    private double price;
    private int quantity;
    private double totalCost;

    //Constructors
    public TableRow(Product product, int quantity)
    {
        this.name = product.getName();
        this.unit = product.getUnit();
        this.price = product.getPrice();
        this.quantity = quantity;
        this.totalCost = (this.price*this.quantity);
    }

    public TableRow(String name, String unit, double price, int quantity)
    {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.totalCost = (this.price*this.quantity);
    }

    //Geters
    public String getName()
    {
        return name;
    }

    public String getUnit()
    {
        return unit;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getTotalCost()
    {
        return totalCost;
    }
}
