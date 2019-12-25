package ua.edu.sumdu.j2se.yermolenko.tasks.view;

public class Print {
    public static void printMenuSetTime() {
        System.out.println("Введите дату/время в формате: dd-MM-yyyy HH:mm" + "\n"
                + "Если вы хотите сделать задачу повторяющейся введите дату/время в формате:"
                + "dataStart$interval$dataEnd" + "\n"
                + "dataStart - дата начала; interval - дни:часы:минуты; dataEnd - дата окончания." + "\n"
                + "Например: 24-08-2020 16:00$01:05:04$26-08-2020 16:00" + "\n"
                + "Ваш ввод:");
    }

    public static void printText(String text) {
        System.out.println(text);
    }

    public static void printErr(String err) {
        System.err.println(err);
    }
}
