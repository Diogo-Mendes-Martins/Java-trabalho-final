package conta;

import cliente.Cliente;

public class Corrente extends Conta{
    public Corrente(int numero, int agencia, double saldo, String tipoConta, String titular, String cpfTitular) {
        super(numero, agencia, saldo, tipoConta, titular, cpfTitular);
    }
}
