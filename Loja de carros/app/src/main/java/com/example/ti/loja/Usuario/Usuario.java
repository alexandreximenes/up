package com.example.ti.loja.Usuario;

public class Usuario {

    private Integer id;
    private String foto;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(Integer id, String foto, String nome, String email, String senha) {
        this(foto, nome, email, senha);
        this.id = id;
    }

    public Usuario(String foto, String nome, String email, String senha) {
        this.foto = foto;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", foto='" + foto + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
