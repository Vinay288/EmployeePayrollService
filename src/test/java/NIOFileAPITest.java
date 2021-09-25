import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOFileAPITest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPath_WhenCorrect_CheckIfFileExist() {
        Path homePath = Paths.get(HOME);

        Assertions.assertTrue(Files.exists(homePath));
    }
}