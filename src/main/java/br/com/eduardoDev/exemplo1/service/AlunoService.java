package br.com.eduardoDev.exemplo1.service;


import br.com.eduardoDev.exemplo1.entidade.Aluno;
import br.com.eduardoDev.exemplo1.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public List<Aluno> obterTodos() {
        return alunoRepository.findAll();
    }

    public Aluno inserir( Aluno aluno) {
        alunoRepository.insert(aluno);

        return aluno;
    }

    public Aluno atualizar(String id,Aluno novosDadosDoAluno) {

        Optional<Aluno> aluno = findById(id);

        if(aluno.isPresent()){
            Aluno novoAluno = aluno.get();
            novoAluno.setNome(novosDadosDoAluno.getNome());
            novoAluno.setSobrenome(novosDadosDoAluno.getSobrenome());
            novoAluno.setCpf(novosDadosDoAluno.getCpf());
            novoAluno.setMatricula(novosDadosDoAluno.getMatricula());
            alunoRepository.save(novoAluno);

            return novoAluno;
        }
        return null;
    }


    public void remove(String id) {

        Optional<Aluno> aluno = findById(id);

        aluno.ifPresent(value -> alunoRepository.delete(value));
    }


    public Optional<Aluno> findById(String id) {

        return alunoRepository.findById(id);
    }
}
