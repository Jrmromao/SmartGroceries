package co.devhut.smartgroceries;

/**
 * Created by jrmromao on 29/10/2017.
 * class to hold all the URLs used when calling the web-service
 */

class URLs {

    private static final String ROOT_URL = "http://192.168.0.3:8081/SmartGrocery/PHP/Api.php?apicall=";
    static final String URL_LOGIN = ROOT_URL + "login";
    static final String URL_REGISTER = ROOT_URL + "signup";
    static final String URL_GET_PRODUCT = ROOT_URL + "getProduct";


}
