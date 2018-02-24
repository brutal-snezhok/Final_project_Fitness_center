package com.tsyrulik.dmitry.model.entity;

public class Review {
    private int reviewId;
    private int clientId;
    private int mark;
    private String textReview;

    public Review(int reviewId, int clientId, int mark, String textReview) {
        this.reviewId = reviewId;
        this.clientId = clientId;
        this.mark = mark;
        this.textReview = textReview;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
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
        result = 31 * result + mark;
        result = 31 * result + (textReview != null ? textReview.hashCode() : 0);
        return result;
    }
}