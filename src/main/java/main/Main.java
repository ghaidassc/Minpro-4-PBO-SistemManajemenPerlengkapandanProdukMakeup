/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.MakeupController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.dao.MakeupDao;
import view.MakeupView;

/**
 *
 * @author gedascc
 */
public class Main {
    public static void main(String[] args) {
        
        System.setProperty("org.jboss.logging.provider", "jdk");
        java.util.logging.LogManager.getLogManager().reset();
        java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.OFF);
        
        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeupPU");EntityManager em = emf.createEntityManager()) {
            
            MakeupDao makeupDao = new MakeupDao(em);
            MakeupView view = new MakeupView();
            MakeupController controller = new MakeupController(view, makeupDao);
            
            controller.mulaiProgram();


        }
    }
}
