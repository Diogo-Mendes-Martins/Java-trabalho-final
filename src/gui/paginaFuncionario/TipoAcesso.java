package gui.paginaFuncionario;

import conta.Conta;
import funcionario.Funcionario;
import gui.PaginaLogin;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import principal.Sistema;
import principal.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class TipoAcesso implements ActionListener {
    //Copia as informações das hashs originais
    HashMap<String, String> conta = new HashMap<String, String>();
    HashMap<String, String> cracha = new HashMap<String, String>();

    //Escolha entre acesso a conta bancaria ou painel do funcionario
    //GUI
    JFrame frame = new JFrame(); //Cria frame
    JButton botaoConta = new JButton("Conta"); //Cria o botão de acesso a conta
    JButton botaoFuncionario = new JButton("Painel funcionário"); //Cria o botão de acesso ao painel do funcionário
    JButton botaoSair = new JButton("Sair");


    //Constructor GUI
    public TipoAcesso(HashMap<String, String> crachaOriginal, HashMap<String, String> contaOriginal){

        //Copia os hash originais
        cracha = crachaOriginal;
        conta = contaOriginal;

        //Config da página
        botaoConta.setBounds(55, 75, 200, 25); //Posição do botão Conta
        botaoFuncionario.setBounds(55, 125, 200, 25); //Posição botão Funcionario
        botaoSair.setBounds(55, 175, 200, 25); //Posição botão Sair

        botaoConta.setFocusPainted(false); //Remove frame de seleção de cima do botão Conta
        botaoConta.addActionListener(this); //Lê clique bo botão Conta
        botaoFuncionario.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoFuncionario.addActionListener(this); //Lê clique bo botão login
        botaoSair.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoSair.addActionListener(this); //Lê clique bo botão login


        frame.add(botaoSair);
        frame.add(botaoConta); //Adiciona o botão CONTA ao frame
        frame.add(botaoFuncionario); //Adiciona o botão FUNCIONARIO ao frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Tipo de acesso"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }


    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoConta) { //Ação que acontecerá após clique no botão CONTA
            if(conta.get(Sistema.cpfLogado).equals("Corrente")){ //Se o funcionário logado possuir CONTA CORRENTE
                PaginaCorrente paginaCorrente = new PaginaCorrente(Conta.getNumConta());  //Chama página CONTA CORRENTE
                System.out.println("pagina corrente");
            }
            else if (conta.get(Sistema.cpfLogado).equals("Poupanca")){
                PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama página CONTA POUPANÇA
                System.out.println("pagina poupança");
            }
            frame.dispose();
        }
        else if (e.getSource() == botaoFuncionario) { //Ação que acontecerá após clique no botão FUNCIONARIO
            if (cracha.get(Sistema.cpfLogado).equals("Gerente")) { //Se o usuário for um gerente
                PaginaGerente paginaGerente = new PaginaGerente(); //Chama a pagina do gerente
            }
            else if (cracha.get(Sistema.cpfLogado).equals("Diretor")) { //Se o usuário for um diretor
                PaginaDiretor paginaDiretor = new PaginaDiretor(); //Chama a pagina do diretor
            }
            else if (cracha.get(Sistema.cpfLogado).equals("Presidente")) { //Se o usuário for um presidente
                PaginaPresidente paginaPresidente = new PaginaPresidente(); //Chama a pagina do presidente
            }
            frame.dispose();
        }
        if (e.getSource() == botaoSair) { //Ação que acontecerá após clique no botão CONTA
            PaginaLogin paginaLogin = new PaginaLogin(Usuario.getLogin(), Funcionario.getCracha(), Usuario.getTipoUsuario(), Conta.getConta());
            frame.dispose();
        }
    }
}
