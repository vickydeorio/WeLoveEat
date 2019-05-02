package com.weloveeat.model;

import java.math.BigDecimal;

public class IngredientVO {
    private String name;
    private Integer id;
    private BigDecimal unitPrice;

    public IngredientVO(Integer id, String name, BigDecimal unitPrice){
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
