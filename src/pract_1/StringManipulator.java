package pract_1;

import java.util.function.Consumer;

public class StringManipulator implements Consumer<String> {
    /**
     * Принимает строку и изменяет каждый третий символ на его эквивалент в верхнем регистре.
     *
     * @param  input  входная строка, которую нужно изменить
     */
    @Override
    public void accept(String input) {
        StringBuilder modifiedString = new StringBuilder(input);
        for (int i = 2; i < input.length(); i += 3) {
            char currentChar = input.charAt(i);
            char upperCaseChar = Character.toUpperCase(currentChar);
            modifiedString.setCharAt(i, upperCaseChar);
        }
        System.out.println(modifiedString);
    }
    public static void main(String[] args) {
        StringManipulator stringManipulator = new StringManipulator();
        stringManipulator.accept("Hello, world!");    }
}
