package bih.ba.smjestise.smjestise.Helpers;

import android.app.Application;
import android.content.Context;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Džana on 20.8.2017.
 */

public class GlobalVars extends Application {

    private int num_of_rooms_var;
    private String property_name;
    private String property_address;
    private Integer num_of_adults;
    private Integer num_of_children;
    private Date checkin;
    private String checkIN;
    private Date checkout;
    private String checkOUT;
    private long t1;
    private long t2;
    public static String currency; //making it static in order to access it in ApartmentViewHolder class
    private long days_difference;
    private String hostcity;
    private String currentUser;
    public String date_of_reservation;
    public String date_of_saving_apartment;
    private String cancellationFromDatabase;
    private static GlobalVars instance;
    private String first_name;
    private String last_name;
    private String email;
    private String userPhoneNumber;
    private String totalPriceToPay;
    private String userAddress;
    private String userCity;

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getTotalPriceToPay() {
        return totalPriceToPay;
    }

    public void setTotalPriceToPay(String totalPriceToPay) {
        this.totalPriceToPay = totalPriceToPay;
    }

    public static GlobalVars getInstance() {
        return instance;
    }
    public String getCancellationFromDatabase() {
        return cancellationFromDatabase;
    }

    public void setCancellationFromDatabase(String cancellationFromDatabase) {
        this.cancellationFromDatabase = cancellationFromDatabase;
    }

    public String getDate_of_saving_apartment() {
        return date_of_saving_apartment;
    }

    public void setDate_of_saving_apartment(String date_of_saving_apartment) {
        this.date_of_saving_apartment = date_of_saving_apartment;
    }

    public String getCurrent_date() {
        return date_of_reservation;
    }

    public void setCurrent_date(String current_date) {
        this.date_of_reservation = current_date;
    }

    public String getProperty_address() {
        return property_address;
    }

    public void setProperty_address(String property_address) {
        this.property_address = property_address;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    /*variables needed for user reservation info to be stored*/


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHostcity() {
        return hostcity;
    }

    public void setHostcity(String hostcity) {
        this.hostcity = hostcity;
    }

    public long getDays_difference() {
        return days_difference;
    }

    public void setDays_difference(long days_difference) {
        this.days_difference = days_difference;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private int counter;

    public void setNum_of_rooms_var(int num_of_rooms_var) {
        this.num_of_rooms_var = num_of_rooms_var;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public long getT1() {
        return t1;
    }

    public void setT1(long t1) {
        this.t1 = t1;
    }

    public long getT2() {
        return t2;
    }

    public void setT2(long t2) {
        this.t2 = t2;
    }

    public GlobalVars() {
    }

    public GlobalVars(Integer num_of_adults, Integer num_of_children, Date checkin, Date checkout) {
        this.num_of_adults = num_of_adults;
        this.num_of_children = num_of_children;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckIN() {
        return checkIN;
    }

    public void setCheckIN(String checkIN) {
        this.checkIN = checkIN;
    }

    public String getCheckOUT() {
        return checkOUT;
    }

    public void setCheckOUT(String checkOUT) {
        this.checkOUT = checkOUT;
    }

    public GlobalVars(String property_name) {
        this.property_name = property_name;
    }

    public String getProperty_name() {
        return property_name;
    }


    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public Integer getNumOfRoomsVar() {
        return num_of_rooms_var;
    }

    public void setNumOfRoomsVar(Integer someVariable) {
        this.num_of_rooms_var = someVariable;
    }

    public Integer getNum_of_rooms_var() {
        return num_of_rooms_var;
    }

    public void setNum_of_rooms_var(Integer num_of_rooms_var) {
        this.num_of_rooms_var = num_of_rooms_var;
    }

    public Integer getNum_of_adults() {
        return num_of_adults;
    }

    public void setNum_of_adults(Integer num_of_adults) {
        this.num_of_adults = num_of_adults;
    }

    public Integer getNum_of_children() {
        return num_of_children;
    }

    public void setNum_of_children(Integer num_of_children) {
        this.num_of_children = num_of_children;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
