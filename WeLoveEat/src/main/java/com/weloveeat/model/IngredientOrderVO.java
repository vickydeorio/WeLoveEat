package com.weloveeat.model;

import java.io.Serializable;

public class IngredientOrderVO implements Serializable {
    private IngredientVO ingredientVO;
    private Integer quantity;

    public IngredientVO getIngredientVO() {
        return ingredientVO;
    }

    public void setIngredientVO(IngredientVO ingredientVO) {
        this.ingredientVO = ingredientVO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quatity) {
        this.quantity = quatity;
    }

    public boolean equals(IngredientVO ingredientVO){
        return this.getIngredientVO().equals(ingredientVO);
    }
}
