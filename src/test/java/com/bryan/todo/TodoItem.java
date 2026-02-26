package com.bryan.todo;

import jakarta.persistence.*;

@Entity
@Table(name = "todo_items")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String task;

    // REQUIRED by Hibernate
    public TodoItem() {}

    public TodoItem(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return id + ") " + task;
    }
}
