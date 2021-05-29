package principal;

import javafx.beans.binding.MapExpression;

import java.util.HashMap;

public abstract class Usuario {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private String tipo;

    //Hashmap CPF - Senha
    public static HashMap<String, String> login = new HashMap<String,String>();
    //Hashmap CPF - Usuario
    public static HashMap<String, String> tipoUsuario = new HashMap<String,String>();

    //Constructors
    public Usuario(String nome, String sobrenome, String cpf, String tipo, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.senha = senha;
    }
    public Usuario() {
    }
    //Getters e Setters
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public static HashMap<String, String> getLogin() {
        return login;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getUsuario() {
        return tipo;
    }
    public void setUsuario(String usuario) {
        this.tipo = tipo;
    }
    public static HashMap<String, String> getTipoUsuario() {
        return tipoUsuario;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

