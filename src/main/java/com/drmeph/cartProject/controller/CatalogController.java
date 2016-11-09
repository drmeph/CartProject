package com.drmeph.cartProject.controller;

import com.drmeph.cartProject.enums.ResultCode;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.model.ProductModel;
import com.drmeph.cartProject.model.RatingModel;
import com.drmeph.cartProject.model.response.ListProductsAdminResponseModel;
import com.drmeph.cartProject.model.response.ListProductsResponseModel;
import com.drmeph.cartProject.model.response.ResponseModel;
import com.drmeph.cartProject.model.response.ShowProductResponseModel;
import com.drmeph.cartProject.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by kdorfer on 2016-11-05.
 */
@RestController
@RequestMapping(value = "/")
public class CatalogController extends BaseController {
    private static final Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    ProductService productService;

    /*
     * Customer section
     */

    /**
     * Handle pulling the list of products in the catalog
     *
     * @return a json response containing :
     * message, ResultCode, product list of names and ids
     */
    @RequestMapping(value = "catalog/list-products", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ListProductsResponseModel getListProducts() {

        ListProductsResponseModel responseModel = new ListProductsResponseModel();

        try {
            responseModel.setProducts(productService.getListProducts());
            setResultCode(responseModel, ResultCode.GET_LIST_PRODUCTS_SUCCESS, null);
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.GET_LIST_PRODUCTS_FAILED, e.getMessage());
        }

        return responseModel;
    }

    /**
     * Handle pulling a product record given a productId
     *
     * @param productId
     * @return a json response containing :
     * message, ResultCode, product name, id, price, rating, inStock
     */
    @RequestMapping(value = "catalog/show-product/{productId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ShowProductResponseModel showProduct(@PathVariable int productId) {
        ShowProductResponseModel responseModel = new ShowProductResponseModel();

        try {
            responseModel.setProduct(productService.getProduct(productId));
            setResultCode(responseModel, ResultCode.SHOW_PRODUCT_SUCCESS, null);
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.SHOW_PRODUCT_FAILED, e.getMessage());
        }

        return responseModel;
    }

    /**
     * Handle the rating request for any users Customer or Administrator
     *
     * @param ratingModel json object containing productId and rating value (1 - 10)
     * @param errors contains all the validation errors
     * @return a json model contains the result message and code
     */
    @RequestMapping(value = "catalog/rate-product", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel rateProduct(@Valid @RequestBody RatingModel ratingModel, Errors errors) {
        ResponseModel responseModel = new ResponseModel();

        if (errors.hasErrors()) {
            setResultCode(responseModel, ResultCode.RATE_PRODUCT_FAILED, null);
            setErrors(responseModel, errors);

            return responseModel;
        }

        try {
            productService.rateProduct(ratingModel);
            setResultCode(responseModel, ResultCode.RATE_PRODUCT_SUCCESS, null);

        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.RATE_PRODUCT_FAILED, null);
        }

        return responseModel;
    }


    /*
     * Administrator section
     */

    /**
     * Handle adding a product to the catalog
     *
     * @param model
     * @param errors
     * @return
     */
    @RequestMapping(value = "admin/catalog/add-product", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel addProduct(@RequestBody ProductModel model, Errors errors) {
        ResponseModel responseModel = new ResponseModel();

        if (errors.hasErrors()) {
            setResultCode(responseModel, ResultCode.ADD_PRODUCT_FAILED, null);
            setErrors(responseModel, errors);

            return responseModel;
        }

        try {
            productService.addProduct(model);
            setResultCode(responseModel, ResultCode.ADD_PRODUCT_SUCCESS, null);
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.ADD_PRODUCT_FAILED, e.getMessage());
        }

        return responseModel;
    }

    /**
     * handle removing a product from the catalog
     *
     * @param productId
     * @return
     */
    //TODO test with no productId
    @RequestMapping(value = "admin/catalog/remove-product/{productId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel deleteProduct(@PathVariable int productId) {
        ResponseModel responseModel = new ResponseModel();

        try {
            productService.deleteProduct(productId);
            setResultCode(responseModel, ResultCode.DELETE_PRODUCT_SUCCESS, null);
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.DELETE_PRODUCT_FAILED, e.getMessage());
        }

        return responseModel;
    }

    /**
     * Handle pulling the list of products for a Administrator
     *
     * @return
     */
    @RequestMapping(value = "admin/catalog/list-products", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ListProductsAdminResponseModel getAdminListProduct() {
        ListProductsAdminResponseModel responseModel = new ListProductsAdminResponseModel();

        try {
            responseModel.setProducts(productService.getListProducts());
            setResultCode(responseModel, ResultCode.GET_ADMIN_LIST_PRODUCT_SUCCESS, null);
        } catch (DAOException e) {
            setResultCode(responseModel, ResultCode.GET_ADMIN_LIST_PRODUCT_FAILED, e.getMessage());
        }

        return responseModel;
    }
}
