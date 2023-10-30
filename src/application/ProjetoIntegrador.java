package application;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.impl.produtorDao;
import model.entities.Produtor;
import java.util.Scanner;

public class ProjetoIntegrador {
    private static List<Produtor> list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        produtorDao produtorDao = DaoFactory.createProdutorDao();


        System.out.println("\nDIGITE O ID DE UM PRODUTOR PARA IMPRIMIR");
        int EscolhaProdutor = sc.nextInt();
        Produtor findProdutor = produtorDao.findById(EscolhaProdutor);
        System.out.println(findProdutor);

        sc.nextLine();//limpa o buffer
        System.out.println("\nCADASTRO DE PRODUTOR");
        System.out.print("\nDigite um nome: ");
        // Leia o nome fornecido pelo usuário
        String nome = sc.nextLine();
        // Exibe o nome armazenado no variavel e o ID cadastrado no Banco de dados
        System.out.println("\nNome digitado: " + nome);
        Produtor newProdutor = new Produtor(0, nome);
        produtorDao.insert(newProdutor);
        System.out.println("Inserted!");

        System.out.println("\nLISTAR TODOS OS PRODUTORES");
        list = produtorDao.findAll();
        for (Produtor obj : list) {
            System.out.println(obj);
        }

        System.out.println("\nDELETAR UM PRODUTOR");
        System.out.print("\nEnter id for delete test: ");
        int idDelete = sc.nextInt();
        produtorDao.deleteById(idDelete);
        System.out.println("Delete completed");

        System.out.println("ATUALIZAR PRODUTOR");
        System.out.println("\nEscolha um ID para atualizar");
        int idUpdate = sc.nextInt();
        sc.nextLine();//limpa o buffer
        System.out.print("\nDigite um nome: ");
        // Leia o nome fornecido pelo usuário

        String atualizarNome = sc.nextLine();//variavel que armazena o nmoe temporareamente
        Produtor prod2 = produtorDao.findById(idUpdate);
        prod2.setNome(atualizarNome); //manda por parametro para a função que atualiza
        produtorDao.update(prod2);
        System.out.println("Update completed");

        sc.close();
    }
}
