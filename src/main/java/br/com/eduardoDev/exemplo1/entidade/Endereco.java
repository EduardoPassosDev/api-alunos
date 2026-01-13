package br.com.eduardoDev.exemplo1.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;
}
