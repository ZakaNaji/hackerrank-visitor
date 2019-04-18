package VisitorPattern;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

import VisitorPattern.Tree.Tree;
import VisitorPattern.TreeVis.FancyVisitor;
import VisitorPattern.TreeVis.ProductOfRedNodesVisitor;
import VisitorPattern.TreeVis.SumInLeavesVisitor;


public class Solution {

    private static Color[] colors;
    private static int[] from;
    private static int[] to;
    private static int n;

    public static Tree solve() {
        Tree root;

        return null;
    }

    public static void readTree(Scanner scanner){
        n = scanner.nextInt(); //Number of Nodes
        scanner.nextLine();//Go to next line
        int[] values = SolutionUtils.filValues(scanner.nextLine());
        Color[] colors = SolutionUtils.filColors(scanner.nextLine());

    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

    public static Color[] getColors() {
        return colors;
    }

    public static int[] getFrom() {
        return from;
    }

    public static int[] getTo() {
        return to;
    }

    public int getN() {
        return n;
    }
}

class SolutionUtils{
    public static int[] filValues(String values){
        String[] arr = values.split(" ");
        int[] vals = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            vals[i] = Integer.valueOf(arr[i]);
        }
        return vals;
    }
    public static Color[] filColors(String colors){
        String[] arr = colors.split(" ");
        Color[] cols = new Color[arr.length];
        for (int i = 0; i < arr.length; i++){
            byte col = Byte.valueOf(arr[i]);
            cols[i] = (col == 0)?Color.RED:Color.GREEN;
        }
        return cols;
    }
}