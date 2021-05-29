package conta;

import cliente.Cliente;
import funcionario.Funcionario;
import principal.Sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public abstract class Conta {
    private int numero;
    private int agencia;
    private double saldo;
    private String cpfTitular;
    private String titular;
    private String tipoConta;

    //HashMap CPF - Tipo de conta
    public static HashMap<String, String> conta = new HashMap<String,String>();
    //Hashmap CPF - Conta
    public static HashMap<String, Conta> numConta = new HashMap<String, Conta>();
    //Hashmap CPF - ArrayList (transações)
    public static HashMap<String, ArrayList<String>> transacoes = new HashMap<>();
    //Hashmap Nome - CPF
    public static HashMap<String, String> nomeCpf = new HashMap<String, String>();
    //Hashmap Nome - Agencia
    public static HashMap<String, Integer> nomeAgencia = new HashMap<String, Integer>();

    //Métodos
    //Criar conta
    static public void CriaConta(Conta conta){
        Conta.conta.put(conta.getCpfTitular(), conta.getTipoConta());
        Conta.numConta.put(conta.getCpfTitular(), conta);
        ArrayList<String> transacoesConta = new ArrayList<String>();
        Conta.transacoes.put(conta.getCpfTitular(), transacoesConta);
        Sistema.numContas++;
    }
    //Saque
    static public int Saque(double valor, Conta conta) throws IOException {
        if(valor > 0) {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            if (valor <= conta.getSaldo()) {
                String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                //Cálculo transação
                final double taxa = 0.10;
                conta.setSaldo(conta.getSaldo() - (valor + taxa));
                String totalFormatado = formatter.format(valor + taxa);
                //Adição ao extrato
                ArrayList<String> temporaria = new ArrayList<String>();
                temporaria = transacoes.get(Sistema.cpfLogado);
                temporaria.add("\nSaque" + "\nValor: " + formatter.format(valor) + "\nTaxa de serviço: " + formatter.format(taxa) + "\nTotal debitado: " + totalFormatado + "\n" + "Data: " + data + "\n");
                transacoes.put(Sistema.cpfLogado, temporaria);
                //Geração de comprovante
                String timeStampData = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
                try {
                    FileWriter comprovante = new FileWriter("Saque" + timeStampData);
                    comprovante.write("\nSaque" + "\nCpf do titular: " + Sistema.cpfLogado + "\nValor: " + formatter.format(valor) + "\nTaxa de serviço: " + formatter.format(taxa) + "\nTotal debitado: " + totalFormatado+ "\n" + "Data: " + data + "\n");
                    comprovante.close();
                }
                catch (IOException | NumberFormatException ex){
                    System.out.println("Erro");
                }
                return 1;
            } else {
                return 2;
            }
        }
        else {return 3;}
    }
    //Depósito
    static public boolean Deposito(double valor, Conta conta){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        if (valor > 0) {
            String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            final double taxa = 0.10;
            //Cálculo transação
            conta.setSaldo(conta.getSaldo() + valor);
            String totalFormatado = formatter.format(valor - taxa);
            //Adição ao extrato
            ArrayList<String> temporaria = new ArrayList<String>();
            temporaria = transacoes.get(Sistema.cpfLogado);
            temporaria.add("\nDepósito" + "\nValor: " + formatter.format(valor) + "\nTaxa de serviço: " + formatter.format(taxa) + "\nTotal depositado: " + (totalFormatado) + "\n" + "Data: " + data + "\n");
            transacoes.put(Sistema.cpfLogado, temporaria);
            //Geração de comprovante
            String timeStampData = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
            try {
                FileWriter comprovante = new FileWriter("Deposito" + timeStampData);
                comprovante.write("\nDepósito" + "\nCpf do titular: " + Sistema.cpfLogado  + "\nValor: " + formatter.format(valor) + "\nTaxa de serviço: " + formatter.format(taxa) + "\nTotal depositado: " + (totalFormatado) + "\n" + "Data: " + data + "\n");
                comprovante.close();
            }
            catch (IOException | NumberFormatException ex){
                System.out.println("Erro");
            }
            return true;
        }
        else{return false;}
    }
    //Transferência
    static public int Transferencia(double valor, Conta conta1, Conta conta2){
        if (valor > 0) {
            final double taxa = 0.20;
            if (conta1.getSaldo() >= valor) {
                String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                //Cálculo transação
                conta1.setSaldo(conta1.getSaldo() - valor);
                conta2.setSaldo(conta2.getSaldo() + valor);
                //Adição ao extrato titular
                ArrayList<String> temporaria = new ArrayList<String>();
                temporaria = transacoes.get(Sistema.cpfLogado);
                temporaria.add("\nTransferência" + "\nCPF do titular: " + Sistema.cpfLogado + "\nValor: " + formatter.format(valor) + "\nTaxa de serviço: " + formatter.format(taxa) + "\nDestinatário: " + conta2.getCpfTitular() + "\nTotal debitado: " + formatter.format(valor + taxa) + "\n"+ "Data: " + data + "\n");
                transacoes.put(Sistema.cpfLogado, temporaria);
                //Adição ao extrato destinatário
                temporaria = transacoes.get(conta2.getCpfTitular());
                temporaria.add("\nTransferência" + "\nCPF do titular: " + conta2.getCpfTitular() + "\nValor: " + formatter.format(valor) + "\nRemetente: " + Sistema.cpfLogado + "\n"+ "Data: " + data + "\n");
                transacoes.put(conta2.getCpfTitular(), temporaria);
                //Geração de comprovante
                String timeStampData = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
                try {
                    FileWriter comprovante = new FileWriter("Transferencia" + timeStampData);
                    comprovante.write("\nTransferência" + "\nCPF do titular: " + Sistema.cpfLogado  + "\nValor: " + formatter.format(valor) + "\nTaxa de serviço: " + formatter.format(taxa) + "\nTotal debitado: " + formatter.format(valor + taxa) + "\n" + "Data: " + data + "\n");
                    comprovante.close();
                }
                catch (IOException | NumberFormatException ex){
                    System.out.println("Erro");
                }
                return 1;
            } else {
                return 2;
            }
        }
        else{return 3;}
    }
    //Simular rendimento
    static public double Rendimento(double valor, int dias){
        if(valor > 0 && dias > 0){
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            //Parâmetros de rendimento: 2% ao ano
            double rendimento;
            final double porcentRendimento = (0.02/365);
            rendimento = (valor * porcentRendimento) * dias;
            //Geração de comprovante
            String timeStampData = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
            try {
                FileWriter comprovante = new FileWriter("Rendimento" + timeStampData);
                comprovante.write("\nSimulação de rendimento" + "\nCpf do titular: " + Sistema.cpfLogado + "\nValor: " + formatter.format(valor) + "\nQuantidade de dias: " + dias + "\nPorcentagem de rendimento: 2% ao ano" + "\nTotal de rendimento no periodo informado: " + formatter.format(rendimento)+ "\n" + "Data: " + data + "\n");
                comprovante.close();
            }
            catch (IOException | NumberFormatException ex){
                System.out.println("Erro");
            }
















            return rendimento;
        }
        else {
            return 0;
        }
    }
    //Contratar seguro
    static public int Seguro(double valor, Conta conta){
        if(valor > 0) {
            if (valor <= conta.getSaldo()) {
                String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                conta.setSaldo(conta.getSaldo()-(0.20*valor));
                //Adição ao extrato
                ArrayList<String> temporaria = new ArrayList<String>();
                temporaria = transacoes.get(Sistema.cpfLogado);
                temporaria.add("\nContratação seguro de vida" + "\nCPF do titular: " + Sistema.cpfLogado + "\nValor assegurado: " + formatter.format(valor)+ "\nTributo do seguro: " + formatter.format(0.20*valor) + "\nData: "+ data + "\n");
                transacoes.put(Sistema.cpfLogado, temporaria);
                //Geração de comprovante
                String timeStampData = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
                try {
                    FileWriter comprovante = new FileWriter("Transferencia" + timeStampData);
                    comprovante.write("\nContratação seguro de vida" + "\nCPF do titular: " + Sistema.cpfLogado  + "\nValor assegurado: " + formatter.format(valor) + "\nTributo do seguro: " + formatter.format(0.20*valor) + "\nData: " + data + "\n");
                    comprovante.close();
                }
                catch (IOException | NumberFormatException ex){
                    System.out.println("Erro");
                }
                return 1;
            } else {
                return 2;
            }
        }
        else {return 3;}
    }














    //Constructors
    public Conta(int numero, int agencia, double saldo, String tipoConta, String titular, String cpfTitular) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.titular = titular;
        this.cpfTitular = cpfTitular;
        this.tipoConta = tipoConta;
    }

















    //Getters e Setters
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getAgencia() {
        return agencia;
    }
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public String getCpfTitular() {
        return cpfTitular;
    }
    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }
    public String getTipoConta() {
        return tipoConta;
    }
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
    public static HashMap<String, String> getConta() {
        return conta;
    }
    public static HashMap<String, Conta> getNumConta() {
        return numConta;
    }
    public String getTitular() {
        return titular;
    }
    public void setCliente(String titular) {
        this.titular = titular;
    }
}
