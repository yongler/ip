package duke.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    @Test
    void dividerLine() {
        Ui ui = new Ui();
        assertEquals("---------------------------------", ui.dividerLine());
    }
}