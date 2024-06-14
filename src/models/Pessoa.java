package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//1– Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
public class Pessoa {
    String nome;
    LocalDate dataNascimento;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void meusDados(){
        System.out.println("Nome: " + this.nome);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = this.dataNascimento.format(formatter);
        System.out.println("Data nascimento: " + dataFormatada);
    }
}
