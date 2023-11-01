package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.produtorDao;
import model.entities.Produtor;
import model.dao.impl.FornecedorDao; // Importe a classe FornecedorDao
import model.entities.Fornecedor; // Importe a classe Fornecedor

public class ProjetoIntegrador {

    private static List<Produtor> list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        produtorDao produtorDao = DaoFactory.createProdutorDao();
        FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();

        int escolha;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Listar Produtores");
            System.out.println("2 - Cadastrar Produtor");
            System.out.println("3 - Deletar Produtor");
            System.out.println("4 - Atualizar Produtor");
            System.out.println("5 - Listar Fornecedores");
            System.out.println("6 - Cadastrar Fornecedor");
            System.out.println("7 - Deletar Fornecedor");
            System.out.println("8 - Atualizar Fornecedor");
            System.out.println("0 - Sair");

            escolha = sc.nextInt();

            sc.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("\nLISTAR TODOS OS PRODUTORES");
                    list = produtorDao.findAll();
                    for (Produtor obj : list) {
                        System.out.println(obj);
                    }
                    break;
                case 2:
                    System.out.println("\nCADASTRO DE PRODUTOR");
                    System.out.print("\nDigite um nome: ");
                    String nome = sc.nextLine();
                    Produtor newProdutor = new Produtor(0, nome);
                    produtorDao.insert(newProdutor);
                    System.out.println("Inserted!");
                    break;
                case 3:
                    System.out.println("\nDELETAR UM PRODUTOR");
                    System.out.print("\nEscolha um ID para deletar: ");
                    int idDelete = sc.nextInt();
                    produtorDao.deleteById(idDelete);
                    System.out.println("Delete completed");
                    break;
                case 4:
                    System.out.println("ATUALIZAR PRODUTOR");
                    System.out.println("\nEscolha um ID para atualizar");
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\nDigite um nome: ");
                    String atualizarNome = sc.nextLine();
                    Produtor prod2 = produtorDao.findById(idUpdate);
                    prod2.setNome(atualizarNome);
                    produtorDao.update(prod2);
                    System.out.println("Update completed");
                    break;
                case 5:
                    // Listar Fornecedores
                    List<Fornecedor> listaFornecedores = fornecedorDao.findAll();
                    for (Fornecedor fornecedor : listaFornecedores) {
                        System.out.println(fornecedor);
                    }
                    break;
                case 6:
                    System.out.println("\nCADASTRO DE FORNECEDOR");
                    System.out.print("\nDigite um nome: ");
                    String nomeFornecedor = sc.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefoneFornecedor = sc.nextLine();
                    Fornecedor newFornecedor = new Fornecedor(0, nomeFornecedor, telefoneFornecedor);
                    fornecedorDao.insert(newFornecedor);
                    System.out.println("Fornecedor inserido com sucesso!");
                    break;
                case 7:
                    System.out.println("\nDELETAR UM FORNECEDOR");
                    System.out.print("\nEscolha um ID para deletar: ");
                    int idFornecedorDelete = sc.nextInt();
                    fornecedorDao.deleteById(idFornecedorDelete);
                    System.out.println("Fornecedor deletado com sucesso!");
                    break;
                case 8:
                    System.out.println("ATUALIZAR FORNECEDOR");
                    System.out.println("\nEscolha um ID para atualizar");
                    int idFornecedorUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\nDigite um nome: ");
                    String novoNomeFornecedor = sc.nextLine();
                    System.out.print("Digite o novo telefone: ");
                    String novoTelefoneFornecedor = sc.nextLine();
                    Fornecedor fornecedorAtualizado = new Fornecedor(idFornecedorUpdate, novoNomeFornecedor, novoTelefoneFornecedor);
                    fornecedorDao.update(fornecedorAtualizado);
                    System.out.println("Fornecedor atualizado com sucesso!");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }

        } while (escolha != 0);

        sc.close();
    }
}

