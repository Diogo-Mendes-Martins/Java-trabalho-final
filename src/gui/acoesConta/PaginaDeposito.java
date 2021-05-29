package gui.acoesConta;

import conta.Conta;
import gui.alerta.TransacaoRealizada;
import gui.alerta.ValorInvalido;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import principal.Sistema;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PaginaDeposito implements ActionListener {

    HashMap<String, Conta> numConta = new HashMap<String, Conta>();
    HashMap<String, String> tipoConta = new HashMap<String, String>();

    //GUI
    JFrame frame = new JFrame();
    JButton botaoDepositar = new JButton("Depositar");
    JTextField caixaDepositar = new JTextField();
    JLabel labelDepositar = new JLabel("Valor:");
    JButton botaoVoltar = new JButton("Voltar");

    //Constructor GUI
    public PaginaDeposito(HashMap<String, Conta> numContaOriginal, HashMap<String, String> tipoContaOriginal){

        //Clone hashs
        numConta = numContaOriginal;
        tipoConta = tipoContaOriginal;




        //Configs da página
        labelDepositar.setBounds(55, 75, 75, 25); //Posição label depositar
        caixaDepositar.setBounds(55, 100, 200, 25); //Caixa de texto depositar
        botaoDepositar.setBounds(55, 150, 100, 25); //Botao depositar
        botaoVoltar.setBounds(155, 150, 100, 25); //Posição botão de voltar


        botaoDepositar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoDepositar.addActionListener(this); //Lê clique bo botão depositar
        frame.add(botaoDepositar);
        frame.add(caixaDepositar);
        frame.add(labelDepositar);
        frame.add(botaoVoltar);
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoVoltar.addActionListener(this); //Lê clique bo botão login
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Depósito"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }

    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == botaoDepositar){ //Se o botão depositar for pressionado
                Conta contaLogada = numConta.get(Sistema.cpfLogado); //Pega a conta vinculada ao cpf logado através do HASH numConta
                double valorDeposito = Double.parseDouble(caixaDepositar.getText()); //Lê o valor digitado para depósito
                boolean transacaoAprovada = Conta.Deposito(valorDeposito, contaLogada); //Chama o método Deposito()
                if (transacaoAprovada) {
                    TransacaoRealizada transacaoRealizada = new TransacaoRealizada(tipoConta); //Chama a pagina TRANSAÇÃO REALIZADA
                    frame.dispose();
                }
                else{
                    ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
                }
            }
        }
        catch(NumberFormatException ex){
            ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
        }
        if (e.getSource() == botaoVoltar) { //Ação após apertar o botão Voltar
            if (tipoConta.get(Sistema.cpfLogado).equals("Corrente")) { //Se a conta do usuário for do tipo CORRENTE
                PaginaCorrente paginaCorrente = new PaginaCorrente(Conta.getNumConta()); //Chama a pagina da conta corrente
            }
            else if (tipoConta.get(Sistema.cpfLogado).equals("Poupanca")) { //Se a conta do usuário for do tipo POUPANÇA
                PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama a pagina da conta poupança
            }
            frame.dispose();
        }
    }
}
