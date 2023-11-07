package com.projectFullStack.controller;

import com.projectFullStack.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProducts(@RequestBody ProductRequestDTO data) {
        Product productData = new Product(data);
        repository.save(productData);
        return;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> productList = repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<ProductDeleteDTO> deleteProduct(@PathVariable Long codigo){
        if (repository.existsById(codigo)) {
            repository.deleteById(codigo);
            return ResponseEntity.ok(new ProductDeleteDTO(codigo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{codigo}")
    public ResponseEntity<ProductUpdateDTO> updateFood(@PathVariable Long codigo, @RequestBody ProductUpdateDTO productUpdateDTO){
        if (repository.existsById(codigo)) {
            Product existingProduct = repository.findById(codigo).orElseThrow();
            existingProduct.setName(productUpdateDTO.name());
            existingProduct.setMarca(productUpdateDTO.marca());
            existingProduct.setClasse(productUpdateDTO.classe());
            existingProduct.setQtdestoque(productUpdateDTO.qtdestoque());

            Product updatedProduct = repository.save(existingProduct);

            return ResponseEntity.ok(new ProductUpdateDTO(
                    updatedProduct.getCodigo(),
                    updatedProduct.getName(),
                    updatedProduct.getMarca(),
                    updatedProduct.getClasse(),
                    updatedProduct.getQtdestoque()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
