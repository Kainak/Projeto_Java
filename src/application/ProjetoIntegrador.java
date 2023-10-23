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

import static model.dao.DaoFactory.createDepartmentDao;

public class ProjetoIntegrador {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        produtorDao produtorDao = DaoFactory.createProdutorDao();

        System.out.println("\n=== TEST 6: seller insert =====");
        Produtor newProdutor = new Produtor(5, "Juarez", "133.466.789-00","juarez@example.com", "123456789","987654321","12345678901234","Razao Social Ltda.", true);

        produtorDao.insert(newProdutor);
        System.out.println("Inserted! New id = " + newProdutor.getIDprodutor());



//        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0 );
//        sellerDao.insert(newSeller);
//        System.out.println("Inserted! New id = " + newSeller.getId());




//        System.out.println("=== TEST 1: seller findById =====");
//        Seller seller = sellerDao.findById(3);
//        System.out.println(seller);
//
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
