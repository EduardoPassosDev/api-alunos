package br.com.eduardoDev.exemplo1.controller;

import br.com.eduardoDev.exemplo1.dto.AlunoDTO;
import br.com.eduardoDev.exemplo1.entidade.Aluno;
import br.com.eduardoDev.exemplo1.entidade.Endereco;
import br.com.eduardoDev.exemplo1.httpClient.CepHttpCliente;
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

    @Autowired
    CepHttpCliente cepHttpCliente;

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
    public ResponseEntity<Aluno> inserir(@RequestBody @Valid AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno(alunoDTO);

        Endereco endereco = cepHttpCliente.obterEnderecoPeloCep(alunoDTO.getCep());

        aluno.setEndereco(endereco);

        alunoService.inserir(aluno);

        return ResponseEntity.created(null).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@RequestBody Aluno novosDadosAluno, @PathVariable String id ) {
        Optional<Aluno> aluno = alunoService.findById(id);

        if(aluno.isEmpty()){
            return ResponseEntity.notFound().build();
        }

       Aluno responseAluno = alunoService.atualizar(id, novosDadosAluno);
        return ResponseEntity.ok().body(responseAluno);
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
