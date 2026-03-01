package com.bryan.todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoDaoTest {

    @Test
    void testAddTask() {
        TodoDao dao = new TodoDao();

        int id = dao.addTask("Test Task");

        assertTrue(id > 0, "Task ID should be greater than 0");
    }

    @Test
    void testRemoveTask() {
        TodoDao dao = new TodoDao();

        int id = dao.addTask("Delete Me");
        boolean removed = dao.removeTask(id);

        assertTrue(removed, "Task should be removed successfully");
    }
}
