package contents;

public class Product
{
    private String name;
    private String unit;
    private double price;

    // Constructors
    public Product(String name, String unit, double price)
    {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public Product(String tab[])
    {
        this.name = tab[0];
        this.unit = tab[1];
        this.price = Double.parseDouble(tab[2]);
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

    //Returns String array.
    public String[] getData()
    {
        String[] tab = new String[3];
        tab[0] = name;
        tab[1] = unit;
        tab[2] = Double.toString(price);

        return tab;
    }
}
