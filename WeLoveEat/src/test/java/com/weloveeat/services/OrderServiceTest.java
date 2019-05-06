package com.weloveeat.services;

import com.weloveeat.dao.IngredientDAO;
import com.weloveeat.dao.OrderDAO;
import com.weloveeat.model.OrderVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderServiceTest {
    OrderService orderService;

    @Before
    public void before(){
        this.orderService = new OrderService();
        this.orderService.orderDAO = new OrderDAO();
        this.orderService.ingredientDAO = new IngredientDAO();
    }

    /* Testes de valor de lanches do cardápio */
    @Test
    public void xBaconMenuPrice(){
        OrderVO orderVO = orderService.getMenuOrder("X-Bacon", null);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(6.5));
    }

    @Test
    public void xBurgerMenuPrice(){
        OrderVO orderVO = orderService.getMenuOrder("x-burguer", null);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(4.5));
    }

    @Test
    public void xEggMenuPrice(){
        OrderVO orderVO = orderService.getMenuOrder("x-egg", null);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(5.3));
    }

    @Test
    public void xEggBaconMenuPrice(){
        OrderVO orderVO = orderService.getMenuOrder("x-eggbacon", null);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(7.3));
    }

    /* Teste de cálculo de preços */
    @Test
    public void customizedOrderPrice(){
        OrderVO orderVO = new OrderVO();

        orderVO.addIngredient(2,orderService.getIngredient("Alface", null));
        orderVO.addIngredient(2,orderService.getIngredient("Bacon", null));
        orderVO.addIngredient(2,orderService.getIngredient("Hambúrguer de carne", null));
        orderVO.addIngredient(2,orderService.getIngredient("Ovo", null));
        orderVO.addIngredient(2,orderService.getIngredient("Queijo", null));

        orderService.calculate(orderVO);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(15.4));
    }

    /* Testes das promoções */
    @Test
    public void promoMuitaCarne(){
        OrderVO orderVO = new OrderVO();

        orderVO.addIngredient(0,orderService.getIngredient("Alface", null));
        orderVO.addIngredient(0,orderService.getIngredient("Bacon", null));
        orderVO.addIngredient(3,orderService.getIngredient("Hambúrguer de carne", null));
        orderVO.addIngredient(0,orderService.getIngredient("Ovo", null));
        orderVO.addIngredient(1,orderService.getIngredient("Queijo", null));

        orderService.calculate(orderVO);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(7.5));
    }

    @Test
    public void promoMuitoQueijo(){
        OrderVO orderVO = new OrderVO();

        orderVO.addIngredient(0,orderService.getIngredient("Alface", null));
        orderVO.addIngredient(0,orderService.getIngredient("Bacon", null));
        orderVO.addIngredient(1,orderService.getIngredient("Hambúrguer de carne", null));
        orderVO.addIngredient(0,orderService.getIngredient("Ovo", null));
        orderVO.addIngredient(3,orderService.getIngredient("Queijo", null));

        orderService.calculate(orderVO);

        assertThat(orderVO.getPrice()).isEqualTo(new BigDecimal(6).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void promoLight(){
        OrderVO orderVO = new OrderVO();

        orderVO.addIngredient(1,orderService.getIngredient("Alface", null));
        orderVO.addIngredient(0,orderService.getIngredient("Bacon", null));
        orderVO.addIngredient(1,orderService.getIngredient("Hambúrguer de carne", null));
        orderVO.addIngredient(0,orderService.getIngredient("Ovo", null));
        orderVO.addIngredient(1,orderService.getIngredient("Queijo", null));

        orderService.calculate(orderVO);

        assertThat(orderVO.getPrice()).isEqualTo(BigDecimal.valueOf(4.41));
    }

}
