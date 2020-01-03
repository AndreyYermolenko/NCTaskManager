package ua.edu.sumdu.j2se.yermolenko.tasks.view;

/**
 * Class Print realizes print text for console.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class Print {
    /**
     * Method printMenuSetTime realizes print menu set time for console.
     */
    public static void printMenuSetTime() {
        System.out.println("Введите дату/время в формате: dd-MM-yyyy HH:mm" + "\n"
                + "Если вы хотите сделать задачу повторяющейся введите дату/время в формате:"
                + "dataStart$interval$dataEnd" + "\n"
                + "dataStart - дата начала; interval - дни:часы:минуты; dataEnd - дата окончания." + "\n"
                + "Например: 24-08-2020 16:00$01:05:04$26-08-2020 16:00" + "\n"
                + "Ваш ввод:");
    }

    /**
     * Method printText realizes print text for console.
     *
     * @param text of type String
     */
    public static void printText(String text) {
        System.out.println(text);
    }

    /**
     * Method printErr realizes print error for console.
     *
     * @param err of type String
     */
    public static void printErr(String err) {
        System.err.println(err);
    }
}
