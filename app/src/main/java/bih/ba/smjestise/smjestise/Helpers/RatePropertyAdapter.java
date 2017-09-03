package bih.ba.smjestise.smjestise.Helpers;

/**
 * Created by Džana on 3.9.2017.
 */

public class RatePropertyAdapter {
    private String commentProperty;
    private float rateProperty;

    public RatePropertyAdapter() {
    }

    public RatePropertyAdapter(String commentProperty, float rateProperty) {
        this.commentProperty = commentProperty;
        this.rateProperty = rateProperty;
    }

    public String getCommentProperty() {
        return commentProperty;
    }

    public void setCommentProperty(String commentProperty) {
        this.commentProperty = commentProperty;
    }

    public float getRateProperty() {
        return rateProperty;
    }

    public void setRateProperty(float rateProperty) {
        this.rateProperty = rateProperty;
    }
}
