package Binary_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Binary_Search_Tree {

    public static void main(String[] args) {
        Binary_Search_Tree tree = new Binary_Search_Tree();

        /*
                For at lave det ønskede træ skal der bruges tree.insert på noderne i level-order rækkefølge. Altså gå fra venstre mod højre lag for lag.
         */

        tree.insert(22);
        tree.insert(10);
        tree.insert(36);
        tree.insert(8);
        tree.insert(15);
        tree.insert(26);
        tree.insert(40);
        tree.insert(6);
        tree.insert(11);
        tree.insert(24);
        tree.insert(28);
        tree.insert(45);
        tree.insert(2);
        tree.insert(7);
        tree.insert(13);
        tree.insert(27);
        tree.insert(30);
        tree.insert(48);
        tree.insert(12);
        tree.insert(14);
        tree.insert(29);
        tree.insert(32);
        tree.insert(46);
        tree.insert(50);


        tree.printTree();
        System.out.println("Is the tree minimal height: " + tree.minimalHeight());
        System.out.println("\n");

        System.out.println("Number of branches: " + tree.countTwigs());
        System.out.println("\n");

        System.out.println("Tree height: " + tree.getHeight());
        System.out.println("\n");

        System.out.println("Internal path length: " + tree.getInternalPathLength());
        System.out.println("\n");

        System.out.println("Number of nodes: " + tree.getNumOfNodes());
        System.out.println("\n");

        System.out.println("Maximum value in the tree: " + tree.findMax());
        System.out.println("\n");

        System.out.println("Is the tree a valid BST? " + tree.isValidBST());
        System.out.println("\n");

        System.out.println("Number of leaf nodes: " + tree.countLeaves());
        System.out.println("\n");

        System.out.println("Level-order traversal: ");
        tree.levelOrderTraversal();
        System.out.println("\n");

        System.out.println("In-order traversal: ");
        tree.inOrderTraversal();
        System.out.println("\n");

        System.out.println("Pre-order traversal: ");
        tree.preOrderTraversal();
        System.out.println("\n");

        System.out.println("Post-order traversal: ");
        tree.postOrderTraversal();
        System.out.println("\n");
    }

    private Node root;
    private int numOfNodes = 0;

    public Binary_Search_Tree() {
        root = null;
    }

    public void insert(int idata) {
        root = insert(root, idata);
    }

    public int getHeight() {
        return getHeight(root);
    }

    public int countTwigs() {
        return countTwigs(root);
    }

    public int getInternalPathLength() {
        return getInternalPathLength(root, 0);
    }

    public void printTree() {
        BTreePrinter.printNode(root);
    }

    public int getNumOfNodes() {
        return numOfNodes;
    }

    public boolean minimalHeight() {
        if (numOfNodes == 1) {
            return true;
        }

        int minHeight = 1;
        int compareNum = 1;
        while (true) {
            minHeight++;
            compareNum += (int) Math.pow(minHeight, minHeight - 1);
            if (compareNum >= numOfNodes) {
                break;
            }
        }

        return minHeight == getHeight();
    }

    private Node insert(Node root, int idata) {
        if (root == null) {
            numOfNodes += 1;  // Increment only when a new node is created
            return new Node(idata);
        }

        if (idata < root.data) {
            root.left = insert(root.left, idata);
        } else if (idata > root.data) {
            root.right = insert(root.right, idata);
        }

        return root;
    }

    private int countTwigs(Node node) {
        if (node == null) {
            return 0;
        }

        Node twig = null;
        Node parent = null;
        Node grantParent = node;

        if (grantParent.left == null && grantParent.right != null) {
            parent = grantParent.right;
        } else if (grantParent.left != null && grantParent.right == null) {
            parent = grantParent.left;
        }

        if (parent != null) {
            if (parent.left == null && parent.right != null) {
                twig = parent.right;
            } else if (parent.left != null && parent.right == null) {
                twig = parent.left;
            }
        }

        if (twig != null) {
            if (twig.left != null && twig.right != null &&
                    twig.left.left == null && twig.left.right == null &&
                    twig.right.left == null && twig.right.right == null) {
                return 1 + countTwigs(node.left) + countTwigs(node.right);
            }
        }

        return countTwigs(node.left) + countTwigs(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int getInternalPathLength(Node node, int depth) {
        if (node == null) {
            return 0;
        }

        return depth + getInternalPathLength(node.left, depth + 1) +
                getInternalPathLength(node.right, depth + 1);
    }

    // Find maximum value in the tree
    public int findMax() {
        return findMaxRec(root);
    }

    private int findMaxRec(Node root) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    // Level order traversal
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    // In-order traversal
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }

    // Pre-order traversal
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    // Post-order traversal
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    // Validate if the tree is a BST
    public boolean isValidBST() {
        return isValidBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTRec(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        }
        return isValidBSTRec(node.left, min, node.data) && isValidBSTRec(node.right, node.data, max);
    }

    // Count the number of leaf nodes
    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    // Mirror the tree
    public void mirrorTree() {
        root = mirrorTreeRec(root);
    }

    private Node mirrorTreeRec(Node node) {
        if (node == null) {
            return null;
        }

        Node left = mirrorTreeRec(node.left);
        Node right = mirrorTreeRec(node.right);

        node.left = right;
        node.right = left;

        return node;
    }

    static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int idata) {
            data = idata;
            left = null;
            right = null;
        }
    }

    static class BTreePrinter {
        public static <T extends Comparable<?>> void printNode(Node root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node> newNodes = new ArrayList<Node>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <T extends Comparable<?>> int maxLevel(Node node) {
            if (node == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }
    }
}
