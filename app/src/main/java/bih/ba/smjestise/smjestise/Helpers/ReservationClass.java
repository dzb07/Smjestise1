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

    public ReservationClass() {
    }

    public ReservationClass(String host_city,String prop_name, Boolean reserved, String checkin, String checkout,long t1,long t2) {
        this.host_city=host_city;
        this.prop_name = prop_name;
        this.reserved = reserved;
        this.checkin = checkin;
        this.checkout = checkout;
        timestamp1=t1;
        timestamp2=t2;
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
