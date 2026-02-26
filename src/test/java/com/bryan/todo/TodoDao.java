package com.bryan.todo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TodoDao {

    public int addTask(String task) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            TodoItem item = new TodoItem(task);
            session.persist(item);

            tx.commit();
            return item.getId(); // ID assigned by DB
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Add failed: " + e.getMessage());
            return -1;
        }
    }

    public boolean removeTask(int id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            TodoItem item = session.get(TodoItem.class, id);
            if (item == null) return false;

            session.remove(item);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Remove failed: " + e.getMessage());
            return false;
        }
    }

    public List<TodoItem> getAllTasks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TodoItem order by id", TodoItem.class).list();
        } catch (Exception e) {
            System.out.println("Query failed: " + e.getMessage());
            return List.of();
        }
    }
}
