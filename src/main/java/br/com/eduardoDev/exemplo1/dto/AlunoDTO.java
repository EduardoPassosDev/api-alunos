package br.com.eduardoDev.exemplo1.dto;

import br.com.eduardoDev.exemplo1.entidade.Endereco;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {

    @NotNull
    @Min(value = 1)
    private int matricula;

    @NotEmpty
    private String nome;
    private String sobrenome;
    private String cpf;

    private String cep;

}
