import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Задание 1, задача 1: Класс Имя
class Name {
    private String lastName; // Фамилия
    private String firstName; // Личное имя
    private String middleName; // Отчество

    // Конструкторы
    public Name(String firstName) { // Конструктор только с именем
        this(null, firstName, null);// Вызов основного конструктора
    }

    public Name(String lastName, String firstName) { // Конструктор с фамилией и именем
        this(lastName, firstName, null); // Вызов основного конструктора
    }

    public Name(String lastName, String firstName, String middleName) { // Основной конструктор
        this.lastName = lastName; // Инициализируем фамилию
        this.firstName = firstName; // Инициализируем имя
        this.middleName = middleName; // Инициализируем отчество
    }

    public String getLastName() { return lastName; } // Получаем фамилию
    public String getFirstName() { return firstName; } // Получаем имя
    public String getMiddleName() { return middleName; } // Получаем отчество

    @Override
    public String toString() { // Преобразуем в строку
        StringBuilder sb = new StringBuilder(); // Создаем строителя строк

        if (lastName != null && !lastName.isEmpty()) { // Если фамилия задана
            sb.append(lastName); // Добавляем фамилию
        }

        if (firstName != null && !firstName.isEmpty()) { // Если имя задано
            if (sb.length() > 0) sb.append(" "); // Добавляем пробел если уже есть текст
            sb.append(firstName); // Добавляем имя
        }

        if (middleName != null && !middleName.isEmpty()) { // Если отчество задано
            if (sb.length() > 0) sb.append(" "); // Добавляем пробел если уже есть текст
            sb.append(middleName); // Добавляем отчество
        }

        return sb.toString(); // Возвращаем готовую строку
    }
}

// Задание 1, задача 2 и Задания 4-5. Класс Время
class Time {
    private int totalSeconds; // Время в секундах с начала суток

    // Конструктор из секунд
    public Time(int totalSeconds) { // Конструктор из общего числа секунд
        this.totalSeconds = totalSeconds % (24 * 3600); // Обеспечиваем, чтобы было не больше 24 часов
    }

    // Конструктор из часов, минут, секунд
    public Time(int hours, int minutes, int seconds) { // Конструктор из компонентов времени
        this(hours * 3600 + minutes * 60 + seconds); // Преобразуем в секунды и вызываем основной конструктор
    }

    // Для задания 5
    public int getHours() { // Получаем количество часов
        return totalSeconds / 3600; // Часы = общее время / 3600
    }

    public int getMinutes() { // Получаем количество минут с начала часа
        return (totalSeconds % 3600) / 60; // Минуты = остаток от часов / 60
    }

    public int getSeconds() { // Получаем количество секунд с начала минуты
        return totalSeconds % 60; // Секунды = остаток от деления на 60
    }

    @Override
    public String toString() { // Преобразуем в строку формата ЧЧ:ММ:СС
        int hours = getHours(); // Получаем часы
        int minutes = getMinutes(); // Получаем минуты
        int seconds = getSeconds(); // Получаем секунды

        return String.format("%d:%02d:%02d", hours, minutes, seconds); // Форматируем вывод
    }

    // Метод для получения общего количества секунд
    public int getTotalSeconds() { // Получаем общее количество секунд
        return totalSeconds; // Возвращаем значение поля
    }
}

// Задания 2-3. Класс Отдел
class Department {
    private String name; // Название отдела
    private Employee manager;  // Начальник отдела
    private List<Employee> employees; // Список сотрудников отдела

    public Department(String name) { // Конструктор отдела
        this.name = name; // Устанавливаем название
        this.employees = new ArrayList<>(); // Инициализируем пустой список сотрудников
    }

    public String getName() { return name; } // Получаем название отдела

    public Employee getManager() { return manager; } // Получаем начальника отдела

    public void setManager(Employee manager) { // Назначаем начальника
        this.manager = manager; // Устанавливаем начальника
        if (!employees.contains(manager)) { // Если начальника нет в списке сотрудников
            employees.add(manager); // Добавляем его в список
        }
    }

    public void addEmployee(Employee employee) { // Добавляем сотрудника в отдел
        if (!employees.contains(employee)) { // Если сотрудника еще нет в списке
            employees.add(employee); // Добавляем
        }
    }

    public List<Employee> getEmployees() {
        return employees;  // Возвращаем список
    }

    @Override
    public String toString() { // Строковое представление отдела
        return "Отдел: " + name + (manager != null ? ", начальник: " + manager.getName() : ", начальник не назначен");
    }
}

// Задания 2-3. Класс Сотрудник
class Employee {
    private String name; // Имя сотрудника
    private Department department; // Отдел сотрудника

    public Employee(String name, Department department) { // Конструктор сотрудника
        this.name = name; // Устанавливаем имя
        this.department = department; // Устанавливаем отдел
        department.addEmployee(this); // Автоматически добавляем в отдел
    }

    public String getName() { return name; } // Получаем имя сотрудника
    public Department getDepartment() { return department; } // Получаем отдел сотрудника

    @Override
    public String toString() { // Строковое представление сотрудника
        if (department.getManager() == this) { // Если сотрудник - начальник отдела
            return name + " начальник отдела " + department.getName(); // Специальный формат для начальника
        } else {
            return name + " работает в отделе " + department.getName() + // Обычный сотрудник
                    ", начальник которого " + department.getManager().getName();
        }
    }

    // Метод для получения списка всех сотрудников отдела (задание 3)
    public List<Employee> getDepartmentEmployees() { // Получаем всех сотрудников отдела
        return department.getEmployees(); // Делегируем запрос отделу
    }
}

// Главный класс с методом main
public class ex1 {

    // Метод для безопасного ввода целого числа
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) { // Бесконечный цикл до получения корректного ввода
            System.out.print(prompt); // Выводим приглашение для ввода
            try {
                return Integer.parseInt(scanner.nextLine()); // Пытаемся преобразовать ввод в число
            } catch (NumberFormatException e) { // Если ввод не число
                System.out.println("Ошибка! Пожалуйста, введите целое число."); // Выводим сообщение об ошибке
            }
        }
    }

    // Метод для безопасного ввода строки
    public static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt); // Выводим приглашение для ввода
        return scanner.nextLine(); // Читаем строку из консоли
    }

    public static void main(String[] args) { // Главный метод программы
        Scanner scanner = new Scanner(System.in); // Создаем объекта для ввода данных

        System.out.println("Задание 1, задача 1. Имена");
        Name name1 = new Name("Клеопатра"); // Создаем имя только с личным именем
        Name name2 = new Name("Пушкин", "Александр", "Сергеевич"); // Создаем полное имя
        Name name3 = new Name("Маяковский", "Владимир"); // Создаем имя без отчества

        System.out.println("1. " + name1); // Выводим первое имя
        System.out.println("2. " + name2); // Выводим второе имя
        System.out.println("3. " + name3); // Выводим третье имя
        System.out.println();

        System.out.println("Задание 1, задача 2 и задания 4-5. Время");
        Time time1 = new Time(10); // Создаем время из 10 секунд
        Time time2 = new Time(10000); // Создаем время из 10000 секунд
        Time time3 = new Time(100000); // Создаем время из 100000 секунд
        Time time4 = new Time(2, 3, 5); // Создаем время из часов, минут, секунд

        System.out.println("10 секунд: " + time1); // Выводим время 1
        System.out.println("10000 секунд: " + time2); // Выводим время 2
        System.out.println("100000 секунд: " + time3); // Выводим время 3
        System.out.println("2 часа, 3 минуты, 5 секунд: " + time4); // Выводим время 4
        System.out.println();
        
        System.out.println("=== ЗАДАНИЕ 5: АНАЛИЗ ВРЕМЕНИ ===");
        Time testTime = new Time(34056); // Создаем тестовое время для анализа
        System.out.println("Время 34056 секунд: " + testTime); // Выводим время
        System.out.println("Часов: " + testTime.getHours()); // Выводим часы
        System.out.println("Минут с начала часа: " + testTime.getMinutes()); // Выводим минуты
        System.out.println("Секунд с начала минуты: " + testTime.getSeconds()); // Выводим секунды

        Time testTime2 = new Time(4532); // Создаем время 4532 секунд для анализа минут
        System.out.println("\nВремя 4532 секунд: " + testTime2); // Выводим время
        System.out.println("Минут: " + testTime2.getMinutes()); // Выводим минуты

        Time testTime3 = new Time(123); // Создаем время 123 секунды для анализа секунд
        System.out.println("\nВремя 123 секунды: " + testTime3); // Выводим время
        System.out.println("Секунд: " + testTime3.getSeconds()); // Выводим секунды
        System.out.println();

        System.out.println("Задания 2-3. Сотрудники и отделы");

        Department itDepartment = new Department("IT"); // Создаем отдел IT

        // Создаем сотрудников
        Employee petrov = new Employee("Петров", itDepartment); // Создаем сотрудника Петров
        Employee kozlov = new Employee("Козлов", itDepartment); // Создаем сотрудника Козлов
        Employee sidorov = new Employee("Сидоров", itDepartment); // Создаем сотрудника Сидоров

        itDepartment.setManager(kozlov); // Назначаем Козлова начальником

        // Выводим информацию о сотрудниках
        System.out.println(petrov); // Выводим информацию о Петрове
        System.out.println(kozlov); // Выводим информацию о Козлове (начальник)
        System.out.println(sidorov); // Выводим информацию о Сидорове
        System.out.println();

        System.out.println("Задание 3. Список сотрудников отдела");
        List<Employee> departmentEmployees = kozlov.getDepartmentEmployees(); // Получаем список сотрудников
        System.out.println("Сотрудники отдела IT:"); // Выводим заголовок списка
        for (Employee emp : departmentEmployees) { // Перебираем всех сотрудников
            System.out.println("  - " + emp.getName()); // Выводим имя каждого сотрудника
        }
        System.out.println();


        // Часть с вводом данных
        System.out.println("ЧАСТЬ С ВВОДОМ");

        // Ввод имени
        System.out.println("Создаем новое имя:");
        String lastName = getStringInput(scanner, "Фамилия (можно пропустить): "); // Вводим фамилию
        String firstName = getStringInput(scanner, "Имя: "); // Вводим имя
        String middleName = getStringInput(scanner, "Отчество (можно пропустить): "); // Вводим отчество

        Name userName = new Name( // Создаем имя из введенных данных
                lastName.isEmpty() ? null : lastName, // Если фамилия пустая - null, иначе фамилия
                firstName, // Имя
                middleName.isEmpty() ? null : middleName // Если отчество пустое - null, иначе отчество
        );
        System.out.println("Созданное имя: " + userName); // Выводим созданное имя
        System.out.println();

        // Ввод времени через секунды
        System.out.println("Создаем время из секунд:");
        int seconds = getIntInput(scanner, "Вводим количество секунд: "); // Вводим секунды
        Time userTime1 = new Time(seconds); // Создаем время из секунд
        System.out.println("Время: " + userTime1); // Выводим время
        System.out.println("Анализ: " + userTime1.getHours() + " часов, " + // Выводим детальный анализ времени
                userTime1.getMinutes() + " минут, " +
                userTime1.getSeconds() + " секунд");
        System.out.println();

        // Ввод времени через часы, минуты, секунды
        System.out.println("Создаем время из часов, минут, секунд:");
        int hours = getIntInput(scanner, "Часы: "); // Вводим часы
        int minutes = getIntInput(scanner, "Минуты: "); // Вводим минуты
        int secs = getIntInput(scanner, "Секунды: "); // Вводим секунды
        Time userTime2 = new Time(hours, minutes, secs); // Создаем время из компонентов
        System.out.println("Время: " + userTime2); // Выводим время
        System.out.println();

        // Создаем новый отдел и сотрудников
        System.out.println("Создаем новый отдел:");
        String deptName = getStringInput(scanner, "Название отдела: "); // Вводим название отдела
        Department userDepartment = new Department(deptName); // Создаем отдел

        System.out.println("Добавляем сотрудников в отдел:");
        String emp1Name = getStringInput(scanner, "Имя первого сотрудника: "); // Вводим имя 1 сотрудника
        Employee emp1 = new Employee(emp1Name, userDepartment); // Создаем сотрудника 1

        String emp2Name = getStringInput(scanner, "Имя второго сотрудника: "); // Вводим имя 2 сотрудника
        Employee emp2 = new Employee(emp2Name, userDepartment); // Создаем сотрудника 2

        // Назначаем начальника
        System.out.println("Назначаем начальника отдела:");
        System.out.println("1. " + emp1Name); // Выводим вариант 1 - первый сотрудник
        System.out.println("2. " + emp2Name); // Выводим вариант 2 - второй сотрудник
        int choice = getIntInput(scanner, "Выбираем номер: "); // Получаем выбор пользователя

        if (choice == 1) { // Если выбран первый вариант
            userDepartment.setManager(emp1); // Назначаем первого сотрудника начальником
        } else { // Иначе
            userDepartment.setManager(emp2); // Назначаем второго сотрудника начальником
        }

        // Выводим информацию о созданном отделе
        System.out.println();
        System.out.println("СОЗДАННЫЙ ОТДЕЛ");
        System.out.println(emp1); // Выводим информацию о первом сотруднике
        System.out.println(emp2); // Выводим информацию о втором сотруднике
        System.out.println("Список всех сотрудников отдела:"); // Выводим заголовок списка
        for (Employee emp : userDepartment.getEmployees()) { // Перебираем всех сотрудников отдела
            System.out.println("  - " + emp.getName() + // Выводим имя сотрудника
                    (emp == userDepartment.getManager() ? " (начальник)" : "")); // Добавляем отметку для начальника
        }

        scanner.close(); // Закрываем сканер

        System.out.println();
        System.out.println("Программа завершена"); // Выводим финальное сообщение
    }
}