/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author felip
 */
public class Pessoa {

    public String nome;
    public String logradouro;
    public String cidade;
    public String estado;
    public long telefone;
    public String email;
    public int id;

    public Pessoa() {
    }

    public Pessoa(String nome, String logradouro, String cidade, long telefone, String email, int id) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
        this.id = id;
        this.estado = estado;

    }

    public void metodoExibir() {
        System.out.println("ID :" + id);
        System.out.println("Nome :" + nome);
        System.out.println("Logradouro :" + logradouro + ", " + cidade + ", " + estado);

        System.out.println("Cidade :" + cidade);
        System.out.println("Estado :" + estado);
        System.out.println("Telefone :" + telefone);
        System.out.println("Email :" + email);

    }

}
