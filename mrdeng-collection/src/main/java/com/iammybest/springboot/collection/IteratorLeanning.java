package com.iammybest.springboot.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

public class IteratorLeanning {
    public static void main(String[] args) {
//
//        Collection collection = new ArrayList();
//        collection.add("A");
//        collection.forEach(item->{
//            System.out.println(item);
//        });


//        List<String> list = Arrays.asList("A","B","C");
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            String item = iterator.next();
//            System.out.println(item);
//            //Exception UnsupportedOperationException
////            if("B".equalsIgnoreCase(item))
////                iterator.remove();
//        }
//        ListIterator<String>listIterator = list.listIterator();
//        System.out.println(listIterator.next());
//        System.out.println(listIterator.previous());
//        System.out.println(listIterator.next());
//        System.out.println(listIterator.next());
////        listIterator.remove();
//        System.out.println(listIterator.next());

        Collection<Long> longArr = new ArrayList<>();
        longArr.add(1L);longArr.add(null);longArr.add(2L);longArr.add(3L);
//        longArr.add(1000000000000000001L);longArr.add(1002L);longArr.add(1003L);
//        longArr.add(1000000000000000001L);longArr.add(1002L);longArr.add(1003L);
//        longArr.add(1000000000000000001L);longArr.add(1002L);longArr.add(1003L);
        printColletion(longArr);
        longArr.remove(null);
        longArr.remove(2L);
        printColletion(longArr);
        Collection<Integer> intArr = new ArrayList(longArr);
        printColletion(intArr);
    }

    public static void  printColletion(Collection collection){
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
