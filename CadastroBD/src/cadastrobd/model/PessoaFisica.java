
package cadastrobd.model;


public class PessoaFisica extends Pessoa{
    public long cpf;
    
    public PessoaFisica(){}
    
    public PessoaFisica(String nome, String logradouro, String cidade, long telefone, String email, int id, long cpf){
        super(nome,logradouro,cidade,telefone,email,id);
        this.cpf = cpf;
    }
    
    @Override
    public void metodoExibir(){
        super.metodoExibir();
        System.out.println("CPF :"+ cpf);
        
    }

    int getId() {
        return super.id;
    }

   
}
