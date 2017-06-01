import compiler.DytecodeCompiler;
import beans.*;
import loader.CompiledClassListLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>Dytecode</code> class allows developers to use the <code>DytecodeCompiler</code>
 * functionality to compile and load bytecode dynamically without having <code>.class</code>
 * files.
 * @author Juan-Alberto Hern&aacute;ndez-Mart&iacute;nez
 * @version %I%, %G%
 */
public class Dytecode {

    /**
     * Compiles and loads in memory a list of <code>Java</code> files without generating
     * <code>.class</code> files for each one.
     * @param fileList the list of <code>Java</code> files.
     * @return a list of classes dynamically compiled in memory. The generated bytecode
     * has no <code>.class</code> file associated.
     * @throws Exception
     */
    public List<List<Class<?>>> compileAndLoad(List<File> fileList) throws Exception {
        List<List<Class<?>>> compiledClasses = new ArrayList<>();
        String className;
        String filePath;
        byte[] sourceCode;

        for(File file : fileList){
            className = file.getName().substring(0, file.getName().lastIndexOf("."));
            filePath = file.getPath();

            sourceCode = Files.readAllBytes(Paths.get(filePath));

            compiledClasses.add(compileAndLoad(className, new String(sourceCode)));
        }

        return compiledClasses;
    }

    /**
     * Compiles and loads a specific source code declared in a <code>String</code> variable
     * dynamically in memory.
     * @param className the name of the class to compile and load dynamically.
     * @param sourceCode the source code declared as a <code>String</code> data type.
     * @return a list of classes dynamically compiled in memory. The generated bytecode
     * has no <code>.class</code> file associated.
     * @throws Exception
     */
    public List<Class<?>> compileAndLoad(String className, String sourceCode) throws Exception {
        DytecodeCompiler dytecodeCompiler = new DytecodeCompiler();

        CompiledClassList compiledCassList = dytecodeCompiler.compile(className, sourceCode);

        CompiledClassListLoader classListLoader = new CompiledClassListLoader();

        return classListLoader.loadAsList(compiledCassList);
    }
}