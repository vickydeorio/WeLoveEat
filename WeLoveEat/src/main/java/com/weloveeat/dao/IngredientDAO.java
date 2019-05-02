package com.weloveeat.dao;

import com.weloveeat.model.IngredientVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class IngredientDAO {

    ArrayList<IngredientVO> allIngredients;

    public IngredientDAO(){
        allIngredients = new ArrayList<IngredientVO>();
        allIngredients.add(new IngredientVO(0, "Alface", BigDecimal.valueOf(0.40)));
        allIngredients.add(new IngredientVO(1, "Bacon",BigDecimal.valueOf(2)));
        allIngredients.add(new IngredientVO(2, "Hambúrguer de carne",BigDecimal.valueOf(3)));
        allIngredients.add(new IngredientVO(3, "Ovo",BigDecimal.valueOf(0.80)));
        allIngredients.add(new IngredientVO(4, "Queijo",BigDecimal.valueOf(1.50)));
    }

    public ArrayList<IngredientVO> getAllIngredients() {
        return allIngredients;
    }

    public IngredientVO getIngredientById(Integer id) {
        if (id != null){
            for (IngredientVO ingredient: allIngredients) {
                if (ingredient.getId().equals(id)){
                    return ingredient;
                }
            }
        }

        return null;
    }

    public IngredientVO getIngredientByName(String name) {
        if(!name.isEmpty()){
            for (IngredientVO ingredient: allIngredients) {
                if (ingredient.getName().trim().toLowerCase().equals(name.trim().toLowerCase())){
                    return ingredient;
                }
            }
        }

        return null;
    }
}
