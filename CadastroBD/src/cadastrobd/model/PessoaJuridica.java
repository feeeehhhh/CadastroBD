
package cadastrobd.model;


public class PessoaJuridica extends Pessoa {
    public Long cnpj;
    
    
    public PessoaJuridica(){}
    
    public PessoaJuridica(String nome, String logradouro, String cidade, int telefone, String email, int id, Long cnpj){
        super(nome,logradouro,cidade,telefone,email,id);
        this.cnpj = cnpj;
    }
    public void metodoExibir(){
        super.metodoExibir();
        System.out.println("CNPJ :"+ cnpj);
    }

    int getId() {
        return super.id;
    }
}
