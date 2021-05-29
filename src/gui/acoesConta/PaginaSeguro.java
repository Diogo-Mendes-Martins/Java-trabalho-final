package gui.acoesConta;

import conta.Conta;
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
import java.util.HashMap;

public class PaginaSeguro implements ActionListener {
    HashMap<String, Conta> numConta = new HashMap<String, Conta>();
    HashMap<String, String> tipoConta = new HashMap<String, String>();

    //GUI
    JFrame frame = new JFrame();
    JTextField caixaValor = new JTextField();
    JLabel labelValor = new JLabel("Valor assegurado: ");
    JButton botaoContratar = new JButton("Contratar");
    JButton botaoVoltar = new JButton("Voltar");
    JLabel labelMensagem1 = new JLabel("No ato da contratação será debitado 20% do");
    JLabel labelMensagem2 = new JLabel("valor contratado como tributo do seguro.");
    JLabel labelTitulo = new JLabel("Seguro de vida");


    //Constructor GUI
    public PaginaSeguro(HashMap<String, Conta> numContaOriginal, HashMap<String, String> contaOriginal){
        //Clone dos hashs
        numConta = numContaOriginal;
        tipoConta = contaOriginal;

        labelMensagem1.setFont(new Font("Serif", Font.PLAIN, 12));
        labelMensagem2.setFont(new Font("Serif", Font.PLAIN, 12));
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 20));


        caixaValor.setBounds(40, 100, 225, 25); //Posição caixa Valor
        labelValor.setBounds(40, 75, 150, 25); //Posição label Valor
        labelMensagem1.setBounds(40, 125, 225,20);
        labelMensagem2.setBounds(40, 145, 225,20);
        labelTitulo.setBounds(40, 25, 150, 25);
        botaoVoltar.setBounds(150, 180, 112, 25); //Posição botão de voltar


        botaoContratar.setBounds(40, 180, 112, 25); //Posição botão Contratar
        botaoContratar.setFocusPainted(false); //Remove frame de seleção de cima do botão Contratar
        botaoContratar.addActionListener(this); //Lê clique bo botão Contratar
        frame.add(botaoVoltar);
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoVoltar.addActionListener(this); //Lê clique bo botão login


        frame.add(labelTitulo);
        frame.add(labelMensagem1);
        frame.add(labelMensagem2);
        frame.add(botaoContratar);
        frame.add(caixaValor);
        frame.add(labelValor);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Seguro - Banco Serratec"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == botaoContratar) { //Ação após apertar o botão Contratar
                Conta contaLogada = numConta.get(Sistema.cpfLogado); //Pega a conta vinculada ao cpf logado através do HASH numConta
                double valor = Double.parseDouble(caixaValor.getText()); //Lê o valor assegurado digitado e armazena na variavel valor
                int contratado = Conta.Seguro(valor, contaLogada);
                if(contratado == 1){
                    TransacaoRealizada transacaoRealizada = new TransacaoRealizada(tipoConta); //Chama a pagina TRANSAÇÃO REALIZADA
                    frame.dispose(); //Fecha o frame
                }
                else if (contratado == 2){
                    SaldoInsuficiente saldoInsuficiente = new SaldoInsuficiente(); //Chama a pagina SALDO INSUFICIENTE
                }
                else if (contratado== 3){
                    ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
                }
            }
        } catch (NumberFormatException ex){
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
