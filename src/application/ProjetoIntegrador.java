package application;

import model.dao.impl.*;
import model.entities.CategoriaFornecedores;
import model.entities.Fornecedor;
import model.entities.Produtor;

import java.util.List;
import java.util.Scanner;

public class ProjetoIntegrador {
    private static List<Produtor> list;
    private static ProdutorDao produtorDao;
    private static FornecedorDao fornecedorDao;
    private static CategoriaFornecedoresDao categoriaFornecedoresDao;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        produtorDao = DaoFactory.createProdutorDao();
        fornecedorDao = DaoFactory.createFornecedorDao();
        categoriaFornecedoresDao = DaoFactory.createCategoriaFornecedoresDao();
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
            System.out.println("9 - Listar Categorias de Fornecedores");
            System.out.println("10 - Cadastrar Categoria de Fornecedor");
            System.out.println("11 - Deletar Categoria de Fornecedor");
            System.out.println("12 - Atualizar Categoria de Fornecedor");
            System.out.println("0 - Sair");
            escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    // Listar Produtores
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
                    // Cadastrar Produtor
                    cadastrarProdutor(sc);
                    break;
                case 3:
                    // Deletar Produtor
                    deletarProdutor(sc);
                    break;
                case 4:
                    // Atualizar Produtor
                    atualizarProdutor(sc);
                    break;
                case 5:
                    // Listar Fornecedores
                    listarFornecedores();
                    break;
                case 6:
                    // Cadastrar Fornecedor
                    cadastrarFornecedor(sc);
                    break;
                case 7:
                    // Deletar Fornecedor
                    deletarFornecedor(sc);
                    break;
                case 8:
                    // Atualizar Fornecedor
                    atualizarFornecedor(sc);
                    break;
                case 9:
                    // Listar Categorias de Fornecedores
                    listarCategoriasFornecedores();
                    break;
                case 10:
                    // Cadastrar Categoria de Fornecedor
                    cadastrarCategoriaFornecedor(sc);
                    break;
                case 11:
                    // Deletar Categoria de Fornecedor
                    deletarCategoriaFornecedor(sc);
                    break;
                case 12:
                    // Atualizar Categoria de Fornecedor
                    atualizarCategoriaFornecedor(sc);
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

    // Métodos auxiliares para Produtores
    private static void cadastrarProdutor(Scanner sc) {
        // Cadastrar Produtor
        System.out.println("CADASTRO DE PRODUTOR");
        // Restante do código de cadastro de Produtor...
    }

    private static void deletarProdutor(Scanner sc) {
        // Deletar Produtor
        System.out.println("\nDELETAR UM PRODUTOR");
        // Restante do código de exclusão de Produtor...
    }

    private static void atualizarProdutor(Scanner sc) {
        // Atualizar Produtor
        System.out.println("ATUALIZAR PRODUTOR");
        // Restante do código de atualização de Produtor...
    }

    // Métodos auxiliares para Fornecedores
    private static void listarFornecedores() {
        // Listar Fornecedores
        List<Fornecedor> listaFornecedores = fornecedorDao.findAll();
        for (Fornecedor fornecedor : listaFornecedores) {
            System.out.println(fornecedor);
        }
    }

    private static void cadastrarFornecedor(Scanner sc) {
        // Cadastrar Fornecedor
        System.out.println("\nCADASTRO DE FORNECEDOR");
        // Restante do código de cadastro de Fornecedor...
    }

    private static void deletarFornecedor(Scanner sc) {
        // Deletar Fornecedor
        System.out.println("\nDELETAR UM FORNECEDOR");
        // Restante do código de exclusão de Fornecedor...
    }

    private static void atualizarFornecedor(Scanner sc) {
        // Atualizar Fornecedor
        System.out.println("ATUALIZAR FORNECEDOR");
        // Restante do código de atualização de Fornecedor...
    }

    // Métodos auxiliares para Categorias de Fornecedores
    private static void listarCategoriasFornecedores() {
        // Listar Categorias de Fornecedores
        List<CategoriaFornecedores> listaCategorias = categoriaFornecedoresDao.findAll();
        for (CategoriaFornecedores categoria : listaCategorias) {
            System.out.println(categoria);
        }
    }

    private static void cadastrarCategoriaFornecedor(Scanner sc) {
        // Cadastrar Categoria de Fornecedor
        System.out.println("\nCADASTRO DE CATEGORIA DE FORNECEDOR");
        // Restante do código de cadastro de Categoria de Fornecedor...
    }

    private static void deletarCategoriaFornecedor(Scanner sc) {
        // Deletar Categoria de Fornecedor
        System.out.println("\nDELETAR UMA CATEGORIA DE FORNECEDOR");
        // Restante do código de exclusão de Categoria de Fornecedor...
    }

    private static void atualizarCategoriaFornecedor(Scanner sc) {
        // Atualizar Categoria de Fornecedor
        System.out.println("ATUALIZAR CATEGORIA DE FORNECEDOR");
        // Restante do código de atualização de Categoria de Fornecedor...
    }
}
