package bih.ba.smjestise.smjestise.Helpers;

import java.util.Date;

/**
 * Created by Džana on 21.8.2017.
 */

public class ReservationClass {
    String prop_name;
    Boolean reserved;
    String checkin;
    String checkout;
    long timestamp1;
    long timestamp2;
    String host_city;
    String f_name;
    String l_name;
    String emailAddress;
    String useraddress;
    String userCity;
    String userPhoneNumber;
    String price_to_pay;
    long reservation_made_on;
    String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ReservationClass(String prop_name, Boolean reserved, String checkin, String checkout, long timestamp1, long timestamp2, String host_city, String f_name, String l_name, String emailAddress, String useraddress, String userCity, String userPhoneNumber, String price_to_pay, long reservation_made_on,String userID) {
        this.prop_name = prop_name;
        this.reserved = reserved;
        this.checkin = checkin;
        this.checkout = checkout;
        this.timestamp1 = timestamp1;
        this.timestamp2 = timestamp2;
        this.host_city = host_city;
        this.f_name = f_name;
        this.l_name = l_name;
        this.emailAddress = emailAddress;
        this.useraddress = useraddress;
        this.userCity = userCity;
        this.userPhoneNumber = userPhoneNumber;
        this.price_to_pay = price_to_pay;
        this.reservation_made_on = reservation_made_on;
        this.userID=userID;
    }

    public long getReservation_made_on() {
        return reservation_made_on;
    }

    public void setReservation_made_on(long reservation_made_on) {
        this.reservation_made_on = reservation_made_on;
    }




    public String getPrice_to_pay() {
        return price_to_pay;
    }

    public void setPrice_to_pay(String price_to_pay) {
        this.price_to_pay = price_to_pay;
    }

    public ReservationClass() {
    }




    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public long getTimestamp1() {
        return timestamp1;
    }

    public void setTimestamp1(long timestamp1) {
        this.timestamp1 = timestamp1;
    }

    public long getTimestamp2() {
        return timestamp2;
    }

    public void setTimestamp2(long timestamp2) {
        this.timestamp2 = timestamp2;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getHost_city() {
        return host_city;
    }

    public void setHost_city(String host_city) {
        this.host_city = host_city;
    }
}
