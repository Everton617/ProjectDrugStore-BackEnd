package com.projectFullStack.product;

public record ProductResponseDTO(Long codigo, String name, String marca, String classe, Integer qtdestoque) {

    public ProductResponseDTO(Product product){
        this(product.getCodigo(), product.getName(), product.getMarca(), product.getClasse(), product.getQtdestoque());
    }
}
