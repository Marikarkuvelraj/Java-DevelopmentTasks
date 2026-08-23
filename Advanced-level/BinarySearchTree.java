import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

public class BinarySearchTree {

    TreeNode root;

    
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private TreeNode insertNode(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }

        if (data < root.data) {
            root.left = insertNode(root.left, data);
        } else if (data > root.data) {
            root.right = insertNode(root.right, data);
        }

        return root;
    }

    
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(TreeNode root, int data) {
        if (root == null) {
            return false;
        }

        if (root.data == data) {
            return true;
        }

        if (data < root.data) {
            return searchNode(root.left, data);
        }

        return searchNode(root.right, data);
    }

    
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private TreeNode deleteNode(TreeNode root, int data) {
        if (root == null) {
            return null;
        }

        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } else {
            // Node with no child or one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children
            root.data = findMin(root.right);
            root.right = deleteNode(root.right, root.data);
        }

        return root;
    }

    private int findMin(TreeNode root) {
        int min = root.data;

        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }

        return min;
    }

    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    
    public void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        
        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values) {
            bst.insert(value);
        }

        System.out.println("===== BINARY SEARCH TREE =====");

        System.out.print("In-order Traversal: ");
        bst.inorder(bst.root);
        System.out.println();

        System.out.print("Pre-order Traversal: ");
        bst.preorder(bst.root);
        System.out.println();

        System.out.print("Post-order Traversal: ");
        bst.postorder(bst.root);
        System.out.println();

        System.out.print("\nEnter a value to search: ");
        int searchValue = scanner.nextInt();

        if (bst.search(searchValue)) {
            System.out.println(searchValue + " found in the BST.");
        } else {
            System.out.println(searchValue + " not found in the BST.");
        }

        System.out.print("\nEnter a value to delete: ");
        int deleteValue = scanner.nextInt();

        bst.delete(deleteValue);

        System.out.print("In-order Traversal after deletion: ");
        bst.inorder(bst.root);

        scanner.close();
    }
}