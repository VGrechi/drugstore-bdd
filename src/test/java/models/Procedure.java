package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Procedure {

    private Integer idCliente;
    private Integer inss;
    private String tipo;
    private List<Product> produtos;

    public Procedure(){
        this.produtos = new ArrayList<>();
    }
}
