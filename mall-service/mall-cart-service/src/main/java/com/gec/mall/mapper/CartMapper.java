package com.gec.mall.mapper;

import com.gec.mall.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartMapper extends MongoRepository<Cart,String> {
}
