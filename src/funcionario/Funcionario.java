package funcionario;

import cliente.Cliente;
import jdk.nashorn.internal.ir.FunctionCall;
import principal.Usuario;

import java.util.HashMap;

public class Funcionario extends Usuario {
    private String cargo;
    private double salario;
    private int numConta;


    //Hashmap CPF - Cargo
    public static HashMap<String, String> cracha = new HashMap<String,String>();

    //Constructor
    public Funcionario(String nome, String sobrenome, String cpf, String usuario, String senha, String cargo, double salario, int numConta) {
        super(nome, sobrenome, cpf, usuario, senha);
        this.cargo = cargo;
        this.salario = salario;
        this.numConta = numConta;
    }
    public Funcionario() {
    }

    //Getters e Setters
    public String getCargo() {
        return cargo;
    }

    public static HashMap<String, String> getCracha() {
        return cracha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public static void setCracha(HashMap<String, String> cracha) {
        Funcionario.cracha = cracha;
    }


}
