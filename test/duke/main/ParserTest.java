package duke.main;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void parseDate() {
        Parser parser = new Parser();
        String input = "2019/10/15 1800";
        LocalDateTime date = parser.parseDate(input);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        LocalDateTime test_date = LocalDateTime.parse(input.trim(), formatter);

//        System.out.println(date);
        assertEquals(test_date, date);
    }
}