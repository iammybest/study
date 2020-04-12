package com.iammybest.thread.bqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by MrDeng on 2016/12/20.
 */
public class Veterinarian extends Thread{
    protected final BlockingQueue<Pet.Appointment<Pet>> appts;
    protected String text="";
    protected final int restTime;
    protected boolean shutdown =  false;


    public Veterinarian(BlockingQueue<Pet.Appointment<Pet>> appts, int restTime) {
        this.appts = appts;
        this.restTime = restTime;
    }

    public synchronized void shutdown(){
        shutdown = true;
    }
    @Override
    public void run() {
        while (!shutdown){
            seePatient();

            try {
                Thread.sleep(restTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void seePatient(){
        try{
            Pet.Appointment<Pet> ap = appts.take();
            Pet patient =  ap.getPatient();
            patient.examine();
        }catch (InterruptedException e){
            shutdown();
        }
    }
}
