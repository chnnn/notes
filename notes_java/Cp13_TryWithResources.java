import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Cp13_TryWithResources {
    class Cp13_TryWithResourcesAutoClosable implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Auto closing happens before catch block");
        }
    }

    private void run(Path path1, Path path2) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(path1); BufferedWriter out = Files.newBufferedWriter(path2)) {
        } catch (IOException e) {
        }
        try (Scanner s = new Scanner(System.in);
                Cp13_TryWithResourcesAutoClosable x = new Cp13_TryWithResourcesAutoClosable();) {
            throw new RuntimeException("msg");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally");
        }

        // though catch/finally could be omitted,
        // IOException must be thrown at the parent method.
        // try (BufferedReader in = Files.newBufferedReader(path1); BufferedWriter out =
        // Files.newBufferedWriter(path2)) {
        // }
    }

    public static void main(String[] args) throws IOException {
        Cp13_TryWithResources x = new Cp13_TryWithResources();
        x.run(Paths.get("no"), Paths.get("no"));
    }
}

class Cp13_TryWithResources_JammedTurkeyCage implements AutoCloseable {
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door does not close");
    }

    public static void main(String[] args) {
        // ! try (Cp13_TryWithResources_JammedTurkeyCage t = new
        // Cp13_TryWithResources_JammedTurkeyCage()) {
        // System.out.println("put turkeys in");
        // } // IllegalStateException thrown

        // 1
        try (Cp13_TryWithResources_JammedTurkeyCage t = new Cp13_TryWithResources_JammedTurkeyCage()) {
            System.out.println("put turkeys in");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
        }
        System.out.println("------------------------");

        // 2
        try (Cp13_TryWithResources_JammedTurkeyCage t = new Cp13_TryWithResources_JammedTurkeyCage()) {
            System.out.println("put turkeys in");
            throw new IllegalStateException("turkeys ran off");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println(t.getMessage());
        }
    }
}
