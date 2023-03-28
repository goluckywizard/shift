package com.example.demo.model;

import com.example.demo.entity.TypeValue;
import lombok.Data;

@Data
public class AttributeValueModel {
    String attribute;
    String value;
    public static AttributeValueModel toModel(TypeValue value) {
        AttributeValueModel model = new AttributeValueModel();
        model.setAttribute(value.getAttribute().getName());
        model.setValue(value.getVal());
        return model;
    }
}
