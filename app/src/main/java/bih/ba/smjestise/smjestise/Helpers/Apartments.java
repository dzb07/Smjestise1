package bih.ba.smjestise.smjestise.Helpers;

import java.io.Serializable;

/**
 * Created by Džana on 12.8.2017.
 */

public class Apartments implements Serializable {
    public String prop_name;
    public String urlImage1;
    public  String host_city;
    public String price;
    public Apartments() {
    }




    public Apartments(String property_name, String urlImage1, String host_city, String price) {
        this.prop_name = property_name;
        this.urlImage1 = urlImage1;
        this.host_city = host_city;
        this.price=price;


    }

    public void setPrice(String price) {
        this.price = price;
    }
    public void setProp_name(String property_name) {
        this.prop_name = property_name;
    }

    public void setUrlImage1(String urlImage1) {
        this.urlImage1 = urlImage1;
    }

    public void setHost_city(String host_city) {
        this.host_city = host_city;
    }



    public String getProp_name() {
        return prop_name;
    }

    public String getUrlImage1() {
        return urlImage1;
    }

    public String getHost_city() {
        return host_city;
    }
    public String getPrice() {
        return price;
    }

}
