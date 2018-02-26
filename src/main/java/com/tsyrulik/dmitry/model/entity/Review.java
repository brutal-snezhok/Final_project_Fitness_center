package com.tsyrulik.dmitry.model.entity;

public class Review {
    private int reviewId;
    private int clientId;
    private String textReview;
    private int mark;


    public Review(int reviewId, int clientId, String textReview, int mark) {
        this.reviewId = reviewId;
        this.clientId = clientId;
        this.textReview = textReview;
        this.mark = mark;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (reviewId != review.reviewId) return false;
        if (clientId != review.clientId) return false;
        if (mark != review.mark) return false;
        return textReview != null ? textReview.equals(review.textReview) : review.textReview == null;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + clientId;
        result = 31 * result + (textReview != null ? textReview.hashCode() : 0);
        result = 31 * result + mark;
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", clientId=" + clientId +
                ", textReview='" + textReview + '\'' +
                ", mark=" + mark +
                '}';
    }
}