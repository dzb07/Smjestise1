package bih.ba.smjestise.smjestise.Helpers;

import java.io.Serializable;

/**
 * Created by Džana on 12.8.2017.
 */

public class Apartments implements Serializable {
    public String prop_name;
    public String host_city;
    public String host_street;
    public String price;
    public String lat;
    public String longitude;
    public String num_of_rooms;
    public String max_num_of_people;
    public String type_of_beds;
    public String arr_time;
    public String bathroom;
    public String kitchen;
    public String breakfast;
    public String parking;
    public String pets;
    public String wifi;
    public String app_code;
    public String urlImage1;
    public String urlImage2;
    public String urlImage3;
    public String urlImage4;
    public String urlImage5;
    public String urlImage6;
    public String urlImage7;
    public String urlImage8;
    public String urlImage9;
    public String urlImage10;

    public Apartments(String longitude, String lat) {
        this.longitude = longitude;
        this.lat=lat;
    }
public Apartments(String lat){
    this.lat=lat;
}
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Apartments(String prop_name, String host_city, String host_address, String price, String num_of_rooms, String max_num_of_people, String type_of_beds, String arr_time, String bathroom, String kitchen, String breakfast, String parking_lot, String pets_allowed, String wifi, String app_code, String urlImage1, String urlImage2, String urlImage3, String urlImage4, String urlImage5, String urlImage6, String urlImage7, String urlImage8, String urlImage9, String urlImage10) {
        this.prop_name = prop_name;
        this.host_city = host_city;
        this.host_street = host_address;
        this.price = price;
        this.num_of_rooms = num_of_rooms;
        this.max_num_of_people = max_num_of_people;
        this.type_of_beds = type_of_beds;
        this.arr_time = arr_time;
        this.bathroom = bathroom;
        this.kitchen = kitchen;
        this.breakfast = breakfast;
        this.parking = parking_lot;
        this.pets = pets_allowed;
        this.wifi = wifi;
        this.app_code = app_code;
        this.urlImage1 = urlImage1;
        this.urlImage2 = urlImage2;
        this.urlImage3 = urlImage3;
        this.urlImage4 = urlImage4;
        this.urlImage5 = urlImage5;
        this.urlImage6 = urlImage6;
        this.urlImage7 = urlImage7;
        this.urlImage8 = urlImage8;
        this.urlImage9 = urlImage9;
        this.urlImage10 = urlImage10;
    }

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
