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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean equals(IngredientVO ingredientVO){
        return this.getIngredientVO().equals(ingredientVO);
    }

    public boolean compareTo(IngredientVO ingredientVO){
        if (!ingredientVO.getId().equals(this.ingredientVO.getId())){
            return false;
        }else if(!ingredientVO.getUnitPrice().equals(this.ingredientVO.getUnitPrice())){
            return false;
        }else if (!ingredientVO.getName().equals(this.ingredientVO.getName())){
            return false;
        }

        return true;
    }
}
