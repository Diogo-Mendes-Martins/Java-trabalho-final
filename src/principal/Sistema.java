package principal;

import cliente.Cliente;
import conta.Conta;
import conta.Corrente;
import conta.Poupanca;
import funcionario.Diretor;
import funcionario.Funcionario;
import funcionario.Gerente;
import funcionario.Presidente;
import gui.PaginaLogin;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static principal.IO.leitor;

public class Sistema {
    static public String cpfLogado;
    static public int numContas;

    public static void main(String[] args) throws FileNotFoundException {
        PaginaLogin paginaLogin = new PaginaLogin(Usuario.getLogin(), Funcionario.getCracha(), Usuario.getTipoUsuario(), Conta.getConta());
        IO.leitor("src\\bancoDeDados.txt");
    }
}
