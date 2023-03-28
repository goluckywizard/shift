package com.example.demo.model;

import com.example.demo.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductModel {
    Long id;
    String name;
    Long batch;
    String producer;
    Double cost;
    Long count;

    List<AttributeValueModel> attributeValueModels;

    public static ProductModel toModel(Product product) {
        ProductModel model = new ProductModel();
        model.setId(product.getIdentifier());
        model.setName(product.getName());
        model.setBatch(product.getBatch());
        model.setCost(product.getCost());
        model.setCount(product.getCount());
        model.setProducer(product.getProducer());
        model.setAttributeValueModels(product.getValueList().stream().map(AttributeValueModel::toModel).toList());
        return model;
    }
}
