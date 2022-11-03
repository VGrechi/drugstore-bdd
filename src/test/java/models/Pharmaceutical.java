package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class Pharmaceutical extends Employee {

    public Pharmaceutical(Employee e) {
        this.inss = e.getInss();
        this.nome = e.getNome();
        this.sexo = e.getSexo();
        this.cidade = e.getCidade();
        this.rua = e.getRua();
        this.cep = e.getCep();
        this.dataNascimento = e.getDataNascimento();
        this.dataContratacao = e.getDataContratacao();
        this.funcao = "farmaceutico";
    }

    private Integer crf;
}
