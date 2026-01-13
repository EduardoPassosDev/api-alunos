package br.com.eduardoDev.exemplo1.entidade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alunos")
public class Aluno {

    @Id
    private String id;

    @NotNull
    @Min(value = 1)
    private int matricula;

    @NotEmpty
    private String nome;
    private String sobrenome;
    private String cpf;

    public static String obterNome(){
        return "Eai, blz?";
    }



}
