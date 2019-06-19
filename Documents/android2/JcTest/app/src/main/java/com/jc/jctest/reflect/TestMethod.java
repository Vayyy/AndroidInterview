package com.jc.jctest.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author：jc   2019/5/6 17:34
 */
public class TestMethod {

    public static void testStatic() {
        System.out.println("test static");
    }

    private int add(int a, int b) {
        return a + b;
    }

    public void testException() throws IllegalAccessException {
        throw new IllegalAccessException("You have some problem.");
    }


    public static void main(String[] a) {
        TestMethod testMethod = new TestMethod();

        Class testMethodClass = TestMethod.class;
        try {
           /* Method testStaticMethod = testMethodClass.getMethod("testStatic");
            testStaticMethod.invoke(null, null);*/

            Method addMethod = testMethodClass.getDeclaredMethod("add", int.class, int.class);
            addMethod.setAccessible(true);
            int result = (int) addMethod.invoke(testMethod, 2, 3);

            System.out.println("result" + result);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}


