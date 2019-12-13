package ua.edu.sumdu.j2se.yermolenko.tasks.view;

public class TextMenu {
    public static void mainMenu() {
        System.out.println("Главное меню." + "\n"
                + "1.Посмотреть список задач." + "\n"
                + "2.Добавить новую задачу." + "\n"
                + "3.Изменить/удалить задачу." + "\n"
                + "4.Завершение работы." + "\n"
                + "Ваш ввод: ");
    }

    public static void menu1() {
        System.out.println(
                "1.1 Следующие 5 задач." + "\n"
                + "1.2 Календарь на сегодня." + "\n"
                + "1.3 Календарь на неделю." + "\n"
                + "1.4 Все задачи." + "\n"
                + "Ваш ввод: ");
    }

    public static void menu3() {
        System.out.println(
                "3.1 Изменить название." + "\n"
                 + "3.2 Изменить активность." + "\n"
                 + "3.3 Изменить время." + "\n"
                 + "3.4 Удалить задачу." + "\n"
                 + "Ваш ввод: ");
    }

    public static void menuSetTime() {
        System.out.println("Введите дату/время в формате: dd-MM-yyyy HH:mm" + "\n"
                + "Если задача повторяющаяся введите дату/время в формате:"
                + "dataStart$interval$dataEnd" + "\n"
                + "dataStart - дата начала; interval - интервал в секундах; dataEnd - дата окончания." + "\n"
                + "Ваш ввод:");
    }
}
