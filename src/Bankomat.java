import java.util.*;

public class Bankomat {

    //Метод поиска доступности разложения, систематизации и отправки пользователю.
    // int sum - сумма, денег, которую клиент хочет снять в банке.
    // int [] banknotes - масссив номиналов банкнот, которые есть в наличии в данном банкомате.
    public int[][] getPossibleOptions(int sum, int[] banknotes) {
        Arrays.sort(banknotes);

        boolean[] possBank = new boolean[sum + 1];
        possBank[0] = true;
        // Цикл проверки банкнот на допустимость к разложени. possBank - possibleBanknotes
        for (int i = 1; i <= sum; i++) {
            for (int banknote : banknotes) {
                if (i >= banknote) {
                    possBank[i] = possBank[i] || possBank[i - banknote];
                }
            }
        }

        if (!possBank[sum]) {
            return new int[0][];
        }

        List<int[]> options = new ArrayList<>();
        List<Integer> possibleOptions = new ArrayList<>();
        //Метод нахождения банкнот
        generateOptions(sum, banknotes, possibleOptions, options, banknotes.length - 1, possBank);

        return options.toArray(new int[0][]);
    }
    //Метод нахождения всех доступных вариантов номиналов банкнот.
    // int sum - сумма, веденная пользователем, которая будет уменьшаться по мере работы рекурсии.
    // int [] banknotes - масссив номиналов банкнот, которые есть в наличии в данном банкомате.
    // List<Integer> possibleOptions - лист, в который записывается возможный вариант выдачи, который будет записан List<int[]> options
    // List<int[]> options - окончательный лист со всеми возможноными комбинациями
    // int start - параметр, который отвечает за колличество номиналов в массиве int[] banknotes.
    // boolean[] possBank - отвечает за возможность разложения числа.

    private void generateOptions(int sum, int[] banknotes, List<Integer> possibleOptions, List<int[]> options, int start, boolean[] possBank) {
        if (sum == 0) {
            int[] option = new int[possibleOptions.size()];
            for (int i = 0; i < possibleOptions.size(); i++) {
                option[i] = possibleOptions.get(i);
            }
            options.add(option);
            return;
        }

        for (int i = start; i >= 0; i--) {
            if (sum >= banknotes[i] && possBank[sum - banknotes[i]]) {
                possibleOptions.add(banknotes[i]);
                generateOptions(sum - banknotes[i], banknotes, possibleOptions, options, i, possBank);
                possibleOptions.remove(possibleOptions.size() - 1);
            }
        }
    }
}