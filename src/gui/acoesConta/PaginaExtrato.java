package gui.acoesConta;

import conta.Conta;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import principal.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PaginaExtrato implements ActionListener {



    //GUI
    JFrame frame = new JFrame(); //Cria novo frame
    JButton botaoVoltar = new JButton("Voltar"); //Cria botão voltar
    JTextPane extrato = new JTextPane(); //Cria painel extrato
    JPanel painelAzul = new JPanel(); //Cria painel azul
    JScrollPane scroll = new JScrollPane(extrato); //Cria painel com scroll e adiciona o painel extrato nele
    JLabel labelExtrato = new JLabel();
    ImageIcon iconeExtrato = new ImageIcon("src\\gui\\acoesConta\\extrato.png");




    //Constructor GUI
    public PaginaExtrato(){

        labelExtrato.setIcon(iconeExtrato);
        painelAzul.add(labelExtrato);
        painelAzul.add(botaoVoltar); //Adiciona o botão voltar ao painelAzul
        painelAzul.setBackground(Color.decode("#3C72BD"));
        labelExtrato.setBounds(28,0,180,200);
        botaoVoltar.setBounds(40,325,100,25);
        painelAzul.setLayout(null);
        painelAzul.setBounds(0, 0, 180, 420); //Posicionamento painelAzul
        scroll.setBounds(180, 0, 223, 385); //Posicionamento painelScroll
        extrato.setEditable(false); //Não deixa o painel scroll ser editado
        frame.add(scroll); //Adiciona o painel scroll ao frame
        frame.add(painelAzul); //Adiciona o painel azul ao frame
        frame.setResizable(false); //Não deixa o frame ser escalonado
        frame.setLayout(null);
        frame.setVisible(true); //Deixa o frame visivel
        frame.setTitle("Extrato"); //Titulo da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(420,420); //Tamanho da janela
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão voltar
        botaoVoltar.addActionListener(this); //Lê clique bo botão voltar
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela








        //Geração do extrato bancário
        BufferedReader leExtrato = null;
        try {
            BufferedWriter escreveExtrato = new BufferedWriter(new FileWriter("extrato")); //Cria escritor de texto
            ArrayList<String> temporaria = new ArrayList<String>(); //Lista auxiliar
            temporaria = Conta.transacoes.get(Sistema.cpfLogado); //Copia lista de transações pra lista auxiliar
            for (int i = 0; i < temporaria.size(); i++) { //Loop para adicionar as transações da array no arquivo de texto
                String texto = temporaria.get(i); //Adiciona a transação da lista na variavel texto
                escreveExtrato.write(texto); //Escreve no arquivo de texto
            }
            escreveExtrato.close(); //Fecha o escritor de texto
            leExtrato = new BufferedReader(new FileReader("extrato")); //Cria o leitor de texto
            extrato.read(leExtrato, "extrato"); //Lê o arquivo de texto
            leExtrato.close(); //Fecha o leitor de texto




        } catch (IOException e) {
            System.out.println("erro jpanel");
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVoltar){ //Se o botão voltar for pressionado
            if (Conta.conta.get(Sistema.cpfLogado).equals("Corrente")) { //Se a conta do usuário for do tipo CORRENTE
                PaginaCorrente paginaCorrente = new PaginaCorrente(Conta.getNumConta()); //Chama a pagina da conta corrente
            }
            else if(Conta.conta.get(Sistema.cpfLogado).equals("Poupanca")) { //Se a conta do usuário for do tipo POUPANÇA)
                PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama a pagina da conta poupança
            }
            frame.dispose();//Fecha o frame
        }
    }
}


