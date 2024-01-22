package com.dden.pasbot.utils;

public class Consts {
    public static final String START_MESSAGE = "Greetings, this is PosBot - a bot that finds pin codes to selected departments in your city. " +
            "To get started, select a city from the available ones:";

    public static final String UNKNOWN_COMMAND = "I'm sorry, I don't know that command";
    public static final String CANT_UNDERSTAND = "I'm sorry, I didn't realize what you meant.";
    public static final String ERROR = "Internal error";

    public static final String CHOSE_MESSAGE = "You have selected %s as your city." +
            "\nSelect the type of departments whose pin code you want to know:";

    public static final String CHOSE_TYPE_MESSAGE = "Congratulations, you have selected an departments of type %s." +
            "\nSelect the address of the departments whose pin code you want to know:";

    public static final String CHOSE_ADDRESS_PIN = "Pin code of the selected department: %s.\n" +
            "Please share feedback, was the pin correct?";

    public static final String CHOSE_ADDRESS_NO_PIN = "Unfortunately, we do not have an up-to-date pin for this department. Would you like to add an up-to-date one?";

    public static final String CHOSE_ADDRESS_OUTDATED_PIN = "Unfortunately, the department's pin code has been marked as obsolete. But you can try it in case it works: %s. Did it turn out to be correct?";

    public static final String YES = "YES";

    public static final String NO = "NO";

    public static final String RESTART = "You can write /start to start again. Thanks for using the bot.";
    public static final String PIN_CORRECT_BYE = "We're glad we could help you. " + RESTART;

    public static final String PIN_INCORRECT_MSG = "We are sorry that the pin code is not up to date. Would you like to add an up-to-date one?";

    public static final String PIN_DONT_ADD_BYE = "We're sorry we weren't able to help you.";

    public static final String PIN_ADD_MSG = "Enter the pin code in the following message by adding /pin at the beginning (example: /pin 1245#)";

    public static final String PIN_ADDED_MSG = "Thanks for adding an up to date pin! " + RESTART;

    public static final String PIN_WRONG_ORDER = "Oops, it seems you haven't yet selected the address you want to add the pin to or something went wrong. " + RESTART;

}
