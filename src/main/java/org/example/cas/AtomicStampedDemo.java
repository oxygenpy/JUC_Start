package org.example.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 17:00
 * @date 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class Book{
    int id;
    String name;
}
public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book b1 = new Book(18, "hhh");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(b1, 1);
        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book b2 = new Book(19, "jjj");

        boolean andSet;
        andSet = stampedReference.compareAndSet(b1, b2, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(andSet + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        andSet = stampedReference.compareAndSet(b2, b1, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(andSet + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}
