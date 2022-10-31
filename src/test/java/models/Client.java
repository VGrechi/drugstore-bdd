package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Client {
    private Integer idCliente;
    private String nome;
    private String sexo;
    private String cidade;
    private String rua;
    private Integer cep;
    private Date dataNascimento;
}
