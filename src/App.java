import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner STDIN = new Scanner(System.in);

    private static final String[] TRUMP_MARK = { "ハート", "ダイヤ", "スペード", "クローバー" };
    private static final String[] TRUMP_NUMBER = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    private static final int FIRST_MARK_INDEX = 0;
    private static final int LAST_MARK_INDEX = TRUMP_MARK.length - 1;
    private static final int FIRST_NUMBER_INDEX = 0;
    private static final int LAST_NUMBER_INDEX = TRUMP_NUMBER.length - 1;

    private static final int[] CORRECT = new int[2];
    private static final int MARK_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public static void main(String[] args) {
        setCorrectByRandom();

        showMessageWithNewLine(Messages.GAME_START);
        showGameMenu();
        startMarkGuessing();
        showMessageWithNewLine(Messages.NUMBER_GUESSING_START);
        startNumberGuessing();

        STDIN.close();
    }

    private static void setCorrectByRandom() {
        Random random = new Random();

        CORRECT[MARK_INDEX] = random.nextInt(TRUMP_MARK.length);
        CORRECT[NUMBER_INDEX] = random.nextInt(TRUMP_NUMBER.length);
    }

    private static void showGameMenu() {
        showMessageWithNewLine(Messages.MARK_GUESSING_START);
        int count = 0;
        for (String str : TRUMP_MARK) {
            showFormattedMessageForTwoArguments(Messages.MARK_INPUT_DEFINE, count, str);
            count++;
        }
    }

    private static void startMarkGuessing() {
        while (isResponseLimitRangeForMark()) {
            int mark = getPlayerInputForTrumpMark();
            if (isEqual(CORRECT[MARK_INDEX], mark)) {
                showFormattedMessage(Messages.MARK_RIGHT, TRUMP_MARK[mark]);
                break;
            }
            showFormattedMessage(Messages.WRONG_ANSWER, TRUMP_MARK[mark]);
        }
    }

    private static void startNumberGuessing() {
        while (isResponseLimitRangeForNumber()) {
            int number = getPlayerInputForTrumpNumber();
            if (isEqual(CORRECT[NUMBER_INDEX], number)) {
                showFormattedMessageForTwoArguments(Messages.GAME_COMPLETE, TRUMP_MARK[CORRECT[MARK_INDEX]],
                        TRUMP_NUMBER[CORRECT[NUMBER_INDEX]]);
                break;
            }
            showFormattedMessage(Messages.WRONG_ANSWER, TRUMP_NUMBER[number]);
        }
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
        }
        showFormattedMessageForTwoArguments(Messages.NUMBER_MUST_BETWEEN_WARN_FOR_NUMBER, FIRST_MARK_INDEX,
                LAST_MARK_INDEX);
        showNewLine();
        return getPlayerInputForTrumpMark();
    }

    private static int getPlayerInputForTrumpNumber() {
        showMessageWithoutNewLine(Messages.WAITING_INPUT);
        String input = STDIN.next();

        if (isTrumpNumber(input)) {
            return Arrays.asList(TRUMP_NUMBER).indexOf(input);
        }
        showFormattedMessageForTwoArguments(Messages.NUMBER_MUST_BETWEEN_WARN_FOR_STRING,
                TRUMP_NUMBER[FIRST_NUMBER_INDEX], TRUMP_NUMBER[LAST_NUMBER_INDEX]);
        showNewLine();
        return getPlayerInputForTrumpNumber();
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

    private static void showFormattedMessageForTwoArguments(String message, int a, String b) {
        System.out.format(message, a, b);
    }

    private static void showFormattedMessageForTwoArguments(String message, int a, int b) {
        System.out.format(message, a, b);
    }
}
