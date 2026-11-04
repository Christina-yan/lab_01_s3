package app;

import java.lang.reflect.Method;

public class Invoker {
    public static void invokeAnnotatedMethods(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            // Проверяем, есть ли аннотация @Repeat
            if (method.isAnnotationPresent(Repeat.class)) {
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();

                // Разрешаем доступ к защищенным и приватным методам
                method.setAccessible(true);

                // Подготавливаем аргументы в зависимости от типа параметров
                Object[] args = prepareArguments(method.getParameterTypes());

                System.out.println("--- Вызов метода: " + method.getName() + " (раз) ---");
                for (int i = 0; i < times; i++) {
                    try {
                        method.invoke(obj, args);
                    } catch (Exception e) {
                        System.err.println("Ошибка при вызове метода: " + e.getMessage());
                    }
                }
            }
        }
    }

    // Метод для генерации тестовых аргументов
    private static Object[] prepareArguments(Class<?>[] paramTypes) {
        Object[] args = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == String.class) {
                args[i] = "Тест";
            } else if (paramTypes[i] == int.class) {
                args[i] = 42;
            } else if (paramTypes[i] == double.class) {
                args[i] = 3.14;
            } else {
                args[i] = null;
            }
        }
        return args;
    }
}