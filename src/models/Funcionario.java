package models;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.DecimalFormat;

//2 – Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    public Funcionario(String nome,LocalDate dataNascimento,BigDecimal salario, String funcao){
        this.nome = nome;
        this.funcao = funcao;
        this.salario = salario;
        this.dataNascimento = dataNascimento;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }

    public String getSalarioFormatado() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String salarioFormatado = df.format(this.salario);
        return salarioFormatado;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void meusDados(){
        super.meusDados();
        System.out.println("Salário: " + this.getSalarioFormatado());
        System.out.println("Função: " + this.funcao + "\n");
    }

}
