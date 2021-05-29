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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PaginaTransferencia implements ActionListener {
    HashMap<String, Conta> numConta = new HashMap<String, Conta>();
    HashMap<String, String> tipoConta = new HashMap<String, String>();

    //GUI
    JFrame frame = new JFrame();
    JButton botaoTransferir = new JButton("Transferir"); //Botão transferir
    JTextField caixaValor = new JTextField(); //Caixa valor a ser transferido
    JTextField caixaCPF = new JTextField(); //Caixa CPF destinatário
    JLabel cpfDestinatario = new JLabel("Chave pix:"); //Label CPF destinatario
    JLabel valorTransferencia = new JLabel("Valor:"); //Label valor
    JButton botaoVoltar = new JButton("Voltar");

    //Constructor GUI
    public PaginaTransferencia(HashMap<String, Conta> numContaOriginal, HashMap<String, String> tipoContaOriginal){
        //Copia a hash original
        numConta = numContaOriginal;
        tipoConta = tipoContaOriginal;

        //Configs da página
        cpfDestinatario.setBounds(55, 100, 75, 25); //Posição label Destinatario
        valorTransferencia.setBounds(55, 50, 75, 25); //Posição label valor

        caixaValor.setBounds(55, 75, 200, 25); //Posição caixa valor
        caixaCPF.setBounds(55, 125, 200, 25); //Posição caixa CPF destinatario
        botaoVoltar.setBounds(155, 175, 100, 25); //Posição botão voltar
        botaoTransferir.setBounds(55, 175, 100, 25); //Posição botão de transferir
        botaoTransferir.setFocusPainted(false); //Remove frame de seleção de cima do botão transferir
        botaoTransferir.addActionListener(this); //Lê clique bo botão transferir
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoVoltar.addActionListener(this); //Lê clique bo botão login
        frame.add(botaoVoltar);
        frame.add(caixaCPF); //Adiciona a caixa destinatario
        frame.add(caixaValor); //Adiciona a caixa valor
        frame.add(botaoTransferir); //Adiciona o botão transferir
        frame.add(cpfDestinatario); //Adiciona a label destinatario
        frame.add(valorTransferencia); //Adiciona a label valor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Transferência"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }

    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            try {
                if (e.getSource() == botaoTransferir) { //Se o botão transferir for pressionado
                    Conta contaLogada = numConta.get(Sistema.cpfLogado); //Pega a conta vinculada ao cpf logado através do HASH numConta
                    double valor = Double.parseDouble(caixaValor.getText()); //Lê o valor digitado para transeferencia e armazena na variavel valor
                    String destinatario = caixaCPF.getText(); //Lê o cpf digitado e armazena na variavel destinatario
                    Conta contaDestinatario = numConta.get(destinatario); //Pega a conta vinculada ao cpf do destinatario
                    System.out.println(contaLogada.getSaldo());
                    System.out.println(contaDestinatario.getSaldo());
                    System.out.println(" ");
                    int transacaoAprovada = Conta.Transferencia(valor, contaLogada, contaDestinatario); //Método transferência
                    if (transacaoAprovada == 1) {
                        System.out.println(contaLogada.getSaldo());
                        System.out.println(contaDestinatario.getSaldo());
                        TransacaoRealizada transacaoRealizada = new TransacaoRealizada(tipoConta); //Chama a pagina TRANSAÇÃO REALIZADA
                        frame.dispose(); //Fecha o frame
                    } else if (transacaoAprovada == 2) {
                        SaldoInsuficiente saldoInsuficiente = new SaldoInsuficiente(); //Chama a pagina SALDO INSUFICIENTE
                    } else if (transacaoAprovada == 3) {
                        ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
                    }
                }
            } catch (NullPointerException ex) {
                ErroDestinatario erroDestinatario = new ErroDestinatario(); //Chama a pagina ERRO DESTINATARIO
            }
        }
        catch(NumberFormatException ex){
            ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
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

