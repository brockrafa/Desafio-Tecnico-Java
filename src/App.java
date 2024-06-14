import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import models.Funcionario;

public class App{
    public static void main(String[] args) {

        // 3.1- Criação da lista de funcionarios
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria",LocalDate.of(2000,10,18),new BigDecimal("2009.44"),"Operador"));
        funcionarios.add(new Funcionario("João",LocalDate.of(1990,05,12),new BigDecimal("2284.38"),"Operador"));
        funcionarios.add(new Funcionario("Caio",LocalDate.of(1961,05,02),new BigDecimal("9836.14"),"Coordenador"));
        funcionarios.add(new Funcionario("Miguel",LocalDate.of(1988,10,14),new BigDecimal("19119.88"),"Diretor"));
        funcionarios.add(new Funcionario("Alice",LocalDate.of(1995,01,5),new BigDecimal("2234.68"),"Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",LocalDate.of(1999,11,19),new BigDecimal("1582.72"),"Operador"));
        funcionarios.add(new Funcionario("Arthur",LocalDate.of(1993,03,31),new BigDecimal("4071.84"),"Contador"));
        funcionarios.add(new Funcionario("Laura",LocalDate.of(1994,7,8),new BigDecimal("3017.45"),"Gerente"));
        funcionarios.add(new Funcionario("Heloísa",LocalDate.of(2003,05,24),new BigDecimal("1606.85"),"Eletricista"));
        funcionarios.add(new Funcionario("Helena",LocalDate.of(1996,9,02),new BigDecimal("2799.93"),"Gerente"));

        // 3.2- Remove o funcionario João
        Optional<Funcionario> f = funcionarios.stream().filter(funcionario -> funcionario.getNome().equals("João")).findFirst();
        f.ifPresentOrElse(funcionario -> funcionarios.remove(funcionario),()-> System.out.println("Funcionário não encontrado"));

        //3.3- Imprime todos os funcionarios
        funcionarios.stream().forEach(funcionario -> {
            funcionario.meusDados();
        });
        
        //3.4- Aumento de 10% para os funcionarios
        System.out.println("\nAumento de 10% para funcionários");
        System.out.println("____________________________________\n");
        funcionarios.stream().map(
                funcionario -> {
                    BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.1"));
                    BigDecimal novoSalario = funcionario.getSalario().add(aumento);
                    funcionario.setSalario(novoSalario);
                    return funcionario;
                }
        ).forEach(funcionario -> {
            System.out.println("Novo salário de " + funcionario.getNome() + ": R$" + funcionario.getSalarioFormatado());
            System.out.println("\n");
        });

        //3.5- Agrupar os funcionarios em um MAP onde a chave é a função
        Map<String,List<Funcionario>> funcionariosFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6- Imprimir os funcionários, agrupados por função
        System.out.println("\nListagem de funcionários por Função");
        System.out.println("____________________________________\n");
        funcionariosFuncao.forEach((funcao,funcs) -> {    
                System.out.println(funcao);
                funcs.forEach(funcionario -> {
                    System.out.println("- " + funcionario.getNome());
                });
                System.out.println("_______________");
        });

        //3.8- Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\n Lista de funcionarios que fazem aniversário no mês 10 ou 12");
        System.out.println("_________________________________________________________________\n");
        funcionarios.stream().filter(funcionario -> {
            return funcionario.getDataNascimento().getMonthValue() == 10  || funcionario.getDataNascimento().getMonthValue() == 12;
        }).forEach(funcionario -> {
            funcionario.meusDados();
        });
        System.out.println("_________________________________________________________________\n");

        //3.9- Encontrar o funcionário com maior idade
        System.out.println("Funcionário com maior idade ");
        funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).ifPresent(funcionario -> {
                int idade = Period.between(funcionario.getDataNascimento(),LocalDate.now()).getYears();
                System.out.println("Nome: " + funcionario.getNome() + "\nIdade: " + idade );
            }
        );
        System.out.println("_________________________________________________________________\n");

        //3.10- Ordenar a lista em ordem alfabetica
        System.out.println("\nFuncionários ordenados alfabeticamente");
        System.out.println("_____________________________________\n");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        funcionarios.stream().forEach(funcionario -> {
            funcionario.meusDados();
        });
        System.out.println("_________________________________________________________________\n");

        //3.11- Total dos salários de funcionarios
        BigDecimal somaSalario = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("Soma dos salários de funcionários: R$ " + somaSalario.setScale(2,BigDecimal.ROUND_HALF_DOWN));
        System.out.println("_________________________________________________________________\n");

        //3.12- Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        funcionarios.stream().forEach(funcionario ->{
            BigDecimal qtdSalario = funcionario.getSalario().divide(new BigDecimal("1212"),2,BigDecimal.ROUND_HALF_DOWN);
            System.out.println("Quantidade de salários minimos do funcionário(a): " + funcionario.getNome());
            System.out.println("Quantidade de salário: " + qtdSalario);
            System.out.println("");
        });

    }
}