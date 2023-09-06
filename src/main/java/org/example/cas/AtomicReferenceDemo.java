package org.example.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 16:15
 * @date 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    String name;
    int age;
}
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User u1 = new User("zs", 14);
        User u2 = new User("li", 14);

        atomicReference.set(u1);
        System.out.println(atomicReference.compareAndSet(u1, u2));
        System.out.println(atomicReference.get());
    }

}
