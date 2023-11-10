//package application;
//
//import model.dao.impl.UsuarioDao;
//
//import java.util.Scanner;
//
//import static model.dao.impl.DaoFactory.createUsuarioDao;
//
//public class UsuarioTest {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        UsuarioDao usuarioDao = createUsuarioDao();
//
////        System.out.println("=== TEST 1: findById =======");
////        model.dao.UsuarioDao user = usuarioDao.findById(1);
////        System.out.println(user);
////
////        System.out.println("\n=== TEST 2: findAll =======");
////        List<model.dao.UsuarioDao> list = usuarioDao.findAll();
////        for (model.dao.UsuarioDao d : list) {
////            System.out.println(d);
////        }
//
//        System.out.println("\n=== TEST 3: insert =======");
//        model.dao.UsuarioDao newUsuario = new model.dao.UsuarioDao(null, "master@gmail.com", "123456");
//        usuarioDao.insert(newUsuario);
//        System.out.println("Inserted! New id: " + newUsuario.getIDUsuario());
//
////        System.out.println("\n=== TEST 4: update =======");
////        model.dao.UsuarioDao dep2 = usuarioDao.findById(1);
////        dep2.setEmail("master_teste@gmail.com");
////        usuarioDao.update(dep2);
////        System.out.println("Update completed");
////
////        System.out.println("\n=== TEST 5: delete =======");
////        System.out.print("Enter id for delete test: ");
////        int id = sc.nextInt();
////        usuarioDao.deleteById(id);
////        System.out.println("Delete completed");
//
//
//        sc.close();
//    }
//}
