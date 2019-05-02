package com.weloveeat.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class OrderVO implements Serializable {
    //Objeto de IngredientVO e quantidade
    private List<IngredientOrderVO> ingredientsList = new ArrayList<IngredientOrderVO>();
    private String name;
    private Integer id;
    private BigDecimal price;

    public OrderVO(String name, Integer id){
        setName(name);
        setId(id);
    }

    public OrderVO(Integer id){
        setName("Lanche personalizado");
        setId(id);
    }

    public OrderVO(){

    }

    public void addIngredient(Integer quantity, IngredientVO ingredient){
        IngredientOrderVO ingredientOrderVO = new IngredientOrderVO();
        ingredientOrderVO.setIngredientVO(ingredient);
        ingredientOrderVO.setQuatity(quantity);

        getIngredientsList().add(ingredientOrderVO);
    }

    public void removeIngredient(IngredientVO ingredient) throws Exception {
        boolean isRemoved = false;
        for (IngredientOrderVO ingredientOrderVO : ingredientsList) {
            if(ingredientOrderVO.equals(ingredient)){
                ingredientsList.remove(ingredientOrderVO);
                isRemoved = true;
            }
        }

        if(!isRemoved){
            throw new Exception("Impossível remover");
        }
    }

    public void editQuantity(Integer quantity, IngredientVO ingredient) {
        boolean isAdd = false;

        if(quantity > 0) {
            for (IngredientOrderVO ingredientOrderVO : ingredientsList) {
                if (ingredientOrderVO.equals(ingredient)) {
                    ingredientOrderVO.setQuatity(quantity);
                    isAdd = true;
                }
            }

            if(!isAdd) {
                addIngredient(quantity, ingredient);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<IngredientOrderVO> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<IngredientOrderVO> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
