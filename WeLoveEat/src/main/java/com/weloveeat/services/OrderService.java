package com.weloveeat.services;

import com.weloveeat.dao.IngredientDAO;
import com.weloveeat.dao.OrderDAO;
import com.weloveeat.model.IngredientOrderVO;
import com.weloveeat.model.IngredientVO;
import com.weloveeat.model.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    IngredientDAO ingredientDAO;

    public void addIngredient(Integer quantity, IngredientVO ingredient, OrderVO order) {
        order.addIngredient(quantity, ingredient);
    }

    public void removeIngredient(IngredientVO ingredient, OrderVO order) {
        try {
            order.removeIngredient(ingredient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editQuantity(Integer quantity, IngredientVO ingredient, OrderVO order) {
        order.editQuantity(quantity, ingredient);
    }

    public void calculate(OrderVO order) {
        BigDecimal num;
        BigDecimal sum = BigDecimal.valueOf(0);
        hasPromotion(order);
        IngredientVO hamburguer = getIngredient("hambúrguer de carne", null);
        IngredientVO queijo = getIngredient("queijo", null);

        if (order != null){
            for (IngredientOrderVO ingredient: order.getIngredientsList()) {
                //Qtd recebe a quantidade do ingrediente no pedido
                BigDecimal qtd = BigDecimal.valueOf(ingredient.getQuantity());

                //Se o lanche participa da promoção plus e o ingrediente atual é um hambúrguer de carne ou um queijo
                if( (order.getPromotions().contains("maisCarne") && ingredient.equals(hamburguer)) || (order.getPromotions().contains("maisQueijo") && ingredient.equals(queijo)) ){
                    //Multiplica-se a quantidade por 2/3
                    qtd = BigDecimal.valueOf(qtd.floatValue() * 2/3);
                    //Obtem-se o valor absoluto
                    qtd = BigDecimal.valueOf(Math.abs(qtd.floatValue()));
                }

                //Multiplica-se a quantidade pelo preço unitário do ingrediente
                num = qtd.multiply(ingredient.getIngredientVO().getUnitPrice());

                //Adiciona-se o valor deste ingrediente(num) ao valor total do pedido
                sum = sum.add(num);
            }
            //Se o pedido participa da promoção "light", multiplica-se o valor total por 90%
            sum = (order.getPromotions().contains("light")) ? sum.multiply(BigDecimal.valueOf(0.90)): sum;

            order.setPrice(sum);
        }

    }

    public void hasPromotion(OrderVO order){
        ArrayList<String> ret = new ArrayList<String>();

        IngredientVO hamburguer = getIngredient("hambúrguer de carne", null);
        IngredientVO queijo = getIngredient("queijo", null);
        IngredientVO alface = getIngredient("alface", null);
        IngredientVO bacon = getIngredient("bacon", null);

        boolean containBacon = false;
        boolean containAlface = false;

        if (order != null){
            for (IngredientOrderVO ingredient: order.getIngredientsList()) {
                if( ingredient.getIngredientVO().equals(hamburguer) && (ingredient.getQuantity() >= 3) ){
                    ret.add("maisCarne");
                }else if(ingredient.getIngredientVO().equals(queijo) && (ingredient.getQuantity() >= 3) ){
                    ret.add("maisQueijo");
                }else if(ingredient.getIngredientVO().equals(bacon) && ingredient.getQuantity() > 0) {
                    containBacon = true;
                }else if(ingredient.getIngredientVO().equals(alface) && ingredient.getQuantity() > 0){
                    containAlface = true;
                }
            }

            if(containAlface && !containBacon){
                ret.add("light");
            }

            order.setPromotions(ret);
        }
    }

    public ArrayList<IngredientVO> getAllIngredients() {
        return ingredientDAO.getAllIngredients();
    }

    public IngredientVO getIngredient(String name, Integer id) {
        return (id != null) ? ingredientDAO.getIngredientById(id) : (!name.isEmpty()) ? ingredientDAO.getIngredientByName(name) : null;
    }

    public ArrayList<OrderVO> getAllMenuOrders() {
        return orderDAO.getAllOptions();
    }

    public List<String> getAllMenuOrdersName() {
        List<String> names = new ArrayList<String>();

        for (OrderVO order: getAllMenuOrders()) {
            names.add(order.getName());
        }

        return names;
    }

    public OrderVO getMenuOrder(String name, Integer id) {
        OrderVO order = null;

        if(id != null){
            order = orderDAO.getOptionById(id);
            calculate(order);
        }else if(!name.isEmpty()){
            order = orderDAO.getOptionByName(name);
            calculate(order);
        }
        return order;
    }
}
