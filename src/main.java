import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = getInputInt(scanner, "Введите сумму (от 1 до 10000): ", 1, 10000);
        int count = getInputInt(scanner, "Введите количество номиналов банкнот: ", 1, 100);

        int[] banknotes = new int[count];
        System.out.println("Введите номиналы банкнот:");
        for (int i = 0; i < count; i++) {
            banknotes[i] = getInputInt(scanner, "Номинал " + (i + 1) + " (от 1 до 10000): ", 1, 10000);
        }

        Bankomat bankomat = new Bankomat();
        int[][] options = bankomat.getPossibleOptions(sum, banknotes);
        if (options.length == 0) {
            System.out.println("Невозможно разложить данную сумму на указанные номиналы банкнот.");
        } else {
            System.out.println("Возможные варианты:");
            for (int i = 0; i < options.length; i++) {
                for (int j = 0; j < options[i].length; j++) {
                    System.out.print(options[i][j]);
                    if (j < options[i].length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        }
    }

    // Метод проверки допустимых значений.
    private static int getInputInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();

                if (input < min || input > max) {
                    System.out.println("Некорректный ввод. Пожалуйста, введите число от " + min + " до " + max + ".");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
                scanner.nextLine();
            }
        }
    }
}
