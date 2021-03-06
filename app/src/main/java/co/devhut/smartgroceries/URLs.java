package co.devhut.smartgroceries;

/**
 * Created by jrmromao on 29/10/2017.lass to hold all
 * the URLs used when calling the web-service
 */


class URLs {

    //home
    // private static final String ROOT_URL = "http://192.168.56.1:8081/SmartGrocery/PHP/Api.php?apicall=";
    private static final String ROOT_URL = "http://192.168.0.3:8082/WebService/webresources/api/";

    //home portugal
    //private static final String ROOT_URL = "http://192.168.56.1:8081/WebService/webresources/api/";
    // private static final String ROOT_URL = "http://192.168.56.1:8082/WebService/webresources/api/";

    //login
    //college
    //private static final String ROOT_URL = "http://172.21.11.22:8081/SmartGrocery/PHP/Api.php?apicall=";
    static final String URL_LOGIN = ROOT_URL + "login";
    static final String URL_REGISTER = ROOT_URL + "signup";
    static final String URL_GET_PRODUCT = ROOT_URL + "getProduct";
    static final String URL_GET_BEST_PRODUCT = ROOT_URL + "getBestProduct";

//192.168.56.10
    //
}
