import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author Yang
 * @since 1.8
 */
public class FilesTest {
    public static void main(String[] args) {
        try {
            filesTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        pathTest();
    }

    /**
     * Path类使用示例
     */
    public static void pathTest() {
//        路径
        Path path = Paths.get("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\test.txt");
        System.out.println(path.getRoot());
        System.out.println(path.getParent());
        System.out.println(path.getFileName());
        System.out.println(path.endsWith("test.txt"));
//        相对路径
        Path pathSibling = path.resolveSibling("text_out.txt");
        System.out.println(pathSibling);
        Path relativize = path.relativize(pathSibling);
        System.out.println(relativize);

        System.out.println(path.toUri());
    }

    /**
     * Files类使用
     */
    public static void filesTest() throws IOException {
//        读写文件
        Path path = Paths.get("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\test.txt");
        Files.write(path, "hello".getBytes());
        List<String> strings = Files.readAllLines(path);
        System.out.println(strings);

//        list列出当前目录  walk递归子目录
        Path directory = Paths.get("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO");
        Files.list(directory).forEach(System.out::println);
        Files.walk(directory).forEach(System.out::println);

        FileVisitor<Path> fileVisitor=new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = file.getFileName().toString();
                String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                if (extension.equals(".txt")) {
                    System.out.println(file.getFileName());
                    System.out.println(attrs.creationTime());
                }
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(directory, fileVisitor);

//        文件 目录
//        增加
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
//            Files.copy();
//            Files.move();
        }
//        删除
        Files.deleteIfExists(path);

    }
}
