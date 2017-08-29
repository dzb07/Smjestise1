package bih.ba.smjestise.smjestise.Helpers;

/**
 * Created by Džana on 28.8.2017.
 */

public class RateApplicationAdapter {

    private float rate;
    private String userComment;

    public RateApplicationAdapter() {
    }

    public RateApplicationAdapter(float rate, String userComment) {
        this.rate = rate;
        this.userComment = userComment;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}
