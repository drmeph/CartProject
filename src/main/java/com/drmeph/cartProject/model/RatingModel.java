package com.drmeph.cartProject.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by kdorfer on 2016-11-07.
 */
public class RatingModel {
    private int productId;

    @Min(1) @Max(10)
    private int rating;

    public RatingModel() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
