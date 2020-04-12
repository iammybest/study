package com.iammybest.thread.bqueue;

/**
 * Created by MrDeng on 2016/12/20.
 */
public abstract class Pet {
    private final String name;

    public Pet(String name){
        this.name=name;
    }

    public abstract void examine();

    public class Cat extends Pet{

        public Cat(String name) {
            super(name);
        }

        public void examine() {

        }
    }


    public class Dog extends Pet{

        public Dog(String name) {
            super(name);
        }

        public void examine() {

        }
    }

    public class Appointment<T>{
        private final  T toBeSeen;
        public Appointment(T incoming){
            toBeSeen = incoming;
        }
        public T getPatient(){
            return toBeSeen;
        }

    }

    public static void main(String[] args) {

//        Veterinarian v1 = new Veterinarian(appts,3000);
    }
}
