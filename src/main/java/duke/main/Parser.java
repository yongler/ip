package duke.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {};

    public static LocalDateTime parseDate(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm ");
        LocalDateTime date = LocalDateTime.parse(by, formatter);
        return date;
    }
}
