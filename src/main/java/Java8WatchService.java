import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class Java8WatchService {
    private final WatchService watcher;
    private final Map<WatchKey, Path> dirWatchers;

    public Java8WatchService(Path dir) throws IOException {
        this.watcher= (WatchService) FileSystems.getDefault().newWatchService();
        this.dirWatchers=new HashMap<WatchKey, Path>();
        scanAndRegisterDirectories(dir);
    }

    private void registerDirWatchers(Path dir) throws IOException{
        WatchKey key=dir.register((java.nio.file.WatchService) watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        dirWatchers.put(key, dir);
    }
    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void processEvents() {
        while(true) {
            WatchKey key;
            try {
                key=watcher.take();
            } catch(InterruptedException x) {
                return;
            }
            Path dir = dirWatchers.get(key);
            if(dir==null) 	continue;
            for(WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s\n",event.kind().name(),child);

                if(kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    try {
                        if(Files.isDirectory(child)) scanAndRegisterDirectories(child);
                    } catch(IOException x) {}
                } else if(kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                    if(Files.isDirectory(child)) dirWatchers.remove(key);
                }
            }

            boolean valid = key.reset();
            if(!valid) {
                dirWatchers.remove(key);
                if(dirWatchers.isEmpty()) break;
            }
        }
    }

}

