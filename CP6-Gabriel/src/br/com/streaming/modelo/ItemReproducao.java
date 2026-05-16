package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

public abstract class ItemReproducao implements Reproduzivel {

    protected String nome;

    public ItemReproducao(String nome) {
        setNome(nome);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome}

}