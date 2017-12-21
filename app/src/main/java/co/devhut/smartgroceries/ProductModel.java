package co.devhut.smartgroceries;

/**
 * Created by jrmromao on 04/11/2017.
 * class to hold the product details
 */

public class ProductModel {


    private int mUPC_num;
    private String mBrand;
    private String mName;
    private String mDescription;
    private String mExpiryDate;
    private double mPrice;
    private int prodCount;







    public ProductModel() {
    }

    public ProductModel(int mUPC_num, String mBrand, String mName, String mDescription, String mExpiryDate, double mPrice) {
        this.mUPC_num = mUPC_num;
        this.mBrand = mBrand;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mExpiryDate = mExpiryDate;
        this.mPrice = mPrice;
    }


    public ProductModel(int mUPC_num, String mBrand, String mName, String mDescription, String mExpiryDate, double mPrice, int prodCount) {
        this.mUPC_num = mUPC_num;
        this.mBrand = mBrand;
        this.mName = mName;
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

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductModel)) return false;

        ProductModel that = (ProductModel) o;

        if (getmUPC_num() != that.getmUPC_num()) return false;
//        if (Double.compare(that.getmPrice(), getmPrice()) != 0) return false;
//        if (getProdCount() != that.getProdCount()) return false;
//        if (!getmBrand().equals(that.getmBrand())) return false;
//        if (!getmName().equals(that.getmName())) return false;
//        if (!getmDescription().equals(that.getmDescription())) return false;
        return getmExpiryDate().equals(that.getmExpiryDate());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getmUPC_num();
        result = 31 * result + getmBrand().hashCode();
        result = 31 * result + getmName().hashCode();
        result = 31 * result + getmDescription().hashCode();
        result = 31 * result + getmExpiryDate().hashCode();
        temp = Double.doubleToLongBits(getmPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getProdCount();
        return result;
    }
}


