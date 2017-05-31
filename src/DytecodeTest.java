import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DytecodeTest {

    public static void main(String... args) throws Exception {
        Dytecode dytecode = new Dytecode();

        // Compiling and loading multiple classes in a specific directory
        List<File> listFiles = dytecode.getJavaFiles("/home/unmasked_00/Documentos/Programacion/Pruebas/app");
        List<List<Class<?>>> compiledClasses = dytecode.compileAndLoad(listFiles);

        for(List<Class<?>> list : compiledClasses){
            for(Class cls : list) {
                System.out.println(cls.getName());
                for(Method m : cls.getDeclaredMethods()){
                    System.out.println(m.getName());
                }
            }
        }

        //Compiling and loading a source code contained in a String variable
        String sc =
                "package ann;\n" +
                "\n" +
                "public class X {\n" +
                "    interface Interface{\n" +
                "        void m1(String p1);\n" +
                "    }\n" +
                "\n" +
                "    class InnerClass implements Interface{\n" +
                "        public void m1(String p1){}\n" +
                "    }\n" +
                "\n" +
                "    class OtherInnerClass extends X.InnerClass{\n" +
                "\n" +
                "    }\n" +
                "    public static void main(String... a){\n" +
                "\n" +
                "    }\n" +
                "}";

        List<Class<?>> classes = dytecode.compileAndLoad("X", sc);

        classes.forEach(cls -> {
            for(Method m : cls.getDeclaredMethods())
                System.out.println(cls.getName() + "\t" + m.getName());
        });
    }
}