package gui.acoesConta;

import conta.Conta;
import gui.alerta.ErroDestinatario;
import gui.alerta.SaldoInsuficiente;
import gui.alerta.TransacaoRealizada;
import gui.alerta.ValorInvalido;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import principal.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class PaginaSaque implements ActionListener {
    HashMap<String, Conta> numConta = new HashMap<String, Conta>();
    HashMap<String, String> tipoConta = new HashMap<String, String>();


    //GUI
    JFrame frame = new JFrame();
    JTextField caixaValor = new JTextField(); //Cria caixa para o usuario dar o input de valor do saque
    JLabel labelValor = new JLabel("Valor:"); //Label "Valor: "
    JButton botaoSacar = new JButton("Sacar"); //Botão SACAR
    JButton botaoVoltar = new JButton("Voltar");




    //Constructor GUI
    public PaginaSaque(HashMap<String, Conta> numContaOriginal, HashMap<String, String> tipoContaOriginal){
        //Clone dos hashs
        numConta = numContaOriginal;
        tipoConta = tipoContaOriginal;



        //Configs da página
        numConta = numContaOriginal;
        labelValor.setBounds(55, 75, 75, 25); //Posição da label VALOR
        caixaValor.setBounds(55, 100, 200, 25); //Posição caixa VALOR
        botaoVoltar.setBounds(155, 150, 100, 25); //Posição botão de voltar
        botaoSacar.setBounds(55, 150, 100, 25); //Posição botão de login
        botaoSacar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoSacar.addActionListener(this); //Lê clique bo botão login
        frame.add(botaoSacar); //Adiciona botão sacar
        frame.add(botaoVoltar);
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoVoltar.addActionListener(this); //Lê clique bo botão login
        frame.add(caixaValor); //Adiciona caixa valor
        frame.add(labelValor); //Adiciona label valor

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Saque"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }

    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == botaoSacar){ //Ação após apertar o botão Sacar
                Conta contaLogada = numConta.get(Sistema.cpfLogado); //Pega a conta vinculada ao cpf logado através do HASH numConta
                double valorSaque = Double.parseDouble(caixaValor.getText()); //Lê o valor digitado para saque
                int transacaoAprovada = Conta.Saque(valorSaque, contaLogada); //Chama o método Saque()
                if (transacaoAprovada == 1){
                    TransacaoRealizada transacaoRealizada = new TransacaoRealizada(tipoConta); //Chama a pagina TRANSAÇÃO REALIZADA
                    frame.dispose(); //Fecha o frame
                }
                else if (transacaoAprovada == 2){
                    SaldoInsuficiente saldoInsuficiente = new SaldoInsuficiente(); //Chama a pagina SALDO INSUFICIENTE
                }
                else if (transacaoAprovada == 3){
                    ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
                }
            }
        }
        catch (NumberFormatException ex){
            ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        if (e.getSource() == botaoVoltar) { //Ação após apertar o botão Voltar
            if (tipoConta.get(Sistema.cpfLogado).equals("Corrente")) { //Se a conta do usuário for do tipo CORRENTE
                PaginaCorrente paginaCorrente = new PaginaCorrente(Conta.getNumConta()); //Chama a pagina da conta corrente
                frame.dispose();
            }
            else if (tipoConta.get(Sistema.cpfLogado).equals("Poupanca")) { //Se a conta do usuário for do tipo POUPANÇA
                PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama a pagina da conta poupança
                frame.dispose();
            }
        }





    }
}
