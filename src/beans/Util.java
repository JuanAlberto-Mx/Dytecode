package beans;

import java.util.Arrays;

/**
 * <code>Util</code> class contains a set of methods to compare objects.
 * @author Juan-Alberto Hern&aacute;ndez-Mart&iacute;nez
 * @version %I%, %G%
 */
public final class Util {

    /**
     * Private constructor for avoiding new class instances.
     */
    private Util(){
    }

    /**
     * Compares two objects.
     * @param ob1 the first object.
     * @param obj2 the second object.
     * @return <code>True</code> if the objects are equals; <code>false</code> otherwise.
     */
    public static boolean equal(Object ob1, Object obj2){
        return ob1 == obj2 || ob1 != null && ob1.equals(obj2);
    }

    /**
     * Gets the hash code.
     * @param objects the set of objects.
     * @return the hash code.
     */
    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
}