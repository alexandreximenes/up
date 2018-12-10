package com.example.ti.loja.Produto;

public class Produto {

    private Integer id;
    private String foto;
    private String titulo;
    private String descricao;
    private Integer quantidade;
    private Double preco;
    private String tamanho;
    private String marca;

    public Produto() {
    }


    public Produto(String foto, String titulo, String descricao, Integer quantidade, Double preco, String tamanho, String marca) {
        this.id = id;
        this.foto = foto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.tamanho = tamanho;
        this.marca = marca;

    }
    public Produto(Integer id, String foto, String titulo, String descricao, Integer quantidade, Double preco, String tamanho, String marca) {
        this(foto, titulo, descricao, quantidade, preco, tamanho, marca);
        this.id = id;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
