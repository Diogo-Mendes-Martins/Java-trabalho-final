package funcionario;

import principal.Usuario;

import java.io.FileWriter;
import java.io.IOException;

import static principal.Sistema.numContas;

public class Gerente extends Funcionario{
    private int numFuncionarios;
    private int agencia;

    //Constructor
    public Gerente(String nome, String sobrenome, String cpf, String usuario, String senha, String cargo, double salario, int numFuncionarios, int agencia, int numConta) {
        super(nome, sobrenome, cpf, usuario, senha, cargo, salario, numConta);
        this.numFuncionarios = numFuncionarios;
        this.agencia = agencia;
    }
    public static void CriaGerente(Gerente funcionario){
        Usuario.login.put(funcionario.getCpf(), funcionario.getSenha());
        Funcionario.cracha.put(funcionario.getCpf(), funcionario.getCargo());
        Usuario.tipoUsuario.put(funcionario.getCpf(), funcionario.getUsuario());
    }










    //Relatório contas abertas
    public static void NumeroContas(){
        try{
            FileWriter relatorioContas = new FileWriter("relatorioContas");
            relatorioContas.write("\nRelatório de contas por agência\n\nEsta agência possui " + numContas + " contas abertas.");
            relatorioContas.close();
        } catch (IOException e){
            System.out.println("deu ruim");
        }
    }
    //Getters e Setters
    public int getNumFuncionarios() {
        return numFuncionarios;
    }
    public void setNumFuncionarios(int numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }
    public int getAgencia() {
        return agencia;
    }
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
}
