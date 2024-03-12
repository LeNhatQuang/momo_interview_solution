package org.example;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class Printer {
    private static Printer instance;
    private Printer(){}

    public static Printer getInstance(){
        if(instance == null){
            synchronized(Printer.class){
                if(instance == null){
                    instance = new Printer();
                }
            }
        }
        return instance;
    }

    public <T> void printList (Collection<T> collection) {
        Optional<T> first = collection.stream().findFirst();
        if(!first.isPresent()) {
            System.out.print("Nothing to show!");
            return;
        }

        Field[] fields = Arrays.stream(first.get().getClass().getDeclaredFields()).filter(
                        e -> e.getAnnotation(Printable.class) != null)
                .toArray(Field[]::new);

        System.out.println("______________________________________________________");
        for (int i = 0; i < fields.length; i++) {
            System.out.print(" | " + fields[i].getAnnotation(Printable.class).header());
        }
        System.out.println(" | ");
        System.out.println("______________________________________________________");

        collection.stream().forEach(element -> {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(element);
                    if(value instanceof LocalDate) {
                        value = DateUtil.format((LocalDate) value);
                    }
                    System.out.print(" | " + value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" |");
        });
    }
}
