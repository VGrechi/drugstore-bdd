package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer codProduto;
    private String descricao;
    private Integer qtdEstoque;
    private BigDecimal precoUnitario;
    private String unidade;
    private String tipo;

    public Product(Integer codProduto){
        this.codProduto = codProduto;
    }

}
