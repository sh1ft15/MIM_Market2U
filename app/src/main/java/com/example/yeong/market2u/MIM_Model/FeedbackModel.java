package com.example.yeong.market2u.MIM_Model;

/**
 * Created by yeong on 26/4/2017.
 */

public class FeedbackModel {

    private String commentID;
    private String comment;
    private String commentTimestamp;
    private double rating;
    private String userID;
    private String productID;

    public FeedbackModel(){

    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentTimestamp(String commentTimestamp) {
        this.commentTimestamp = commentTimestamp;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentTimestamp() {
        return commentTimestamp;
    }

    public double getRating() {
        return rating;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getUserID() {
        return userID;
    }

    public String getProductID() {
        return productID;
    }
}
