import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPath_WhenCorrect_CheckIfFileExist() {
        Path homePath = Paths.get("./"+PLAY_WITH_NIO);
        Assertions.assertTrue(Files.exists(homePath));
    }
    @Test
    public void givenPath_WhenCorrect_CheckIfFileNotExist() {
        Path playPath = Paths.get("./"+PLAY_WITH_NIO);
        if(Files.exists(playPath))
            FileUtils.deleteFiles(playPath.toFile());
        Assertions.assertFalse(Files.exists(playPath));
    }
    @Test
    public void createDirectory_WhenCorrect_CheckIfDirectoryExist() {
        Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
        try {
            Files.createDirectories(playPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(Files.exists(playPath));
    }
    @Test
    public void createFilesWhenCorrect_CheckIfFilesExist(){
        IntStream.range(1, 10).forEach(counter -> {
            Path tempFile=Paths.get("./temp"+counter);
            Assertions.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            }catch(IOException e) {
                Assertions.assertTrue(Files.exists(tempFile));
            }
        });

    }
    @Test
    public void givenADirectoryWhenWatchedListAllTheActivities() throws IOException{
        Path dir=Paths.get(HOME+"/"+ PLAY_WITH_NIO);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new Java8WatchService(dir).processEvents();
    }

}