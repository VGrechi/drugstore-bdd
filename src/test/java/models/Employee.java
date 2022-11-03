package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    protected Integer inss;
    protected String nome;
    protected String sexo;
    protected String cidade;
    protected String rua;
    protected Integer cep;
    protected String dataNascimento;
    protected String dataContratacao;
    protected String funcao;

    public Pharmaceutical becomePharmaceutical() {
        return new Pharmaceutical(this);
    }
}
