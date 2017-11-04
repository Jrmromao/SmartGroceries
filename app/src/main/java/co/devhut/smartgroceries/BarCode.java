package co.devhut.smartgroceries;

/**
 * Created by Joao Romao on 04/11/2017.
 * Class to hold the barcode number
 */

public class BarCode {


    private static String barcodeNum;

    public BarCode() {
    }


    static void setBarcodeNum(String barcodeNum) {
        BarCode.barcodeNum = barcodeNum;
    }

    static String getBarcode() {
        return barcodeNum;
    }


}
