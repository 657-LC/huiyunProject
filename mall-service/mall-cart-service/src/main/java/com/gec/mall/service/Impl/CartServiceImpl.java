package com.gec.mall.service.Impl;

import com.gec.mall.cart.model.Cart;
import com.gec.mall.goods.feign.SkuFeign;
import com.gec.mall.goods.model.Sku;
import com.gec.mall.mapper.CartMapper;
import com.gec.mall.service.CartService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private MongoTemplate mongoTemplate;

    //加入购物车
    @Override
    public void add(String id, String userName, Integer num) {
        //删除购物车中当前的商品
        cartMapper.deleteById(userName+id);

        if (num>0){
            //查询sku
            RespResult<Sku> skuResp = skuFeign.one(id);
            //将sku转换成Cart
            Cart cart = new Cart();
            cart.setUserName(userName);
            cart.setSkuId(id);
            cart.setNum(num);
            sku2cart(skuResp.getData(),cart);
            //批量添加
            cartMapper.save(cart);
        }
    }

    @Override
    public List<Cart> list(String userName) {
        //查询条件
        Cart cart = new Cart();
        cart.setUserName(userName);
        return cartMapper.findAll(Example.of(cart), Sort.by("_id"));
    }


    public void sku2cart(Sku sku,Cart cart){
        cart.setImage(sku.getImage());
        cart.set_id(cart.getUserName()+cart.getSkuId());
        cart.setName(sku.getName());
        cart.setPrice(sku.getPrice());
        cart.setSkuId(sku.getId());
    }

    /***
     * 根据ID集合查询购物车列表
     * @param ids
     * @return
     */
    @Override
    public Iterable<Cart> list(List<String> ids) {
        return cartMapper.findAllById(ids);
    }

    /***
     * 根据ID删除
     * @param ids
     */
    @Override
    public void delete(List<String> ids) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").in(ids)),Cart.class);
    }

}
