package ir.sajjadyosefi.android.xTubeless.classes.example.lambda;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ir.sajjadyosefi.android.xTubeless.classes.example.designPattern.factory.StaffFactory;
import ir.sajjadyosefi.android.xTubeless.classes.example.designPattern.factory.model.Staff;

public class MainClass {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void mainMethod(){

        List<UserInLambldaExample> users = new ArrayList<>();
        users.add(new UserInLambldaExample("hero user 1", 1, 1.5f));
        users.add(new UserInLambldaExample("hero user 2", 2, 2f));
        users.add(new UserInLambldaExample("hero user 3", 3, 2f));
        users.add(new UserInLambldaExample("hero user 4", 4, 0.5f));
        users.add(new UserInLambldaExample("hero user 5", 5, 3f));


        //------------------------------
        //example 1

        //without lambda
        Comparator<UserInLambldaExample> userComparator =new Comparator<UserInLambldaExample>() {
            @Override
            public int compare(UserInLambldaExample u1, UserInLambldaExample u2) {
                return Float.compare(u1.getRank(), u2.getRank());
            }
        };
        users.sort(userComparator.reversed());

        for (UserInLambldaExample user : users) {
            System.out.println(user.getName() + " : " + user.getRank());
        }


        //with lambda
        users.sort((u1, u2) -> (Float.compare(u1.getRank(), u2.getRank())));
        Collections.reverse(users);
        users.forEach(u -> System.out.println(u.getName() + " : " + u.getRank()));

        //with lambda2
        users.sort(Comparator.comparing(UserInLambldaExample::getRank).reversed());
        users.forEach(u -> System.out.println(u.getName() + " : " + u.getRank()));

        users.sort(Comparator.comparing(UserInLambldaExample::getRank).reversed().thenComparing(UserInLambldaExample::getName));


        //------------------------------
        //lambda example 2
        Predicate<UserInLambldaExample> userPredicate = p -> p.getRank() > 1f;
        Predicate<UserInLambldaExample> negative = userPredicate.negate();

        System.out.printf("good users : ");
        for (UserInLambldaExample user : users) {
            if (userPredicate.test(user)) {
                System.out.println(user.getName());

            }
        }
        System.out.println("bad users : ");
        for (UserInLambldaExample user : users) {
            if (negative.test(user)) {
                System.out.println(user.getName());

            }
        }
        //------------------------------
        Runnable methodAsVariable = () -> System.out.println("Hello zero to hero");


    }


    //------------------------------
    //lambda example 3
    public void mainX(String[] args) {
        ZeroToHero zeroToHero = new ZeroToHero();
        ZeroToHeroMessage zeroToHeroMessage = new ZeroToHeroMessage();
        zeroToHero.print(zeroToHeroMessage);
    }

    public class ZeroToHero {
        public void print(ZeroToHeroInterface messaging) {
            messaging.message();
        }
    }

    @FunctionalInterface
    interface ZeroToHeroInterface {
        public void message();
    }
    class ZeroToHeroMessage implements ZeroToHeroInterface {
        @Override
        public void message() {
            System.out.println("Hello  zero to hero");
        }
    }
    //------------------------------

}
