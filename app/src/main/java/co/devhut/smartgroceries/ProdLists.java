package co.devhut.smartgroceries;

import java.util.ArrayList;

/**
 * Created by jrmromao on 24/11/2017.
 * class to old he lists of products
 */

class ProdLists {

    private static ArrayList<ProductModel> bestProdList = new ArrayList<>();//new up the arrayList -  i was getting an error,
    private static ArrayList<ProductModel> scanProdList = new ArrayList<>();// and ultimately the app was crashing, because i was not newing up the arrays

    static ArrayList<ProductModel> getBestProdList() {
        return bestProdList;
    }

    static void setBestProdList(ProductModel bestProd) {

        if (ProdLists.bestProdList == null) {
            ProdLists.bestProdList = new ArrayList<>();
            ProdLists.bestProdList.add(bestProd);
        } else {
            ProdLists.bestProdList.add(bestProd);
        }
    }

    static ArrayList<ProductModel> getScanProdList() {
        return scanProdList;
    }

    static void setScanProdList(ProductModel pm) {
        if (ProdLists.scanProdList == null) {
            ProdLists.scanProdList = new ArrayList<>();
            ProdLists.scanProdList.add(pm);
        } else {
            ProdLists.scanProdList.add(pm);
        }

    }
}
