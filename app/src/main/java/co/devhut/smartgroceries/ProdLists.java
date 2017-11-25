package co.devhut.smartgroceries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrmromao on 24/11/2017.
 * class to old he lists of products
 */

public class ProdLists {

    private static ArrayList<ProductModel> bestProdList;
    private static List<ProductModel> scanProdList;


    public static ArrayList<ProductModel> getBestProdList() {
        return bestProdList;
    }

    public static void setBestProdList(ArrayList<ProductModel> bestProdList) {
        ProdLists.bestProdList = bestProdList;
    }

    public static List<ProductModel> getScanProdList() {
        return scanProdList;
    }

    public static void setScanProdList(List<ProductModel> scanProdList) {
        ProdLists.scanProdList = scanProdList;
    }
}
