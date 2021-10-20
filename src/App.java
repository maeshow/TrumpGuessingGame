import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner STDIN = new Scanner(System.in);

    private static final String[] TRUMP_MARK = { "ハート", "ダイヤ", "スペード", "クローバー" };
    private static final String[] TRUMP_NUMBER = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    private static final int[] CORRECT = new int[2];

    public static void main(String[] args) {
        setCorrectByRandom();

        showMessageWithNewLine(Messages.GAME_START);
        for (String str : Messages.MARK_GUESSING_START) {
            showMessageWithNewLine(str);
        }

        while (isResponseLimitRangeForMark()) {
            int mark = getPlayerInputForTrumpMark();
            if (isEqual(CORRECT[0], mark)) {
                showFormattedMessage(Messages.MARK_RIGHT, TRUMP_MARK[mark]);
                break;
            }
            showFormattedMessage(Messages.WRONG_ANSWER, TRUMP_MARK[mark]);
        }

        showMessageWithNewLine(Messages.NUMBER_GUESSING_START);

        while (isResponseLimitRangeForNumber()) {
            int number = getPlayerInputForTrumpNumber();
            if (isEqual(CORRECT[1], number)) {
                showFormattedMessageForTwoArguments(Messages.GAME_COMPLETE, TRUMP_MARK[CORRECT[0]],
                        TRUMP_NUMBER[CORRECT[1]]);
                break;
            }
            showFormattedMessage(Messages.WRONG_ANSWER, TRUMP_NUMBER[number]);
        }

        STDIN.close();
    }

    private static void setCorrectByRandom() {
        Random random = new Random();

        CORRECT[0] = random.nextInt(TRUMP_MARK.length);
        CORRECT[1] = random.nextInt(TRUMP_NUMBER.length);
    }

    private static int getPlayerInputForTrumpMark() {
        showMessageWithoutNewLine(Messages.WAITING_INPUT);

        String input = STDIN.next();
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showMessageWithNewLine(Messages.ENTER_NUMBER_WARN);
            showNewLine();
            return getPlayerInputForTrumpMark();
        }

        if (isTrumpMarkRange(inputNumber)) {
            return inputNumber;
        } else {
            showMessageWithNewLine(Messages.NUMBER_MUST_BETWEEN_0_TO_3_WARN);
            showNewLine();
            return getPlayerInputForTrumpMark();
        }
    }

    private static int getPlayerInputForTrumpNumber() {
        showMessageWithoutNewLine(Messages.WAITING_INPUT);
        String input = STDIN.next();

        if (isTrumpNumber(input)) {
            return Arrays.asList(TRUMP_NUMBER).indexOf(input);
        } else {
            showMessageWithNewLine(Messages.NUMBER_MUST_BETWEEN_0_TO_K_WARN);
            showNewLine();
            return getPlayerInputForTrumpNumber();
        }
    }

    private static boolean isResponseLimitRangeForMark() {
        return true;
    }

    private static boolean isResponseLimitRangeForNumber() {
        return true;
    }

    private static boolean isEqual(int a, int b) {
        return a == b;
    }

    private static boolean isTrumpMarkRange(int a) {
        return 0 <= a && a < TRUMP_MARK.length;
    }

    private static final boolean isTrumpNumber(String str) {
        return Arrays.asList(TRUMP_NUMBER).contains(str);
    }

    private static void showMessageWithNewLine(String message) {
        System.out.println(message);
    }

    private static void showMessageWithoutNewLine(String message) {
        System.out.printf(message);
    }

    private static void showNewLine() {
        System.out.println();
    }

    private static void showFormattedMessage(String message, String arg) {
        System.out.format(message, arg);
    }

    private static void showFormattedMessageForTwoArguments(String message, String a, String b) {
        System.out.format(message, a, b);
    }
}
