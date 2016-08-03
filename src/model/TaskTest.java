package model;

import java.util.ArrayList;

/**
 * TaskTest class tests the Task class
 *
 * @author Prateek Prasher
 * @author prateekprshr@gmail.com
 */
public class TaskTest
{
    public static void main(String args[])
    {
        Task task = null;


        // Test save and getter setters
        task = new Task("Sample Task one");
        System.out.println("Task id:" + task.getId() + " Description:" + task.getDescription());
        task.save();

        task = new Task();
        task.setId(2);
        task.setDescription("Sample Task two");
        task.save();

        // Test search
        ArrayList<Task> tasks = Task.all();
        for(Task t : tasks)
        {
            System.out.println(t.toString());
        }

        // Test delete
        task = tasks.get(0);
        task.delete();
        /*

        task = Task.find(161);
        System.out.println(task);
        */
    }
}
