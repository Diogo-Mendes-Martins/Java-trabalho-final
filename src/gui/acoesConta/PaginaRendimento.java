package gui.acoesConta;

import conta.Conta;
import gui.alerta.ValorInvalido;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import principal.Sistema;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class PaginaRendimento implements ActionListener {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    //GUI
    JFrame frame = new JFrame();
    JTextField caixaValor = new JTextField();
    JTextField caixaDias = new JTextField();
    JLabel labelValor = new JLabel("Valor:");
    JLabel labelDias = new JLabel("Dias:");
    JButton botaoSimular = new JButton("Simular");
    JLabel valorRendimento = new JLabel();
    JButton botaoVoltar = new JButton("Voltar");
    JLabel mensagemRendimento1 = new JLabel("O valor descrito irá render: ");
    JLabel mensagemRendimento2 = new JLabel();



    //Constructor GUI
    public PaginaRendimento(){
        labelValor.setBounds(50, 25, 75, 25); //Posição label Valor
        caixaValor.setBounds(50, 50, 200, 25); //Posição caixa Valor
        labelDias.setBounds(50, 75, 75, 25); //Posição label Dias
        caixaDias.setBounds(50, 100, 200, 25); //Posição caixa Dias
        botaoSimular.setBounds(50, 150, 100, 25); //Posição botão Simular
        botaoVoltar.setBounds(150, 150, 100, 25); //Posição botão de voltar
        mensagemRendimento1.setBounds(50,190,200,25);
        valorRendimento.setBounds(50,215,200,25); //Posição label rendimento
        mensagemRendimento2.setBounds(50,240,200,25);
        frame.add(mensagemRendimento1);
        frame.add(mensagemRendimento2);
        botaoSimular.setFocusPainted(false); //Remove frame de seleção de cima do botão Simular
        botaoSimular.addActionListener(this); //Lê clique bo botão Simular
        valorRendimento.setVisible(false);
        mensagemRendimento2.setVisible(false);
        mensagemRendimento1.setVisible(false);
        frame.add(botaoVoltar);
        botaoVoltar.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoVoltar.addActionListener(this); //Lê clique bo botão login
        frame.add(valorRendimento);
        frame.add(caixaDias);
        frame.add(caixaValor);
        frame.add(labelDias);
        frame.add(labelValor);
        frame.add(botaoSimular);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(320,320); //Tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Simulador de rendimento"); //Titulo da janela
        frame.setLocationRelativeTo(null); //Centraliza tela
        frame.setResizable(false); //Impede mudança no tamanho da janela
    }


    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == botaoSimular) { //Ação após apertar o botão Simular
                double valor = Double.parseDouble(caixaValor.getText()); //Lê o valor digitado para simulação e armazena na variavel valor
                int dias = Integer.parseInt(caixaDias.getText()); //Lê a qtd de dias para a simulaçao e armazena na variavel dias
                double rendimento = Conta.Rendimento(valor, dias);
                if (rendimento == 0){
                    //CASO SOBRAR TEMPO FAZER PÁGINA DE ERRO PROPRIA
                    ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
                }
                else{
                    mensagemRendimento1.setVisible(true);
                    valorRendimento.setText(String.valueOf(formatter.format(rendimento)));
                    valorRendimento.setVisible(true);
                    mensagemRendimento2.setText("no período de " + dias + " dias.");
                    mensagemRendimento2.setVisible(true);

                }
            }
        }
        catch(NumberFormatException ex){
            ValorInvalido valorInvalido = new ValorInvalido(); //Chama a pagina VALOR INVALIDO
        }
        if (e.getSource() == botaoVoltar) { //Ação após apertar o botão Voltar
            PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama a pagina da conta poupança
            frame.dispose();
        }
    }
}
