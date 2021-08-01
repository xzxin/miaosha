package com.miaoshaproject.service;

import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.service.model.OrderModel;

public interface OrderService {
    // url传递秒杀活动id，下单接口内校验是否属于对应商品切活动已开始
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BussinessException;

}
