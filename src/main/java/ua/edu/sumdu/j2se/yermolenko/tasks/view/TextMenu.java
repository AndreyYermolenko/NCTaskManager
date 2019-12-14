package ua.edu.sumdu.j2se.yermolenko.tasks.view;

public class TextMenu {
    public static void printMainMenu() {
        System.out.println("Главное меню." + "\n"
                + "1.Посмотреть список задач." + "\n"
                + "2.Добавить новую задачу." + "\n"
                + "3.Изменить/удалить задачу." + "\n"
                + "4.Завершение работы." + "\n"
                + "Ваш ввод: ");
    }

    public static void printMenuViewTasks() {
        System.out.println(
                "1.1 Следующие 5 задач." + "\n"
                + "1.2 Календарь на сегодня." + "\n"
                + "1.3 Календарь на неделю." + "\n"
                + "1.4 Все задачи." + "\n"
                        + "Ваш ввод: ");
    }

    public static void printMenuChangeDelete() {
        System.out.println(
                "3.1 Изменить название." + "\n"
                 + "3.2 Изменить активность." + "\n"
                 + "3.3 Изменить время." + "\n"
                 + "3.4 Удалить задачу." + "\n"
                 + "Для сохранения изменений и выхода в главное меню нажмите Enter." + "\n"
                 + "Ваш ввод: ");
    }

    public static void printMenuSetTime() {
        System.out.println("Введите дату/время в формате: dd-MM-yyyy HH:mm" + "\n"
                + "Если вы хотите сделать задачу повторяющейся введите дату/время в формате:"
                + "dataStart$interval$dataEnd" + "\n"
                + "dataStart - дата начала; interval - дни:часы:минуты; dataEnd - дата окончания." + "\n"
                + "Например: 24-08-2020 16:00$01:05:04$26-08-2020 16:00" + "\n"
                + "Ваш ввод:");
    }
}
