package com.weloveeat.dao;

import com.weloveeat.model.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class OrderDAO {
    ArrayList<OrderVO> allOptions;
    @Autowired
    IngredientDAO ingredients;

    public OrderDAO(){
        allOptions = new ArrayList<OrderVO>();
        //ingredients = new IngredientDAO();
    }

    @PostConstruct
    public void init(){
        menuOptions();
    }

    public void menuOptions(){
        Integer logicLim = 0;

        OrderVO xBacon = new OrderVO("X-Bacon", logicLim);
        logicLim++;
        xBacon.addIngredient(0, ingredients.getIngredientByName("Alface"));
        xBacon.addIngredient(1, ingredients.getIngredientByName("Bacon"));
        xBacon.addIngredient(1, ingredients.getIngredientByName("Hambúrguer de carne"));
        xBacon.addIngredient(0, ingredients.getIngredientByName("Ovo"));
        xBacon.addIngredient(1, ingredients.getIngredientByName("Queijo"));
        allOptions.add(xBacon);

        OrderVO xBurguer = new OrderVO("X-Burguer", logicLim);
        logicLim++;
        xBurguer.addIngredient(0, ingredients.getIngredientByName("Alface"));
        xBurguer.addIngredient(0, ingredients.getIngredientByName("Bacon"));
        xBurguer.addIngredient(1, ingredients.getIngredientByName("Hambúrguer de carne"));
        xBurguer.addIngredient(0, ingredients.getIngredientByName("Ovo"));
        xBurguer.addIngredient(1, ingredients.getIngredientByName("Queijo"));
        allOptions.add(xBurguer);

        OrderVO xEgg = new OrderVO("X-Egg", logicLim);
        logicLim++;
        xEgg.addIngredient(0,ingredients.getIngredientByName("Alface"));
        xEgg.addIngredient(0,ingredients.getIngredientByName("Bacon"));
        xEgg.addIngredient(1,ingredients.getIngredientByName("Hambúrguer de carne"));
        xEgg.addIngredient(1,ingredients.getIngredientByName("Ovo"));
        xEgg.addIngredient(1,ingredients.getIngredientByName("Queijo"));
        allOptions.add(xEgg);

        OrderVO xEggBacon = new OrderVO("X-EggBacon",logicLim);
        logicLim++;
        xEggBacon.addIngredient(0,ingredients.getIngredientByName("Alface"));
        xEggBacon.addIngredient(1,ingredients.getIngredientByName("Bacon"));
        xEggBacon.addIngredient(1,ingredients.getIngredientByName("Hambúrguer de carne"));
        xEggBacon.addIngredient(1,ingredients.getIngredientByName("Ovo"));
        xEggBacon.addIngredient(1,ingredients.getIngredientByName("Queijo"));
        allOptions.add(xEggBacon);
    }

    public ArrayList<OrderVO> getAllOptions() {
        return allOptions;
    }

    public OrderVO getOptionByName(String name) {
        for (OrderVO option: allOptions) {
            if (option.getName().trim().toLowerCase().equals(name.trim().toLowerCase()))
                return option;
        }

        return null;
    }

    public OrderVO getOptionById(Integer id) {
        if(id != null){
            for (OrderVO option: allOptions) {
                if(option.getId().equals(id))
                    return option;
            }
        }

        return null;
    }
}
