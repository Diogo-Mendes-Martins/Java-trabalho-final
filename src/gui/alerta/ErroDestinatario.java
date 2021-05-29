package gui.alerta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErroDestinatario implements ActionListener {
    //GUI
    JFrame frame = new JFrame();
    JLabel mensagem = new JLabel("Destinatario não encontrado!");
    JButton botaoOk = new JButton("Ok");
    JLabel labelErro = new JLabel();
    ImageIcon erro = new ImageIcon("src\\gui\\alerta\\erro.png");




    //Constructor GUI
    public ErroDestinatario(){

        //Configs da página
        labelErro.setIcon(erro);
        botaoOk.setBounds(100, 200, 100, 25); //Posição do botão "Ok"
        mensagem.setBounds(60, 150, 200, 25); //Posição da mensagem "Destinatário não encontrado!"
        labelErro.setBounds(85, 35, 200, 100);
        frame.add(botaoOk); //Adiciona o botão "ok" ao frame
        frame.add(mensagem); //Adiciona a mensagem de erro ao frame
        frame.add(labelErro);
        botaoOk.setFocusPainted(false); //Remove frame de seleção de cima do botão Ok
        botaoOk.addActionListener(this); //Lê clique bo botão Ok
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true); //Visibilidade da página
        frame.setTitle("Erro"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
        mensagem.setFont(new Font("", Font.PLAIN, 14));

    }

    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoOk){ //Se o botão ok for pressionado
            frame.dispose(); //Fecha o frame
        }
    }
}
