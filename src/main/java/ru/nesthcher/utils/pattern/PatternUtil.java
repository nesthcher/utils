package ru.nesthcher.utils.pattern;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * Утилитный класс для работы с регулярными выражениями и шаблонами.
 */
@UtilityClass
public class PatternUtil {
    private final Pattern URL_PATTERN = Pattern.compile(
        "^(https?://)?([\\w\\-]+\\.)+[\\w\\-]+(:\\d+)?(/\\S*)?$",
        Pattern.CASE_INSENSITIVE
    );

    private final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$",
        Pattern.CASE_INSENSITIVE
    );

    private final Pattern PHONE_PATTERN = Pattern.compile(
        "^\\+?\\d{10,15}$"
    );

    private final Pattern USERNAME_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_]+$"
    );

    private final Pattern IPV4_PATTERN = Pattern.compile(
        "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.|$)){4}$"
    );

    private final Pattern HEX_COLOR_PATTERN = Pattern.compile(
        "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"
    );

    private final Pattern UUID_PATTERN = Pattern.compile(
        "^[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}$"
    );

    private final Pattern DATE_PATTERN = Pattern.compile(
        "^\\d{4}-\\d{2}-\\d{2}$"
    );

    private final Pattern TIME_PATTERN = Pattern.compile(
        "^\\d{2}:\\d{2}:\\d{2}$"
    );

    private final Pattern NUMBER_PATTERN = Pattern.compile(
        "^-?\\d+(\\.\\d+)?$"
    );

    private final Pattern MAC_PATTERN = Pattern.compile(
        "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$"
    );

    private final Pattern BASE64_PATTERN = Pattern.compile(
        "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$"
    );

    private final Pattern BLANK_PATTERN = Pattern.compile(
        "^\\s*$"
    );

    private final Pattern RUSSIAN_PATTERN = Pattern.compile(
        "^[А-Яа-яЁё]+$"
    );

    private final Pattern ENGLISH_PATTERN = Pattern.compile(
        "^[A-Za-z]+$"
    );

    private final Pattern RUSSIAN_ENGLISH_PATTERN = Pattern.compile(
        "^[A-Za-zА-Яа-яЁё]+$"
    );

    private final Pattern RUSSIAN_WITH_DIGITS_PATTERN = Pattern.compile(
        "^[А-Яа-яЁё0-9]+$"
    );

    private final Pattern ENGLISH_WITH_DIGITS_PATTERN = Pattern.compile(
        "^[A-Za-z0-9]+$"
    );
    
    private final Pattern RUSSIAN_ENGLISH_WITH_DIGITS_PATTERN = Pattern.compile(
        "^[A-Za-zА-Яа-яЁё0-9]+$"
    );

    /**
     * Проверяет, соответствует ли строка формату URL.
     *
     * @param str строка для проверки
     * @return true, если строка является URL, иначе false
     */
    public boolean isUrl(
            final String str
    ) {
        return URL_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату email.
     *
     * @param str строка для проверки
     * @return true, если строка является email, иначе false
     */
    public boolean isEmail(
            final String str
    ) {
        if (str == null) return false;
        return EMAIL_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату телефона.
     *
     * @param str строка для проверки
     * @return true, если строка является телефоном, иначе false
     */
    public boolean isPhone(
            final String str
    ) {
        if (str == null) return false;
        return PHONE_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату username.
     *
     * @param str строка для проверки
     * @return true, если строка является username, иначе false
     */
    public boolean isUsername(
            final String str
    ) {
        if (str == null) return false;
        return USERNAME_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату IPv4-адреса.
     *
     * @param str строка для проверки
     * @return true, если строка является IPv4-адресом, иначе false
     */
    public boolean isIPv4(
            final String str
    ) {
        if (str == null) return false;
        return IPV4_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату hex-цвета (#RRGGBB или #RGB).
     *
     * @param str строка для проверки
     * @return true, если строка является hex-цветом, иначе false
     */
    public boolean isHexColor(
            final String str
    ) {
        if (str == null) return false;
        return HEX_COLOR_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату UUID.
     *
     * @param str строка для проверки
     * @return true, если строка является UUID, иначе false
     */
    public boolean isUUID(
            final String str
    ) {
        if (str == null) return false;
        return UUID_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату даты (yyyy-MM-dd).
     *
     * @param str строка для проверки
     * @return true, если строка является датой, иначе false
     */
    public boolean isDate(
            final String str
    ) {
        if (str == null) return false;
        return DATE_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату времени (HH:mm:ss).
     *
     * @param str строка для проверки
     * @return true, если строка является временем, иначе false
     */
    public boolean isTime(
            final String str
    ) {
        if (str == null) return false;
        return TIME_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, является ли строка числом (целым или дробным).
     *
     * @param str строка для проверки
     * @return true, если строка является числом, иначе false
     */
    public boolean isNumber(
            final String str
    ) {
        if (str == null) return false;
        return NUMBER_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, соответствует ли строка формату MAC-адреса.
     *
     * @param str строка для проверки
     * @return true, если строка является MAC-адресом, иначе false
     */
    public boolean isMac(
            final String str
    ) {
        if (str == null) return false;
        return MAC_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, является ли строка Base64.
     *
     * @param str строка для проверки
     * @return true, если строка является Base64, иначе false
     */
    public boolean isBase64(
            final String str
    ) {
        if (str == null) return false;
        return BASE64_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, является ли строка пустой или состоит только из пробелов.
     *
     * @param str строка для проверки
     * @return true, если строка пустая или пробельная, иначе false
     */
    public boolean isBlank(
            final String str
    ) {
        if (str == null) return true;
        return BLANK_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, содержит ли строка только русские буквы.
     */
    public boolean isRussian(
            final String str
    ) {
        if (str == null) return false;
        return RUSSIAN_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, содержит ли строка только английские буквы.
     */
    public boolean isEnglish(
            final String str
    ) {
        if (str == null) return false;
        return ENGLISH_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, содержит ли строка только русские и английские буквы.
     */
    public boolean isRussianEnglish(
            final String str
    ) {
        if (str == null) return false;
        return RUSSIAN_ENGLISH_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, содержит ли строка только русские буквы и цифры.
     */
    public boolean isRussianWithDigits(
            final String str
    ) {
        if (str == null) return false;
        return RUSSIAN_WITH_DIGITS_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, содержит ли строка только английские буквы и цифры.
     */
    public boolean isEnglishWithDigits(
            final String str
    ) {
        if (str == null) return false;
        return ENGLISH_WITH_DIGITS_PATTERN.matcher(str).matches();
    }

    /**
     * Проверяет, содержит ли строка только русские и английские буквы и цифры.
     */
    public boolean isRussianEnglishWithDigits(
            final String str
    ) {
        if (str == null) return false;
        return RUSSIAN_ENGLISH_WITH_DIGITS_PATTERN.matcher(str).matches();
    }
}