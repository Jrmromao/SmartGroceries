package co.devhut.smartgroceries;

/**
 * Created by jrmromao on 05/12/2017.
 * class to be used to old the product details
 */

public class ProdBundle {

    private static ProductModel prodDetails;

    public static ProductModel getProdDetails() {
        return prodDetails;
    }

    public static void setProdDetails(ProductModel prodDetails) {
        ProdBundle.prodDetails = prodDetails;
    }


}
