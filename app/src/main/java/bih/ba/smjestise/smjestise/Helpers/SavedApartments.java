package bih.ba.smjestise.smjestise.Helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Džana on 28.8.2017.
 */

public class SavedApartments {
    private String firebaseUserId;
    private String prop_name;
    private String host_city;

    public SavedApartments(String firebaseUserId, String prop_name, String host_city) {
        this.firebaseUserId = firebaseUserId;
        this.prop_name = prop_name;
        this.host_city = host_city;
    }

    public String getHost_city() {
        return host_city;
    }

    public void setHost_city(String host_city) {
        this.host_city = host_city;
    }

    public SavedApartments() {
    }

    public SavedApartments(String firebaseAuth, String prop_name) {
        this.firebaseUserId = firebaseAuth;
        this.prop_name = prop_name;
    }

    public String getFirebaseAuth() {
        return firebaseUserId;
    }

    public void setFirebaseAuth(String firebaseAuth) {
        this.firebaseUserId = firebaseAuth;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }
}
