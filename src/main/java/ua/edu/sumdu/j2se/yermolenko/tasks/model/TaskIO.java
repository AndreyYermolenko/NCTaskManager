package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Class TaskIO realizes serialize and deserialize the list of tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class TaskIO {
    private final static Logger logger = LogManager.getLogger(TaskIO.class);
    private static File dir = new File("./save");
    static {
        dir.mkdir();
    }
    private static File file = new File(dir,"data.txt");

    /**
     * The method serializes the list of tasks.
     * @param tasks of type AbstractTaskList.
     */
    public static void serialization(AbstractTaskList tasks) {
        writeBinary(tasks, file);
    }

    /**
     * The method deserializes the list of tasks.
     * @param tasks of type AbstractTaskList.
     */
    public static void deSerialization(AbstractTaskList tasks) {
        readBinary(tasks, file);
    }

    //********************

    private static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(tasks);
        objOut.flush();
    }

    private static void read(AbstractTaskList tasks, InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = new ObjectInputStream(in);
        AbstractTaskList list = (AbstractTaskList) objIn.readObject();
        for(Task task: list) {
            tasks.add(task);
        }
    }

    private static void writeBinary(AbstractTaskList tasks, File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
            out.flush();
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private static void readBinary(AbstractTaskList tasks, File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            AbstractTaskList list = (AbstractTaskList) in.readObject();
            for(Task task: list) {
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Список задач пуст!");
        } catch (ClassNotFoundException | IOException e) {
            logger.error(e);
        }
    }

    //********************

    private static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        out.write(line);
        out.flush();
    }

    private static void read(AbstractTaskList tasks, Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
        for(Task task: list) {
            tasks.add(task);
        }
    }

    private static void writeText(AbstractTaskList tasks) {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(line);
            fileWriter.flush();
        } catch (IOException e) {
            logger.error(e);        }
    }

    private static void readText(AbstractTaskList tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
            for (Task task : list) {
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Список задач пуст!");
        } catch (IOException e) {
            logger.error(e);        }
    }
}
