package br.com.eduardoDev.exemplo1.controller;

import br.com.eduardoDev.exemplo1.entidade.Aluno;
import br.com.eduardoDev.exemplo1.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public List<Aluno> obterTodos() {
        return alunoService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> obterAlunoPeloId(@PathVariable String id) {
        Optional<Aluno> aluno = alunoService.findById(id);

        if(aluno.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(aluno.get());
    }


    @PostMapping
    public ResponseEntity<Aluno> inserir(@RequestBody @Valid  Aluno aluno) {
        alunoService.inserir(aluno);

        return ResponseEntity.created(null).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@RequestBody Aluno novosDadosAluno, @PathVariable String id ) {
        Optional<Aluno> aluno = alunoService.findById(id);

        if(aluno.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        alunoService.atualizar(id, novosDadosAluno);


        return ResponseEntity.ok().body(novosDadosAluno);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Aluno> atualizarCpf(@RequestParam("cpf") String cpf, @PathVariable String id) {
        Optional<Aluno> aluno = alunoService.findById(id);

        if (aluno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Aluno novosDadosDoAluno = aluno.get();

        novosDadosDoAluno.setCpf(cpf);

        alunoService.atualizar(id, novosDadosDoAluno);

        return ResponseEntity.ok().body(novosDadosDoAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletar(@PathVariable String id) {
        Optional<Aluno> aluno = alunoService.findById(id);

        if (aluno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        alunoService.remove(id);

        return ResponseEntity.ok(null);
    }


}
