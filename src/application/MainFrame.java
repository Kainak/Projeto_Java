package application;

import model.dao.DaoFactory;
import model.entities.Produtor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import model.dao.impl.produtorDao;
import model.entities.Produtor;


public class MainFrame extends JFrame {
    private JTextField tfNome;
    private JTextField tfSobrenome;
    private JButton btnOK;
    private JButton btnClear;
    private JTextPane lbWelcome;
    private JPanel mainPanel;
    produtorDao produtorDao = DaoFactory.createProdutorDao();

    public MainFrame (){
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String EscolhaProdutor = tfNome.getText();
                String sobrenome = tfSobrenome.getText();
                Produtor findProdutor = produtorDao.findById(Integer.valueOf(EscolhaProdutor));
               // System.out.println(findProdutor);

                lbWelcome.setText("Welcome" + findProdutor + " " + sobrenome);
            }
        });
    }

    public static void main(String[] args) {
        MainFrame myframe = new MainFrame();
    }
}
