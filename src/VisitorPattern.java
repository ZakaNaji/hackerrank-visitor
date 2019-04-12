import java.util.ArrayList;
import java.util.*;

import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int result;

    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private int result = 1;
    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
        if(node.getColor() == Color.RED) result *= node.getValue()%(Math.pow(10,9)+7);
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED) result *= leaf.getValue()%(Math.pow(10,9)+7);
    }
}

class FancyVisitor extends TreeVis {
    private int evenNodes;
    private int greenLeafNode;
    public int getResult() {
        return Math.abs(evenNodes - greenLeafNode);
    }

    public void visitNode(TreeNode node) {
        if(node.getDepth()%2 == 0) evenNodes += node.getValue();
        if(node.getColor() == Color.GREEN) greenLeafNode += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.GREEN) greenLeafNode += leaf.getValue();
    }
}

class VisitorPatternUtils{
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

public class VisitorPattern {

    public static Tree solve() {
        Scanner scanner = new Scanner(System.in);
        int nodesNumber = scanner.nextInt();scanner.nextLine();//go to next line
        int[] values = VisitorPatternUtils.filValues(scanner.nextLine());//GETTING THE VALUES
        Color[] colors = VisitorPatternUtils.filColors(scanner.nextLine());//GETTING THE COLORS
        List<Integer> from = new ArrayList<>(nodesNumber-1);
        List<Integer> to = new ArrayList<>(nodesNumber-1);
        fillFromAndTo(scanner, nodesNumber, from, to);
        Map<Integer,Tree> nodes = new HashMap<>();
        Tree root = new TreeNode(values[from.get(0)],colors[from.get(0)],0);
        Integer rootKey = from.get(0);
        nodes.put(rootKey,root);
        for (int i=0; i<from.size(); i++){
            Tree tempTreeNodeValue = nodes.get(from.get(i));
            Integer tempTreeNodeKey = from.get(i);
            if(isNode(to.get(i),from)){
                Tree tempTreeChildNodeValue = new TreeNode(values[to.get(i)],colors[to.get(i)],tempTreeNodeValue.getDepth()+1);
                Integer tempTreeChildNodeKey = to.get(i);
                ((TreeNode) tempTreeNodeValue).addChild(tempTreeChildNodeValue);
                nodes.put(tempTreeChildNodeKey,tempTreeChildNodeValue);
            }else{
                Tree tempTreeChildNodeValue = new TreeLeaf(values[to.get(i)],colors[to.get(i)],tempTreeNodeValue.getDepth()+1);
                ((TreeNode) tempTreeNodeValue).addChild(tempTreeChildNodeValue);
            }
        }
        return root;
    }

    private static void fillFromAndTo(Scanner scanner, int nodesNumber, List<Integer> from, List<Integer> to) {
        for(int i=0; i < nodesNumber-1; i++){
            String[] fromToArray = scanner.nextLine().split(" ");
            from.add(Integer.valueOf(fromToArray[0])-1);
            to.add(Integer.valueOf(fromToArray[1])-1);
        }
    }

    private static boolean isNode(Integer integer, List<Integer> from) {
        return from.contains(integer);
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
}