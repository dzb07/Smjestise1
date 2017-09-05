package bih.ba.smjestise.smjestise.Helpers;

import java.io.Serializable;

/**
 * Created by Džana on 12.8.2017.
 */

public class Apartments implements Serializable {
    public String prop_name;
    public Integer price_eur;
    public String host_city;
    public String host_street;
    public Integer price;
    public String lat;
    public String longitude;
    public Integer num_of_rooms;
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
    public String ap_desc;
    public String cancellation;

    public String getCancellation() {
        return cancellation;
    }

    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    public String getAp_desc() {
        return ap_desc;
    }

    public void setAp_desc(String ap_desc) {
        this.ap_desc = ap_desc;
    }

    public Integer getPrice_eur() {
        return price_eur;
    }

    public void setPrice_eur(Integer price_eur) {
        this.price_eur = price_eur;
    }

    public Integer getNum_of_rooms() {
        return num_of_rooms;
    }

    public void setNum_of_rooms(Integer num_of_rooms) {
        this.num_of_rooms = num_of_rooms;
    }

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

    public Apartments(String prop_name, Integer price_eur, String host_city, String host_street, Integer price, String lat, String longitude, Integer num_of_rooms, String max_num_of_people, String type_of_beds, String arr_time, String bathroom, String kitchen, String breakfast, String parking, String pets, String wifi, String app_code, String urlImage1, String urlImage2, String urlImage3, String urlImage4, String urlImage5, String urlImage6, String urlImage7, String urlImage8, String urlImage9, String urlImage10, String ap_desc, String cancellation) {
        this.prop_name = prop_name;
        this.price_eur = price_eur;
        this.host_city = host_city;
        this.host_street = host_street;
        this.price = price;
        this.lat = lat;
        this.longitude = longitude;
        this.num_of_rooms = num_of_rooms;
        this.max_num_of_people = max_num_of_people;
        this.type_of_beds = type_of_beds;
        this.arr_time = arr_time;
        this.bathroom = bathroom;
        this.kitchen = kitchen;
        this.breakfast = breakfast;
        this.parking = parking;
        this.pets = pets;
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
        this.ap_desc = ap_desc;
        this.cancellation = cancellation;
    }

    public Apartments() {
    }




    public Apartments(String property_name, String urlImage1, String host_city, Integer price) {
        this.prop_name = property_name;
        this.urlImage1 = urlImage1;
        this.host_city = host_city;
        this.price=price;


    }


    public void setPrice(Integer price) {
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
    public Integer getPrice() {
        return price;
    }

}
