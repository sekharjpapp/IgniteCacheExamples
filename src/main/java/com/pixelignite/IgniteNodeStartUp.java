package com.pixelignite;


import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;

public class IgniteNodeStartUp {
    public static void main(String[] args) throws IgniteException {


        Ignition.start("F:\\apache-ignite\\apache-ignite-2.7.0-bin\\config\\example-ignite.xml");


 }
}
