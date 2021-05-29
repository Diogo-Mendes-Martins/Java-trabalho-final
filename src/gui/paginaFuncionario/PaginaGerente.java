package gui.paginaFuncionario;

import conta.Conta;
import funcionario.Diretor;
import funcionario.Funcionario;
import funcionario.Gerente;
import funcionario.Presidente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaginaGerente implements ActionListener {

    //GUI
    JFrame frame = new JFrame();
    JButton botaoVoltar = new JButton("Voltar"); //Cria botão voltar
    JButton botaoContas = new JButton("Contas abertas");
    JPanel painelAzul = new JPanel(); //Cria painel azul
    JTextPane relatorios = new JTextPane(); //Cria painel relatorios
    JScrollPane scroll = new JScrollPane(relatorios); //Cria painel com scroll e adiciona o painel extrato nele


    //Constructor GUI
    public PaginaGerente(){
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão voltar
        botaoVoltar.addActionListener(this); //Lê clique bo botão voltar

        botaoContas.setFocusPainted(false); //Remove frame de seleção de cima do botão Clientes
        botaoContas.addActionListener(this); //Lê clique bo botão Clientes

        painelAzul.add(botaoContas);
        painelAzul.add(botaoVoltar); //Adiciona o botão voltar ao painelAzul
        painelAzul.setBackground(Color.decode("#3C72BD"));; //Seta a cor do painelAzul
        painelAzul.setBounds(0, 0, 180, 420); //Posicionamento painelAzul
        scroll.setBounds(180, 0, 223, 385); //Posicionamento painelScroll
        relatorios.setEditable(false); //Não deixa o painel scroll ser editado
        frame.add(scroll); //Adiciona o painel scroll ao frame
        frame.add(painelAzul); //Adiciona o painel azul ao frame
        frame.setResizable(false); //Não deixa o frame ser escalonado
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(420,420); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Gerente - Banco Serratec"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoContas) { //Ação após apertar o botão Contas
            try {
                Gerente.NumeroContas();
                BufferedReader leRelatorio = new BufferedReader(new FileReader(("relatorioContas"))); //Cria o leitor de texto
                relatorios.read(leRelatorio, "relatorioContas");
                leRelatorio.close();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        else if (e.getSource() == botaoVoltar){ //Se o botão voltar for pressionado
            TipoAcesso tipoAcesso = new TipoAcesso(Funcionario.getCracha(), Conta.getConta()); //Chama a página tipo de acesso
            frame.dispose();
        }
    }
}
