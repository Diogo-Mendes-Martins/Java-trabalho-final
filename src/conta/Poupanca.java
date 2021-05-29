package conta;

import cliente.Cliente;

public class Poupanca extends Conta {
    public Poupanca(int numero, int agencia, double saldo, String tipoConta, String titular, String cpfTitular) {
        super(numero, agencia, saldo, tipoConta, titular, cpfTitular);
    }
}
