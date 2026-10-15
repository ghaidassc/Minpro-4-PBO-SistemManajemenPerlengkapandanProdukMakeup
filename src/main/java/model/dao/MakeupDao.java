/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;
import model.entitas.Makeup;

/**
 *
 * @author gedascc
 */
public class MakeupDao {
    private final EntityManager em;

    public MakeupDao(EntityManager em) {
        this.em = em;
    }

    public boolean save(Makeup makeup) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(makeup);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Gagal simpan: " + e.getMessage());
            return false;
        }
    }

    public Makeup findById(String id) {
        return em.find(Makeup.class, id);
    }

    public List<Makeup> findAll() {
        try {
            return em.createQuery("SELECT m FROM Makeup m", Makeup.class).getResultList();
        } catch (Exception e) {
            System.err.println("Gagal membaca data: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean update(Makeup makeup) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(makeup);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Gagal update: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Makeup item = em.find(Makeup.class, id);
            if (item != null) {
                em.remove(item);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Gagal menghapus: " + e.getMessage());
            return false;
        }
    }
}
