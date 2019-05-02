package com.weloveeat.controller;

import com.weloveeat.model.IngredientVO;
import com.weloveeat.model.OrderVO;
import com.weloveeat.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/updateorder", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    OrderVO updateOrder(@RequestBody OrderVO orderVO) {
        orderService.calculate(orderVO);
        return orderVO;
    }

    @GetMapping("/menuorders")
    public @ResponseBody
    List<OrderVO> getAllMenuOrders() {
        return orderService.getAllMenuOrders();
    }

    @GetMapping("/ingredients")
    public @ResponseBody
    List<IngredientVO> getAllIngredients() {
        return orderService.getAllIngredients();
    }

    @GetMapping("/optionsname")
    public @ResponseBody List<String> getAllMenuOrdersName(){
        return orderService.getAllMenuOrdersName();
    }

    @RequestMapping(value = "/getorder", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    OrderVO getOrder(@RequestBody Object name) {
        OrderVO ret = orderService.getMenuOrder(name.toString(), null);
        orderService.calculate(ret);
        return ret;
    }

}
