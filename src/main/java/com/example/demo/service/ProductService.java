package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.TypeAttribute;
import com.example.demo.entity.TypeValue;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.AttributeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@ComponentScan({"com.example.shift.repository"})
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    ValueRepository valueRepository;

    public List<ProductModel> getProductsByName(String name) {
        return productRepository.findAllByName(name).stream().map(ProductModel::toModel).toList();
    }
    public ProductModel getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductModel::toModel).orElse(null);
    }
    public void addProduct(ProductModel productModel) {
        Product product = new Product();
        product.setName(productModel.getName());
        product.setCost(productModel.getCost());
        product.setBatch(productModel.getBatch());
        product.setProducer(productModel.getProducer());
        product.setCount(productModel.getCount());

        extractAttributesValuesFromModel(productModel, product);
        productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public ProductModel changeProduct(ProductModel model, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            throw new NoSuchElementException("Продукта с таким id не существует.");
        Product product = productRepository.findById(id).get();
        if (model.getName() != null)
            product.setName(model.getName());
        if (model.getCost() != null)
            product.setCost(model.getCost());
        if (model.getBatch() != null)
            product.setBatch(model.getBatch());
        if (model.getProducer() != null)
            product.setProducer(model.getProducer());
        if (model.getCount() != null)
            product.setCount(model.getCount());
        if (model.getAttributeValueModels() != null) {
            extractAttributesValuesFromModel(model, product);
        }

        productRepository.save(product);
        return ProductModel.toModel(product);
    }

    private void extractAttributesValuesFromModel(ProductModel model, Product product) {
        ArrayList<TypeValue> valueArrayList = new ArrayList<>();

        for (var attributeValueModel : model.getAttributeValueModels()) {
            Optional<TypeAttribute> attributeOptional = attributeRepository.findByName(attributeValueModel.getAttribute());
            TypeAttribute attribute;
            if (attributeOptional.isEmpty()) {
                attribute = new TypeAttribute();
                attribute.setName(attributeValueModel.getAttribute());
                attribute.setValueList(new ArrayList<>());
                attributeRepository.save(attribute);
            }
            else {
                attribute = attributeOptional.get();
            }
            TypeValue value = new TypeValue();
            value.setVal(attributeValueModel.getValue());
            value.setAttribute(attribute);
            valueRepository.save(value);
            valueArrayList.add(value);
        }
        product.setValueList(valueArrayList);
    }
}
