package bih.ba.smjestise.smjestise.Helpers;

/**
 * Created by Džana on 3.9.2017.
 */

public class Comments {
    private String username;
    private String commentProperty;
    private float rateProperty;

    public Comments() {
    }

    public Comments(String username, String commentProperty, float rateProperty) {
        this.username = username;
        this.commentProperty = commentProperty;
        this.rateProperty = rateProperty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
