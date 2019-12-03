package ua.edu.sumdu.j2se.yermolenko.tasks;

import com.google.gson.Gson;

import java.io.*;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(tasks);
        objOut.flush();
        objOut.close();
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = new ObjectInputStream(in);
        AbstractTaskList list = (AbstractTaskList) objIn.readObject();
        for(Task task: list) {
            tasks.add(task);
        }
        in.close();
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(tasks);
        out.flush();
        out.close();
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        AbstractTaskList list = (AbstractTaskList) in.readObject();
        for(Task task: list) {
            tasks.add(task);
        }
    }

    //********************

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        out.write(line);
        out.flush();
        out.close();
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
        for(Task task: list) {
            tasks.add(task);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(line);
        fileWriter.flush();
        fileWriter.close();
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
        for(Task task: list) {
            tasks.add(task);
        }
    }
}
