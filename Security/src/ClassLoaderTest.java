import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author Yang
 * @version 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String className = "SecurityTest";
        loadClass(className);
    }

    public static void loadClass(String name) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ClassLoader classLoader = new CryptoClassLoader();
        Class<?> c = classLoader.loadClass(name);

        Method main = c.getMethod("main", String[].class);
        main.invoke(null,(Object)new String[]{});
    }
}

/**
 * 自定义ClassLoader,用于加载加密的class文件
 */
class CryptoClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = getClassBytes(name);
            Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
            if (c == null) {
                throw new ClassNotFoundException();
            }
            return c;
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    /**
     *获取文件中的字节，并解密
     * @param name 必须是完整名，如java.util.list
     * @return 文件的class字节
     */
    private byte[] getClassBytes(String name) throws IOException {
        String fileName = name.replace(".", "/") + ".crypto";
        String filePath = "D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\out\\production\\Security\\"+fileName;
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(bytes);
    }
}

/**
 * 加密class文件
 */
class CryptoClass {
    public static void main(String[] args) {
        String file = "D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\out\\production\\Security\\SecurityTest.class";
        try {
            cryptoClass(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filePath 完整类文件路径
     * @throws IOException
     */
    public static void cryptoClass(String filePath) throws IOException {
//        对类文件进行加密
        Path inputFile = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(inputFile);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
//        写入文件
        String fileName=inputFile.getFileName().toString();
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
        String encoderFile = inputFile.getParent() + File.separator + fileNameWithoutExtension + ".crypto";
//        if (Files.exists(Paths.get(encoderFile))) {
//            Files.deleteIfExists(Paths.get(encoderFile));
//        }
        OutputStream out = new FileOutputStream(encoderFile);
        out.write(encode);
    }
}
