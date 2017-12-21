package co.devhut.smartgroceries;

/**
 * Created by Joao Romao on 04/11/2017.
 * Class to hold the barcode number
 */

public class BarCode {


    private static boolean flag;
    private static String barcodeNum;

    public BarCode() {
    }


    static void setBarcodeNum(String barcodeNum) {
        BarCode.barcodeNum = barcodeNum;
    }

    static String getBarcode() {
        return "20202392";//barcodeNum;
    }


    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        BarCode.flag = flag;
    }

}
