package gui.paginaConta;

import conta.Conta;
import funcionario.Funcionario;
import gui.PaginaLogin;
import gui.acoesConta.*;
import gui.paginaFuncionario.TipoAcesso;
import principal.Sistema;
import principal.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;

public class PaginaCorrente implements ActionListener {
    HashMap<String, Conta> conta = new HashMap<String, Conta>();
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //GUI
    JFrame frame = new JFrame();
    JButton botaoSaque = new JButton("Saque");
    JButton botaoDeposito = new JButton("Depósito");
    JButton botaoTransferencia = new JButton("Transferencia");
    JButton botaoDeslogar = new JButton("Sair");
    JLabel labelSaldo = new JLabel();
    JLabel textoSaldo = new JLabel("Saldo disponível ");
    JButton botaoExtrato = new JButton("Extrato");
    JButton botaoSeguro = new JButton("Seguro");
    JPanel painelBotoes = new JPanel();
    JPanel painelSaldos = new JPanel();

    //Constructor GUI
    public PaginaCorrente(HashMap<String, Conta> contaOriginal){
        conta = contaOriginal;

        //Configs da página
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(420,420); //Tamanho da janela
        frame.setLayout(null);
        painelSaldos.setLayout(null);
        painelBotoes.setLayout(new GridLayout(3,2,5,5));
        frame.setVisible(true);
        frame.setTitle("Conta Corrente - Banco Serratec"); //Titulo da janela
        painelSaldos.add(textoSaldo);
        painelSaldos.add(labelSaldo); //Adiciona a label Saldo ao frame
        labelSaldo.setVisible(true);
        Conta contaLogada = conta.get(Sistema.cpfLogado); //Pega a conta vinculada ao cpf logado através do HASH numConta
        labelSaldo.setText(String.valueOf(contaLogada.getSaldo())); //Insere o valor do saldo na label SALDO
        textoSaldo.setBounds(25, 20, 420, 36);
        labelSaldo.setBounds(25, 50, 420, 36); //Posição da label saldo
        labelSaldo.setFont(new Font("serif", Font.PLAIN, 36));

        painelBotoes.setBounds(0,120,405,261);
        painelBotoes.setBackground(Color.decode("#3C72BD"));
        painelSaldos.setBounds(0,0,420,200);








        frame.add(painelBotoes);
        frame.add(painelSaldos);

        painelBotoes.add(botaoSaque); //Adiciona botão saque ao frame
        painelBotoes.add(botaoDeposito); //Adiciona botão depósito ao frame
        painelBotoes.add(botaoTransferencia); //Adiciona botão depósito ao frame
        painelBotoes.add(botaoExtrato); //Adiciona o botão extrato ao frame
        painelBotoes.add(botaoSeguro);
        painelBotoes.add(botaoDeslogar);


        botaoDeslogar.setFocusPainted(false); //Remove frame de seleção de cima do botão transferencia
        botaoDeslogar.addActionListener(this); //Lê clique bo botão transferencia



        botaoTransferencia.setBounds(125, 100, 100, 25); //Posição botão de transferencia
        botaoTransferencia.setFocusPainted(false); //Remove frame de seleção de cima do botão transferencia
        botaoTransferencia.addActionListener(this); //Lê clique bo botão transferencia



        botaoSaque.setBounds(125, 150, 100, 25); //Posição botão de saque
        botaoSaque.setFocusPainted(false); //Remove frame de seleção de cima do botão saque
        botaoSaque.addActionListener(this); //Lê clique bo botão saque
        botaoDeposito.setBounds(125, 200, 100, 25); //Posição botão de depósito
        botaoDeposito.setFocusPainted(false); //Remove frame de seleção de cima do botão depósito
        botaoDeposito.addActionListener(this); //Lê clique bo botão depósito
        botaoExtrato.setBounds(125, 250, 100, 25); //Posição botão de Extrato
        botaoExtrato.setFocusPainted(false); //Remove frame de seleção de cima do botão Extrato
        botaoExtrato.addActionListener(this); //Lê clique bo botão Extrato
        botaoSeguro.setBounds(250, 150, 100, 25); //Posição botão Serviços
        botaoSeguro.setFocusPainted(false); //Remove frame de seleção de cima do botão Serviços
        botaoSeguro.addActionListener(this); //Lê clique bo botão Serviços
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
        double saldoConta = contaLogada.getSaldo();

        labelSaldo.setText(String.valueOf(formatter.format(saldoConta))); //Insere o valor do saldo na label SALDO




    }

    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoSaque){ //Ação do botão saque
            int a; //Variavel pro sistema n encher o saco
            PaginaSaque paginaSaque = new PaginaSaque(Conta.getNumConta(), Conta.getConta()); //Chama a pagina da ação SAQUE
            frame.dispose(); //Fecha o frame
        }
        else if (e.getSource() == botaoDeposito){ //Ação do botão deposito
            PaginaDeposito paginaDeposito = new PaginaDeposito(Conta.getNumConta(), Conta.getConta()); //Chama a pagina da ação DEPOSITO
            frame.dispose(); //Fecha o frame
        }
        else if (e.getSource() == botaoTransferencia){ //Ação do botão transferencia
            PaginaTransferencia paginaTransferencia = new PaginaTransferencia(Conta.getNumConta(), Conta.getConta()); //Chama a pagina da ação TRANSFERENCIA
            frame.dispose(); //Fecha o frame
        }
        else if (e.getSource() == botaoExtrato){ //Ação do botão extrato
            PaginaExtrato paginaExtrato = new PaginaExtrato(); //Chama a pagina da ação EXTRATO
            frame.dispose();
        }
        else if (e.getSource() == botaoSeguro) { //Ação após apertar o botão Seguro
            PaginaSeguro paginaSeguro = new PaginaSeguro(Conta.getNumConta(), Conta.getConta());
            frame.dispose();
        }
        else if (e.getSource() == botaoDeslogar){ //Ação do botão serviços
            if (Usuario.tipoUsuario.get(Sistema.cpfLogado).equals("Cliente")){
                PaginaLogin paginaLogin = new PaginaLogin(Usuario.getLogin(), Funcionario.getCracha(), Usuario.getTipoUsuario(), Conta.getConta());
            }
            else if (Usuario.tipoUsuario.get(Sistema.cpfLogado).equals("Funcionario")){
                TipoAcesso tipoAcesso = new TipoAcesso(Funcionario.getCracha(), Conta.getConta()); //Chama a página tipo de acesso
            }
            frame.dispose();
        }
    }
}
