package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import com.google.gson.Gson;

import java.io.*;

public class TaskIO {

    private static File file = new File("src" + File.separator
            + "main" + File.separator
            + "resources" + File.separator
            + "data.txt");


    public static void serialization(AbstractTaskList tasks) {
        writeBinary(tasks, file);
    }

    public static void deSerialization(AbstractTaskList tasks) {
        readBinary(tasks, file);
    }

    //********************

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(tasks);
        objOut.flush();
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = new ObjectInputStream(in);
        AbstractTaskList list = (AbstractTaskList) objIn.readObject();
        for(Task task: list) {
            tasks.add(task);
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            AbstractTaskList list = (AbstractTaskList) in.readObject();
            for(Task task: list) {
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Список задач пуст!");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    //********************

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        out.write(line);
        out.flush();
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
        for(Task task: list) {
            tasks.add(task);
        }
    }

    public static void writeText(AbstractTaskList tasks) {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(line);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
            for (Task task : list) {
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Список задач пуст!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
