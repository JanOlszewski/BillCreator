package FileSupport;

import contents.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProductFileWriter
{
    private File productsDir;
    private String path;

    //Constructor
    private ProductFileWriter()
    {
        path = System.getProperty("user.dir");
        productsDir = new File(path + "\\Products");

        if(!productsDir.exists()) { productsDir.mkdir(); }
    }

    private static class ClassHolder
    {
        private static final ProductFileWriter INSTANCE = new ProductFileWriter();
    }

    public static ProductFileWriter getInstance()
    {
        return ClassHolder.INSTANCE;
    }

    //Methods for writting
    public void writeProduct(Product product)
    {
        try
        {
            PrintWriter writer = new PrintWriter(path + "\\Products" + "\\" + product.getName() + ".txt");
            writer.println(product.getName());
            writer.println(product.getUnit());
            writer.print(product.getPrice());
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
