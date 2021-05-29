package principal;

import cliente.Cliente;
import conta.Conta;
import conta.Corrente;
import conta.Poupanca;
import funcionario.Diretor;
import funcionario.Gerente;
import funcionario.Presidente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IO {
    public static void leitor(String path) throws FileNotFoundException {
        try {
            BufferedReader leitorBd = new BufferedReader(new FileReader(path));
            String linha = "";
            while (true) {
                linha = leitorBd.readLine();
                if (linha != null) {
                    String[] pp = linha.split(";");
                    if (pp[3].equals("Cliente")) {
                        Cliente cliente = new Cliente(pp[0], pp[1], pp[2], pp[3], pp[4], Integer.parseInt(pp[5]));
                        Cliente.CriarCliente(cliente);
                    }
                    else if (pp[3].equals("Funcionario")) {
                        if (pp[5].equals("Gerente")) {
                            Gerente gerente = new Gerente(pp[0], pp[1], pp[2], pp[3], pp[4], pp[5], Double.parseDouble(pp[6]), Integer.parseInt(pp[7]), Integer.parseInt(pp[8]), Integer.parseInt(pp[9]));
                            Gerente.CriaGerente(gerente);
                            System.out.println(gerente.getSenha());
                        } else if (pp[5].equals("Diretor")) {
                            Diretor diretor = new Diretor(pp[0], pp[1], pp[2], pp[3], pp[4], pp[5], Double.parseDouble(pp[6]), Integer.parseInt(pp[7]));
                            System.out.println(diretor.getSenha());
                            Diretor.CriaDiretor(diretor);
                        } else if (pp[5].equals("Presidente")) {
                            Presidente presidente = new Presidente(pp[0], pp[1], pp[2], pp[3], pp[4], pp[5], Double.parseDouble(pp[6]), Double.parseDouble(pp[7]), Integer.parseInt(pp[8]));
                            Presidente.CriaPresidente(presidente);
                            System.out.println(presidente.getSenha());

                        }
                    }
                    else if (pp[3].equals("Corrente")) {
                        Corrente contaC = new Corrente(Integer.parseInt(pp[0]),Integer.parseInt(pp[1]),Double.parseDouble(pp[2]), pp[3], pp[4], pp[5]);
                        Conta.CriaConta(contaC);
                    }
                    else if (pp[3].equals("Poupanca")) {
                        Poupanca contaP = new Poupanca(Integer.parseInt(pp[0]),Integer.parseInt(pp[1]),Double.parseDouble(pp[2]), pp[3], pp[4], pp[5]);
                        Conta.CriaConta(contaP);
                    }
                }
                else{
                    break;
                }
            }
            leitorBd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
