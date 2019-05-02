package com.weloveeat.model;

import java.io.Serializable;

public class IngredientOrderVO implements Serializable {
    private IngredientVO ingredientVO;
    private Integer quatity;

    public IngredientVO getIngredientVO() {
        return ingredientVO;
    }

    public void setIngredientVO(IngredientVO ingredientVO) {
        this.ingredientVO = ingredientVO;
    }

    public Integer getQuatity() {
        return quatity;
    }

    public void setQuatity(Integer quatity) {
        this.quatity = quatity;
    }

    public boolean equals(IngredientVO ingredientVO){
        return this.getIngredientVO().equals(ingredientVO);
    }
}
