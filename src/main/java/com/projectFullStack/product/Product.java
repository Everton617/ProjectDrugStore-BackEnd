package com.projectFullStack.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String name;
    private String marca;
    private String classe;
    private Integer qtdestoque;

    public Product(ProductRequestDTO data){
        this.name = data.name();
        this.classe = data.classe();
        this.marca = data.marca();
        this.qtdestoque = data.qtdestoque();
    }

    public Product(ProductDeleteDTO data){
        this.codigo = data.codigo();
    }

    public Product(ProductUpdateDTO data){
        this.codigo = data.codigo();
        this.name = data.name();
        this.marca = data.marca();
        this.classe = data.classe();
        this.qtdestoque = data.qtdestoque();
    }


}
