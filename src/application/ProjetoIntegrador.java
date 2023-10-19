package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.dao.impl.produtorDao;
import model.entities.Produtor;
import java.util.Scanner;

import static model.dao.DaoFactory.createDepartmentDao;

public class ProjetoIntegrador {

    private static List<Produtor> list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        produtorDao produtorDao = DaoFactory.createProdutorDao();

        System.out.println("=== TEST 1: produtor findById =====");
        Produtor findProdutor = produtorDao.findById(2);
        System.out.println(findProdutor);


        // Solicite ao usuário que digite um nome
        System.out.print("Digite um nome: ");
        // Leia o nome fornecido pelo usuário
        String nome = sc.nextLine();
        // Feche o Scanner

        // Exiba o nome armazenado no variavel e o ID cadastrado no Banco de dados
        System.out.println("Nome digitado: " + nome);
        System.out.println("\n=== TEST 4: produtor insert =====");


        System.out.println("\n=== TEST 3: produtor findAll =====");
        list = produtorDao.findAll();
        for (Produtor obj : list) {
            System.out.println(obj);
        }


        Produtor newProdutor = new Produtor(6, nome);
        produtorDao.insert(newProdutor);
        System.out.println("Inserted! New id = " + newProdutor.getIDprodutor());


//        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0 );
//        sellerDao.insert(newSeller);
//        System.out.println("Inserted! New id = " + newSeller.getId());


//        System.out.println("\n=== TEST 2: seller findByDepartment =====");
//        Department department = new Department(2, null);
//        List<Seller> list = sellerDao.findByDepartment(department);
//        for (Seller obj : list) {
//            System.out.println(obj);
//        }
//
//        System.out.println("\n=== TEST 3: seller findAll =====");
//        list = sellerDao.findAll();
//        for (Seller obj : list) {
//            System.out.println(obj);
//        }
//
//        System.out.println("\n=== TEST 4: seller insert =====");
//        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
//        sellerDao.insert(newSeller);
//        System.out.println("Inserted! New id = " + newSeller.getId());
//
//        System.out.println("\n=== TEST 5: seller update =====");
//        seller = sellerDao.findById(1);
//        seller.setName("Martha Waine");
//        sellerDao.update(seller);
//        System.out.println("Update completed");
//
//        System.out.println("\n=== TEST 6: seller delete =====");
//        System.out.println("Enter id for delete test: ");
//        int id = sc.nextInt();
//        sellerDao.deleteById(id);
//        System.out.println("Delete completed");

        sc.close();
    }
}
