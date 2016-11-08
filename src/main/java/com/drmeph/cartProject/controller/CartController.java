package com.drmeph.cartProject.controller;

import com.drmeph.cartProject.enums.ResultCode;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.model.response.CartResponseModel;
import com.drmeph.cartProject.model.response.ResponseModel;
import com.drmeph.cartProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


/**
 * Created by kdorfer on 2016-11-07.
 */
@RestController
@RequestMapping(value = "cart")
public class CartController extends BaseController {

    @Autowired
    CartService cartService;

    /**
     * Handle adding a product to a cart based on the browser session
     *
     * @param productId the id of the product that needs to be added to the cart
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
     * Handle removing a product from a cart based on the browser session
     *
     * @param productId the id of the product that needs to be removed from the cart
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

    /*
     * Other section
     */
    /**
     * Handles missing parameter requests
     *
     * @param exception
     * @return
     */
    //TODO attempt to handle missing parameters but it's not working for now.
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseModel> missingParameters(Exception exception) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("You are missing a parameter");
        return ResponseEntity.ok(responseModel);
    }
}
