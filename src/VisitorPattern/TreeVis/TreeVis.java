package VisitorPattern.TreeVis;

import VisitorPattern.Tree.TreeLeaf;
import VisitorPattern.Tree.TreeNode;

public abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}
