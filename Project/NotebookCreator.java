//Want to make it so that files will be written/sorted based on what the refereces in the file are

//in a way - try and combine the topo sort w this?

//Documentation/Source:

//https://www.mkyong.com/java/how-to-create-directory-in-java/ -- how to create a new folder

package com.mkyong.file;

import java.io.File;

public class CreateDirectoryExample {

    public static void main(String[] args) {

        File file = new File("C:\\Directory1");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }

    }

}



<<<<<<< HEAD
=======
//test 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

>>>>>>> b276d09d85d858fb5c69652187615b034bbaf50f
