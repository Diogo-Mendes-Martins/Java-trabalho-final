package cliente;

import principal.Usuario;

public class Cliente extends Usuario {
    private int numConta;
    public Cliente(String nome, String sobrenome, String cpf, String usuario,String senha, int numConta) {
        super(nome, sobrenome, cpf, usuario, senha);
        this.numConta = numConta;
    }
    public static void CriarCliente(Cliente cliente){
        Usuario.login.put(cliente.getCpf(), cliente.getSenha());
        Usuario.tipoUsuario.put(cliente.getCpf(), cliente.getUsuario());
    }





}
