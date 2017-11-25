package co.devhut.smartgroceries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jrmromao on 04/11/2017.
 * class to hold the product details
 */

public class ProductModel {


    private int mUPC_num;
    private String mBrande;
    private String mName;
    private String mDescription;
    private String mExpiryDate;
    private double mPrice;
    private int prodCount;







    public ProductModel() {
    }

    public ProductModel(int mUPC_num, String mBrande, String mName, String mDescription, String mExpiryDate, double mPrice) {
        this.mUPC_num = mUPC_num;
        this.mName = mName;
        this.mName = mBrande;
        this.mDescription = mDescription;
        this.mExpiryDate = mExpiryDate;
        this.mPrice = mPrice;
    }


    public ProductModel(int mUPC_num, String mBrande, String mName, String mDescription, String mExpiryDate, double mPrice, int prodCount) {
        this.mUPC_num = mUPC_num;
        this.mName = mName;
        this.mName = mBrande;
        this.mDescription = mDescription;
        this.mExpiryDate = mExpiryDate;
        this.mPrice = mPrice;
        this.prodCount = prodCount;
    }

    public int getProdCount() {
        return prodCount;
    }

    public void setProdCount(int prodCount) {
        this.prodCount = prodCount;
    }

    public String getmBrande() {
        return mBrande;
    }

    public void setmBrande(String mBrande) {
        this.mBrande = mBrande;
    }

    public int getmUPC_num() {

        return mUPC_num;
    }

    public void setmUPC_num(int mUPC_num) {

        this.mUPC_num = mUPC_num;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {

        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmExpiryDate() {
        return mExpiryDate;
    }

    public void setmExpiryDate(String mExpiryDate) {
        this.mExpiryDate = mExpiryDate;
    }

    public double getmPrice() {

        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }


}


