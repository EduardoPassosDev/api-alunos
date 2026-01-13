package br.com.eduardoDev.exemplo1.entidade;

import br.com.eduardoDev.exemplo1.dto.AlunoDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    private Endereco endereco;

    public Aluno(AlunoDTO alunoDTO) {
        setNome(alunoDTO.getNome());
        setSobrenome(alunoDTO.getSobrenome());
        setCpf(alunoDTO.getCpf());
        setMatricula(alunoDTO.getMatricula());
    }
}
