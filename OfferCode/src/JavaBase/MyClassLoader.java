package JavaBase;


import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String path;    // 加载器路径
    private String name;    // 类加载器名称

    public MyClassLoader(String name, String path) {
        super();    // 应用加载器成为该类的父类加载器
        this.path = path;
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name, String path) {
        super(parent);    //调用父类加载器的构造方法
        this.path = path;
        this.name = name;
    }

    /**
     * 通过自定义ClassLoader加载自定义类
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = readClassFileToByteArray(name);
        return this.defineClass(name, data, 0, data.length);
    }

    /**
     * 获取.class字节码数组
     * D:\test\com\jvm\demo.java
     *
     * @param name
     * @return
     */
    private byte[] readClassFileToByteArray(String name) {
        InputStream iStream = null;
        byte[] returnData = null;
        name = name.replaceAll("\\.", "/");

        String filePath = this.path + name + ".class";
        File file = new File(filePath);

        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        try {
            iStream = new FileInputStream(file);
            int tmp = 0;
            while ((tmp = iStream.read()) != -1) {    // 表示还存在
                oStream.write(tmp);
            }
            returnData = oStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                iStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return returnData;
    }

}
