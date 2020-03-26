package com.gr.dto;

import com.gr.entity.Product;
import com.gr.enums.ProductSateEnum;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:37
 */
public class ProductExecution {
    private int state;
    private String stateInfo;
    private int count;
    private Product product;
    private List<Product> list;

    public ProductExecution() {
    }

    public ProductExecution(ProductSateEnum productSateEnum) {
        this.state = productSateEnum.getState();
        this.stateInfo = productSateEnum.getStateInfo();
    }
    public ProductExecution(ProductSateEnum productSateEnum, Product product){
        this.state = productSateEnum.getState();
        this.stateInfo = productSateEnum.getStateInfo();
        this.product = product;
    }
    public ProductExecution(ProductSateEnum productSateEnum, List<Product> products){
        this.state = productSateEnum.getState();
        this.stateInfo = productSateEnum.getStateInfo();
        this.list = products;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
