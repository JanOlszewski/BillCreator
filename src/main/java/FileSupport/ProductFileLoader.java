package FileSupport;

import contents.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class ProductFileLoader
{
    private File productsDir;
    private String path;

    //Constructor
    private ProductFileLoader()
    {
        path = System.getProperty("user.dir");
        productsDir = new File(path + "\\Products");

        if(!productsDir.exists()) { productsDir.mkdirs(); }
    }

    private static class ClassHolder
    {
        private static final ProductFileLoader INSTANCE = new ProductFileLoader();
    }

    public static ProductFileLoader getInstance()
    {
        return ClassHolder.INSTANCE;
    }

    //Loader methods
    public File[] loadProductsDir()
    {
        File[] listOfFiles = productsDir.listFiles();
        return listOfFiles;
    }

    public ObservableList<Product> getProductsList()
    {
        String[] tab = new String[3];

        ObservableList<Product> productsList = FXCollections.observableArrayList();

        File[] listOfFiles = loadProductsDir();
        BufferedReader reader = null;
        String line;

        for(File x : listOfFiles)
        {
            try
            {
                reader = new BufferedReader(new FileReader(x));
                for(int i = 0; ; i++)
                {
                    if((line = reader.readLine()) != null)
                    {
                        tab[i] = line;
                    }
                    else
                    {
                        productsList.add(new Product(tab[0], tab[1], Double.parseDouble(tab[2])));
                        break;
                    }
                }
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return productsList;
    }

    public ObservableList<String> getProductsNamesList()
    {
        ObservableList<String> filesNameList = FXCollections.observableArrayList();
        String name;
        int lgh;

        File[] listOfFiles = loadProductsDir();
        for(File x : listOfFiles)
        {
            name = x.getName();
            lgh = name.length();
            name = name.substring(0, (lgh-4));
            filesNameList.add(name);
        }

        return filesNameList;
    }

    public Product getSingleProduct(String name)
    {
        ObservableList<Product> billsList = getProductsList();

        for(Product x : billsList)
        {
            if(x.getName().equals(name))
            {
                return x;
            }
        }
        return null;
    }

    public void removeProductFiles(ObservableList<String> selectedProducts)
    {
        File[] listOfFiles = loadProductsDir();

        String tym;
        for(String x : selectedProducts)
        {
            tym = x + ".txt";
            for(File f : listOfFiles)
            {
                if(tym.equals(f.getName()))
                {
                    f.delete();
                }
            }
        }
    }
}
