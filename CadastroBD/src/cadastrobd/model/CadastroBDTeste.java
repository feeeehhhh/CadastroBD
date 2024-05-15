package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroBDTeste {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ConectorBD conectorBD = ConectorBD.getInstance();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("-----------------------------------------------");
            System.out.println("Selecione uma opçao:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Sair");
            System.out.println("-----------------------------------------------");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    incluir(conectorBD, scanner);
                    break;
                case 2:
                    alterar(conectorBD, scanner);
                    break;
                case 3:
                    excluir(conectorBD, scanner);
                    break;
                case 4:
                    exibirPeloId(conectorBD, scanner);
                    break;
                case 5:
                    exibirTodos(conectorBD, scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opçao invalida!");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }

    private static void incluir(ConectorBD conectorBD, Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Escolha o tipo: 1 - Fisica, 2 - Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
            PessoaFisica pessoaFisica = new PessoaFisica();

            System.out.println("Digite o nome:");
            pessoaFisica.nome
                    = scanner.nextLine();
            System.out.println("Digite o logradouro:");
            pessoaFisica.logradouro = scanner.nextLine();
            System.out.println("Digite a cidade:");
            pessoaFisica.cidade= scanner.nextLine();
            System.out.println("Digite o Estado:");
            pessoaFisica.estado = scanner.nextLine();
            System.out.println("Digite o CPF:");
            pessoaFisica.cpf = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Digite o email:");
            pessoaFisica.email = scanner.nextLine();
            System.out.println("Digite o telefone:");
            pessoaFisica.telefone = scanner.nextLong();
            System.out.println("Digite o ID:");
            pessoaFisica.id = scanner.nextInt();
            pessoaFisicaDAO.incluir(pessoaFisica);
        } else if (tipo == 2) {
            SequenceManager sequenceManager = new SequenceManager(conectorBD);
            Connection connection = conectorBD.getConnection();
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection, sequenceManager);
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            System.out.println("Digite o nome:");
            pessoaJuridica.nome = scanner.nextLine();
            System.out.println("Digite o logradouro:");
            pessoaJuridica.logradouro = scanner.nextLine();
            System.out.println("Digite a cidade:");

            pessoaJuridica.cidade = scanner.nextLine();
            System.out.println("Digite o Estado:");
            pessoaJuridica.estado = scanner.nextLine();
            System.out.println("Digite o CNPJ:");
            pessoaJuridica.cnpj = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Digite o email:");
            pessoaJuridica.email = scanner.nextLine();
            System.out.println("Digite o telefone:");
            pessoaJuridica.telefone = scanner.nextInt();
            System.out.println("Digite o ID:");
            pessoaJuridica.id = scanner.nextInt();
            pessoaJuridicaDAO.incluir(pessoaJuridica);
            connection.close();
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private static void alterar(ConectorBD conectorBD, Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Escolha o tipo: 1 - Fisica, 2 - Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
            System.out.println("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);
            if (pessoaFisica == null) {
                System.out.println("Pessoa Física nao encontrada!");
                return;
            }
            System.out.println("Nome atual: " + pessoaFisica.nome);
            System.out.println("Digite o novo nome:");
            pessoaFisica.nome = scanner.nextLine();
            System.out.println("CPF atual: " + pessoaFisica.cpf);
            pessoaFisica.cpf = scanner.nextLong();
            System.out.println("Telefone atual: " + pessoaFisica.telefone);
            pessoaFisica.telefone = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Email atual: " + pessoaFisica.email);
            pessoaFisica.email = scanner.nextLine();
            System.out.println("Endereço atual: " + pessoaFisica.logradouro + ", " + pessoaFisica.cidade + ", " + pessoaFisica.estado);
            System.out.println("Digite o novo logradouro:");
            pessoaFisica.logradouro = scanner.nextLine();
            System.out.println("Digite uma nova cidade:");
            pessoaFisica.cidade = scanner.nextLine();
            System.out.println("Digite um novo estado:");
            pessoaFisica.estado = scanner.nextLine();
            pessoaFisicaDAO.alterar(pessoaFisica);
        } else if (tipo == 2) {
            SequenceManager sequenceManager = new SequenceManager(conectorBD);
            Connection connection = conectorBD.getConnection();
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection, sequenceManager);
            System.out.println("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);
            if (pessoaJuridica == null) {
                System.out.println("Pessoa Jurídica não encontrada!");
                return;
            }
            System.out.println("Nome atual: " + pessoaJuridica.nome);
            System.out.println("Digite o novo nome:");
            pessoaJuridica.nome = scanner.nextLine();
            System.out.println("CNPJ atual: " + pessoaJuridica.cnpj);
            pessoaJuridica.cnpj = scanner.nextLong();
            System.out.println("Telefone atual: " + pessoaJuridica.telefone);
            pessoaJuridica.telefone = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Email atual: " + pessoaJuridica.email);
            pessoaJuridica.email = scanner.nextLine();
            System.out.println("Endereço atual: " + pessoaJuridica.logradouro + ", " + pessoaJuridica.cidade + ", " + pessoaJuridica.estado);
            System.out.println("Digite o novo logradouro:");
            pessoaJuridica.logradouro = scanner.nextLine();
            System.out.println("Digite uma nova cidade:");
            pessoaJuridica.cidade = scanner.nextLine();
            System.out.println("Digite um novo estado:");
            pessoaJuridica.estado = scanner.nextLine();
            pessoaJuridicaDAO.alterar(pessoaJuridica);
            connection.close();
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private static void excluir(ConectorBD conectorBD, Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Escolha o tipo: 1 - Fisica, 2 - Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
            System.out.println("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            pessoaFisicaDAO.excluir(id);
        } else if (tipo == 2) {
            SequenceManager sequenceManager = new SequenceManager(conectorBD);
            Connection connection = conectorBD.getConnection();
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection, sequenceManager);
            System.out.println("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            pessoaJuridicaDAO.excluir(id);
            connection.close();
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void exibirPeloId(ConectorBD conectorBD, Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Escolha o tipo: 1 - Fisica, 2 - Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
            System.out.println("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);
            if (pessoaFisica != null) {
                System.out.println("ID: " + pessoaFisica.id);
                System.out.println("Nome: " + pessoaFisica.nome);
                System.out.println("CPF: " + pessoaFisica.cpf);
                System.out.println("Endereço: " + pessoaFisica.logradouro + ", " + pessoaFisica.cidade + ", " + pessoaFisica.estado);

            } else {
                System.out.println("Pessoa Fisica nao encontrada!");
            }
        } else if (tipo == 2) {
            SequenceManager sequenceManager = new SequenceManager(conectorBD);
            Connection connection = conectorBD.getConnection();
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection, sequenceManager);
            System.out.println("Digite o ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);
            if (pessoaJuridica != null) {
                System.out.println("ID: " + pessoaJuridica.id);
                System.out.println("Nome: " + pessoaJuridica.nome);
                System.out.println("CNPJ: " + pessoaJuridica.cnpj);
                System.out.println("Endereço: " + pessoaJuridica.logradouro + ", " + pessoaJuridica.cidade + ", " + pessoaJuridica.estado);

            } else {
                System.out.println("Pessoa Juridica nao encontrada!");
            }
            connection.close();
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private static void exibirTodos(ConectorBD conectorBD, Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Escolha o tipo: 1 - Fisica, 2 - Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
            ArrayList<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.readAll();
            for (PessoaFisica pf : pessoasFisicas) {
                System.out.println("ID: " + pf.id);
                System.out.println("Nome: " + pf.nome);
                System.out.println("CPF: " + pf.cpf);
                System.out.println("Endereço: " + pf.logradouro + ", " + pf.cidade + ", " + pf.estado);

            }
        } else if (tipo == 2) {
            SequenceManager sequenceManager = new SequenceManager(conectorBD);
            Connection connection = conectorBD.getConnection();
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection, sequenceManager);
            ArrayList<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.readAll();
            for (PessoaJuridica pj : pessoasJuridicas) {
                System.out.println("ID: " + pj.id);
                System.out.println("Nome: " + pj.nome);
                System.out.println("CNPJ: " + pj.cnpj);
                System.out.println("Endereço: " + pj.logradouro + ", " + pj.cidade + ", " + pj.estado);
            }
            connection.close();
        } else {
            System.out.println("Tipo invalido!");
        }
    }
}
