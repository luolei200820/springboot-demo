package org.eu.luolei.utils;

public class ThreadLocalUtils {
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    // 清除ThreadLocal中的数据，防止内存泄露
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
