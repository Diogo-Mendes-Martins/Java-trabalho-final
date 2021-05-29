package gui;

import conta.Conta;
import funcionario.Funcionario;
import gui.alerta.SenhaIncorreta;
import gui.alerta.UsuarioInvalido;
import gui.alerta.ValorInvalido;
import gui.paginaConta.PaginaCorrente;
import gui.paginaConta.PaginaPoupanca;
import gui.paginaFuncionario.PaginaDiretor;
import gui.paginaFuncionario.PaginaGerente;
import gui.paginaFuncionario.PaginaPresidente;
import gui.paginaFuncionario.TipoAcesso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static principal.Sistema.cpfLogado;

public class PaginaLogin<teste> implements ActionListener {

    //GUI
    JFrame frame = new JFrame();
    JButton botaoLogin = new JButton("Entrar");
    JTextField caixaCpf = new JTextField();
    JPasswordField caixaSenha = new JPasswordField();
    JLabel labelCpf = new JLabel("CPF:");
    JLabel labelSenha = new JLabel("Senha:");
    ImageIcon logo = new ImageIcon();
    JPanel painelLogin = new JPanel();
    JPanel painelLogo  = new JPanel();
    ImageIcon logoSerratec = new ImageIcon("src\\gui\\logoSerratec.png");
    JLabel labelLogo = new JLabel();






    HashMap<String, String> login = new HashMap<String, String>();
    HashMap<String, String> cracha = new HashMap<String, String>();
    HashMap<String, String> tipoUsuario = new HashMap<String, String>();
    HashMap<String, String> conta = new HashMap<String, String>();

    //Constructor GUI
    public PaginaLogin(HashMap<String, String> loginOriginal, HashMap<String, String> crachaOriginal,
                       HashMap<String, String> tipoUsuarioOriginal, HashMap<String, String> contaOriginal)
    {
        //Copia as informações das hashs originais
        login = loginOriginal;
        cracha = crachaOriginal;
        tipoUsuario = tipoUsuarioOriginal;
        conta = contaOriginal;


        //Configs da página
        labelLogo.setIcon(logoSerratec);
        painelLogo.add(labelLogo);
        painelLogin.setLayout(null);
        painelLogin.add(caixaCpf);
        painelLogin.add(caixaSenha);
        painelLogin.add(botaoLogin);
        painelLogo.setLayout(null);


        labelLogo.setBounds(0,0,250,450);
        painelLogin.add(labelCpf);
        painelLogin.add(labelSenha);
        painelLogin.setBounds(220, 0, 223, 385);
        painelLogo.setBounds(0, 0, 210, 420);
        painelLogo.setBackground(Color.cyan);
        frame.add(painelLogin);
        frame.add(painelLogo);

        labelCpf.setBounds(10, 75, 75, 25); //Posição da label CPF
        labelSenha.setBounds(10, 125, 75, 25); //Posição label Senha
        caixaCpf.setBounds(10, 100, 150, 25); //Posição caixa CPF
        caixaSenha.setBounds(10, 150, 150, 25); //Posição caixa Senha
        botaoLogin.setBounds(10, 200, 100, 25); //Posição botão de login
        botaoLogin.setFocusPainted(false); //Remove frame de seleção de cima do botão login
        botaoLogin.addActionListener(this); //Lê clique bo botão login








        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fecha a janela
        frame.setSize(420, 420); //Tamanho da janela
        frame.setTitle("Login - Banco Serratec"); //Titulo da janela
        frame.setResizable(false); //Impede mudança no tamanho da janela
        frame.setLayout(null);
        frame.setVisible(true); //Visibilidade
        frame.setIconImage(logo.getImage()); //Define o ícone da página
        frame.setLocationRelativeTo(null); //Centraliza tela
    }

    //Método de ações
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoLogin) { //Ação que acontecerá após clique no botão login
            String cpf = caixaCpf.getText(); //Lê CPF
            cpfLogado = cpf;
            String senha = String.valueOf(caixaSenha.getPassword()); //Lê senha


            if (login.containsKey(cpf)) { //Se houver um hash com o CPF lido associado
                if (login.get(cpf).equals(senha)) { //Se lermos o campo CPF e a senha for igual a vinculada no hash
                    frame.dispose();
                    if (tipoUsuario.get(cpf).equals("Cliente")) { //Se o usuário for um cliente


                        if (conta.get(cpf).equals("Corrente")) { //Se a conta do usuário for do tipo CORRENTE
                            PaginaCorrente paginaCorrente = new PaginaCorrente(Conta.getNumConta()); //Chama a pagina da conta corrente
                        }
                        else if (conta.get(cpf).equals("Poupanca")) { //Se a conta do usuário for do tipo POUPANÇA
                            PaginaPoupanca paginaPoupanca = new PaginaPoupanca(Conta.getNumConta()); //Chama a pagina da conta poupança
                        }



                    }
                    else if (tipoUsuario.get(cpf).equals("Funcionario")) { //Se o usuário for um funcionário
                        TipoAcesso tipoAcesso = new TipoAcesso(Funcionario.getCracha(), Conta.getConta()); //Chama a página tipo de acesso
                    }
                }
                else { //Se a senha estiver incorreta
                    SenhaIncorreta senhaIncorreta = new SenhaIncorreta(); //Chama a pagina SENHA INCORRETA
                }
            }
            else { //Se NÃO houver um hash com o CPF lido associado
                UsuarioInvalido usuarioInvalido = new UsuarioInvalido(); //Chama a pagina USUÁRIO INVALIDO
            }
        }
    }
}

