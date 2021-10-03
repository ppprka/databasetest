package ru.danko.testtask;

import java.util.Scanner;

public class Menu {
    public static void menu(){
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        while(cont) {
            System.out.println("Выберите действие: ");
            System.out.println("1. Добавить нового студента;");
            System.out.println("2. Обновить информацию о студенте;");
            System.out.println("3. Получить список студентов;");
            System.out.println("4. Удалить студента;");
            System.out.println("5. Выход.");
            int decision = sc.nextInt();
            if (decision >= 1 && decision <= 4) {
                CRUD crud = new CRUD();
                switch (decision) {
                    case (1):
                        System.out.println(crud.newStudent());
                        System.out.println();
                        break;
                    case (2):
                        System.out.println("Введите id студента информацию о котором хотите обновить");
                        crud.updateStudent(sc.nextLong());
                        System.out.println();
                        break;
                    case (3):
                        boolean cont2 = true;
                        while (cont2) {
                            System.out.println("Выберите необходимый вид списка: ");
                            System.out.println("1. Вывести всех студентво;");
                            System.out.println("2. Вывести по id;");
                            System.out.println("3. Вывести по имени;");
                            System.out.println("4. Вывести по фамилии;");
                            System.out.println("5. Вывести по номеру группы;");
                            System.out.println("6. Вывести по году выпуска;");
                            System.out.println("7. Вывод списка групп где больше определенного количества студентов;");
                            System.out.println("8. Вывод студентов которые заканчивают в один год;");
                            System.out.println("9. Назад.");
                            int decision2 = sc.nextInt();
                            if (decision2 >= 1 && decision2 <= 8) {
                                switch (decision2) {
                                    case (1):
                                        crud.getStudentAll();
                                        System.out.println();
                                        break;
                                    case (2):
                                        System.out.println("Введите id");
                                        crud.getStudentAllOrByIdOrNameOrSurnameOrNumberOrGraduation("id", sc.next());
                                        System.out.println();
                                        break;
                                    case (3):
                                        System.out.println("Введите имя");
                                        crud.getStudentAllOrByIdOrNameOrSurnameOrNumberOrGraduation("name", sc.next());
                                        System.out.println();
                                        break;
                                    case (4):
                                        System.out.println("Введите фамилию");
                                        crud.getStudentAllOrByIdOrNameOrSurnameOrNumberOrGraduation("surname", sc.next());
                                        System.out.println();
                                        break;
                                    case (5):
                                        System.out.println("Введите номер группы");
                                        crud.getStudentAllOrByIdOrNameOrSurnameOrNumberOrGraduation("number", sc.next());
                                        System.out.println();
                                        break;
                                    case (6):
                                        System.out.println("Введите год выпуска");
                                        crud.getStudentAllOrByIdOrNameOrSurnameOrNumberOrGraduation("graduation_yea", sc.next());
                                        System.out.println();
                                        break;
                                    case (7):
                                        System.out.println("Введите количество студентов");
                                        System.out.println();
                                        crud.getStudentByNumber(sc.nextInt());
                                        break;
                                    case (8):
                                        System.out.println("Введите год");
                                        crud.getStudentsBySameYear(sc.next());
                                        System.out.println();
                                        break;
                                }
                            }
                            if (decision2 == 9) {
                                cont2 = false;
                            }
                            if(decision2<1||decision2>9) {
                                System.out.println("Введено некорректное значение.");
                                System.out.println();
                            }
                        }
                        break;
                    case (4):
                        System.out.println("Введите id студента для удаления");
                        crud.deleteStudent(sc.nextLong());
                        System.out.println();
                        break;
                }
            }
            if(decision==5){
                cont=false;
            }
            if(decision<1||decision>5) {
                System.out.println("Введено некорректное значение.");
                System.out.println();
            }
        }
    }
}
