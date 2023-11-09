package application;

import model.dao.impl.DaoFactory;
import model.dao.impl.FornecedorDao;
import model.dao.impl.ProdutorDao;
import model.entities.Fornecedor;
import model.entities.Produtor;

import java.util.List;
import java.util.Scanner;

public class ProjetoIntegrador {
    private static List<Produtor> list;
    private static ProdutorDao produtorDao;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutorDao produtorDao = DaoFactory.createProdutorDao();
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
                        System.out.println("Produtor " + obj.getIDprodutor() + ":");
                        System.out.println("Nome: " + obj.getNome());
                        System.out.println("CPF: " + obj.getCpf());
                        System.out.println("Email: " + obj.getEmail());
                        System.out.println("Telefone: " + obj.getTelefone());
                        System.out.println("Telefone 2: " + obj.getTelefone2());
                        System.out.println("CNPJ: " + obj.getCnpj());
                        System.out.println("Razão Social: " + obj.getRazaoSocial());
                        System.out.println("Produção Própria: " + (obj.isProducaoPropria() ? "Sim" : "Não"));
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println("CADASTRO DE PRODUTOR");

                    sc.nextLine(); //

                    System.out.print("Digite o nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite o CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Digite o e-mail: ");
                    String email = sc.nextLine();

                    System.out.print("Digite o telefone: ");
                    String telefone = sc.nextLine();

                    System.out.print("Digite o segundo telefone: ");
                    String telefone2 = sc.nextLine();

                    System.out.print("Digite o CNPJ: ");
                    String cnpj = sc.nextLine();

                    System.out.print("Digite a razão social: ");
                    String razaoSocial = sc.nextLine();

                    System.out.println("A produção é própria? (true/false): ");
                    boolean producaoPropria = sc.nextBoolean();

                    sc.nextLine();

                    System.out.println("Nome digitado: " + nome);
                    System.out.println("CPF digitado: " + cpf);
                    System.out.println("E-mail digitado: " + email);
                    System.out.println("Telefone digitado: " + telefone);
                    System.out.println("Segundo telefone digitado: " + telefone2);
                    System.out.println("CNPJ digitado: " + cnpj);
                    System.out.println("Razão Social digitada: " + razaoSocial);
                    System.out.println("Produção Própria: " + producaoPropria);

                    Produtor newProdutor = new Produtor(0, nome, cpf, email, telefone, telefone2, cnpj, razaoSocial, producaoPropria);
                    produtorDao.insert(newProdutor);
                    System.out.println("Produtor inserido com sucesso!");
                    break;
                case 3:
                    System.out.println("\nDELETAR UM PRODUTOR");
                    System.out.print("\nEscolha um ID para deletar: ");
                    int idDelete = sc.nextInt();
                    produtorDao.deleteById(idDelete);
                    break;
                case 4:
                    System.out.println("ATUALIZAR PRODUTOR");
                    System.out.println("\nEscolha um ID para atualizar");
                    int idUpdate = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer

                    Produtor prod = produtorDao.findById(idUpdate);

                    if (prod != null) {
                        System.out.print("Digite o novo nome: ");
                        prod.setNome(sc.nextLine());

                        System.out.print("Digite o novo CPF: ");
                        prod.setCpf(sc.nextLine());

                        System.out.print("Digite o novo e-mail: ");
                        prod.setEmail(sc.nextLine());

                        System.out.print("Digite o novo telefone: ");
                        prod.setTelefone(sc.nextLine());

                        System.out.print("Digite o novo segundo telefone: ");
                        prod.setTelefone2(sc.nextLine());

                        System.out.print("Digite o novo CNPJ: ");
                        prod.setCnpj(sc.nextLine());

                        System.out.print("Digite a nova razão social: ");
                        prod.setRazaoSocial(sc.nextLine());

                        System.out.print("A produção é própria? (true/false): ");
                        prod.setProducaoPropria(sc.nextBoolean());

                        produtorDao.update(prod);

                        System.out.println("Produtor atualizado com sucesso!");
                    } else {
                        System.out.println("Produtor não encontrado.");
                    }
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

//    private static void buscarProdutorPorID(Scanner sc) {
//        System.out.print("Digite o ID do produtor para buscar: ");
//        int id = sc.nextInt();
//        Produtor findProdutor = produtorDao.findById(id);
//        System.out.println(findProdutor);
//    }
