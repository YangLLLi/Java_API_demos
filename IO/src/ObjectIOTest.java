import java.io.*;

/**
 * 序列化对象示例
 * ObjectInputStream
 * ObjectOutputStream
 * @author Yang
 * @since 1.8
 */
public class ObjectIOTest implements Serializable {
    private static final long serialVersionUID = -6496991445081395340L;
    private int i;
    private String s;
    //    下面两个变量不会序列化
    private transient double d;
    private static boolean b = true;

    public static void main(String[] args) {
//序列化
        try (OutputStream outputStream = new FileOutputStream("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\t2.dat")) {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            ObjectIOTest test = new ObjectIOTest();
            test.setI(10);
            test.setD(1.2);
            test.setS("aa");
            out.writeObject(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
//反序列化
        try (InputStream inputStream = new FileInputStream("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\t2.dat")) {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            ObjectIOTest test = (ObjectIOTest) in.readObject();
            System.out.println(test.getD());
            System.out.println(test.getS());
            System.out.println(test.getI());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义需覆盖readObject, writeObject方法
     *  private void readObject(ObjectInputStream in)
     *  private void writeObject(ObjectOutputStream out)
     *
     *  单例模式的类需考虑实现
     *  private Object readResolve()
     */


    public ObjectIOTest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public static boolean isB() {
        return b;
    }

    public static void setB(boolean b) {
        ObjectIOTest.b = b;
    }

    @Override
    public String toString() {
        return "ObjectIOTest{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", d=" + d +
                '}';
    }
}

