package com.drmeph.cartProject.controller;

import com.drmeph.cartProject.enums.ResultCode;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.model.response.CartResponseModel;
import com.drmeph.cartProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kdorfer on 2016-11-07.
 */
@RestController
@RequestMapping(value = "cart")
public class CartController extends BaseController {

    @Autowired
    CartService cartService;

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "add-to-cart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponseModel addToCart(@RequestParam int productId) {
        CartResponseModel responseModel = new CartResponseModel();

        try {
            cartService.addToCart(productId);
            setResultCode(responseModel, ResultCode.LOCK_PRODUCT_SUCCESS, null);
            responseModel.setCart(cartService.getCart());
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.LOCK_PRODUCT_FAILED, e.getMessage());
        }

        return responseModel;
    }

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "remove-from-cart", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponseModel removeFromCart(@RequestParam int productId) {
        CartResponseModel responseModel = new CartResponseModel();

        try {
            cartService.removeFromCart(productId);
            setResultCode(responseModel, ResultCode.UNLOCK_PRODUCT_SUCCESS, null);
            responseModel.setCart(cartService.getCart());
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.UNLOCK_PRODUCT_FAILED, e.getMessage());
        }

        return responseModel;
    }
}
