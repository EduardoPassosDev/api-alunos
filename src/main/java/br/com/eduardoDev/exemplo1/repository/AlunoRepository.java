package br.com.eduardoDev.exemplo1.repository;

import br.com.eduardoDev.exemplo1.entidade.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository extends MongoRepository<Aluno, String> {

    public Aluno findByMatricula(int matricula);
}
