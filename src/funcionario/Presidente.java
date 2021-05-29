package funcionario;

import conta.Conta;
import principal.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

public class Presidente extends Funcionario{
    private double partLucros;


    //Constructor
    public Presidente(String nome, String sobrenome, String cpf, String usuario, String senha, String cargo, double salario, double partLucros, int numConta) {
        super(nome, sobrenome, cpf, usuario, senha, cargo, salario, numConta);
        this.partLucros = partLucros;
    }
    public static void CriaPresidente(Presidente funcionario){
        Usuario.login.put(funcionario.getCpf(), funcionario.getSenha());
        Funcionario.cracha.put(funcionario.getCpf(), funcionario.getCargo());
        Usuario.tipoUsuario.put(funcionario.getCpf(), funcionario.getUsuario());
    }


    //Capital do banco
    public static void Capital(){
        double capital = 0;
        Set<String> keys = Conta.numConta.keySet();
        for(String key:keys) {
            Conta contaAux = Conta.numConta.get(key);
            capital = contaAux.getSaldo() + capital;
            }
        try{
            FileWriter relatorioCapital = new FileWriter("relatorioCapital");
            relatorioCapital.write("\nRelatório de capital\n\nO capital total armazenado no banco é R$"+capital);
            relatorioCapital.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
