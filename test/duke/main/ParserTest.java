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

        assertEquals(test_date, date);
    }

    @Test
    void splitInputStringBySpaces() {
        String input = "todo Homework";
        String[] splittedInput = InputParser.splitInputStringBySpaces(input);
        String[] testInput = {"todo", "Homework"};
        assertEquals(testInput, splittedInput);
    }
}