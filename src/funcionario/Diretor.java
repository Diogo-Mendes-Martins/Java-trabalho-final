package funcionario;

import conta.Conta;
import principal.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Diretor extends Funcionario{
    public Diretor(String nome, String sobrenome, String cpf, String usuario, String senha, String cargo, double salario, int numConta) {
        super(nome, sobrenome, cpf, usuario, senha, cargo, salario, numConta);
    }

    public static void CriaDiretor(Diretor funcionario){
        Usuario.login.put(funcionario.getCpf(), funcionario.getSenha());
        Funcionario.cracha.put(funcionario.getCpf(), funcionario.getCargo());
        Usuario.tipoUsuario.put(funcionario.getCpf(), funcionario.getUsuario());
    }













    //Relatorio de clientes
    public static void RelatorioClientes(HashMap<String, Conta> numConta){
        ArrayList<String> dados = new ArrayList<String>();
        for (Conta conta : numConta.values()){
           dados.add("\n" + conta.getTitular() +"\nCPF: "+ conta.getCpfTitular() +"\nAgência: "+ conta.getAgencia());
        }
        java.util.Collections.sort(dados);
        try{
            FileWriter relatorioCLientes = new FileWriter("relatorioClientes");
            for (String aux: dados){
                relatorioCLientes.write(aux + System.lineSeparator());
            }
            relatorioCLientes.close();
        } catch (IOException e){
            System.out.println("deu ruim");
        }
    }








}
