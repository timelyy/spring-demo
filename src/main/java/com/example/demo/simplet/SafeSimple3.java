package com.example.demo.simplet;

public class SafeSimple3 {
    private SafeSimple3(){
    }
    public static SafeSimple3 getInstance(){
        return SafeSimple3Instence.INSTENCE.safeSimple3;
    }
    private enum SafeSimple3Instence{
        INSTENCE;
        private SafeSimple3 safeSimple3;
        SafeSimple3Instence(){
            safeSimple3 = new SafeSimple3();
        }
    }
}
