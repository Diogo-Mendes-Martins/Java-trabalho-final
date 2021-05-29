package gui.alerta;

import conta.Conta;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import principal.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class TransacaoRealizada implements ActionListener {
    HashMap<String, String> conta = new HashMap<String, String>();

    //GUI
    JFrame frame = new JFrame();
    JLabel mensagem = new JLabel("Transação realizada com sucesso!");
    JButton botaoOk = new JButton("Ok");
    JLabel labelRealizada = new JLabel();
    ImageIcon erro = new ImageIcon("src\\gui\\alerta\\checkmark.png");



    //Constructor GUI
    public TransacaoRealizada(HashMap<String, String> contaOriginal) {
        conta = contaOriginal;

        //Configs da página
        labelRealizada.setIcon(erro);
        botaoOk.setBounds(100, 200, 100, 25); //Posição do botão "Ok"
        mensagem.setBounds(43, 160, 300, 25); //Posição da mensagem "Sucesso!"
        labelRealizada.setBounds(85, 35, 200, 130);
        frame.add(botaoOk); //Adiciona o botão "ok" ao frame
        frame.add(mensagem); //Adiciona a mensagem de sucesso ao frame
        frame.add(labelRealizada);
        botaoOk.setFocusPainted(false); //Remove frame de seleção de cima do botão Ok
        botaoOk.addActionListener(this); //Lê clique bo botão Ok
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true); //Visibilidade da página
        frame.setTitle("Sucesso!"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
        mensagem.setFont(new Font("", Font.PLAIN, 14));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoOk){ //Se o botão ok for pressionado
            if (conta.get(Sistema.cpfLogado).equals("Corrente")) { //Se a conta do usuário for do tipo CORRENTE
                PaginaCorrente paginaCorrente = new PaginaCorrente(Conta.getNumConta()); //Chama a pagina da conta corrente
            }
            else if (conta.get(Sistema.cpfLogado).equals("Poupanca")) { //Se a conta do usuário for do tipo POUPANÇA
                PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama a pagina da conta poupança
            }
            frame.dispose(); //Fecha o frame
        }
    }
}
