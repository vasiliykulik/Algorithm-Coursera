package w4binarysearchtrees;

/**
 * Created by Vasiliy Kylik on 15.11.2017.
 */

    // Java program for different tree traversals

    /* Class containing left and right child of current
       node and key value*/
    class NodeT
    {
        int key;
        NodeT left, right;

        public NodeT(int item)
        {
            key = item;
            left = right = null;
        }
    }

    class TreeTraversals
    {
        // Root of Binary Tree
        NodeT root;

        TreeTraversals()
        {
            root = null;
        }

        /* Given a binary tree, print its nodes according to the
          "bottom-up" postorder traversal. */
        void printPostorder(NodeT node)
        {
            if (node == null)
                return;

            // first recur on left subtree
            printPostorder(node.left);

            // then recur on right subtree
            printPostorder(node.right);

            // now deal with the node
            System.out.print(node.key + " ");
        }

        /* Given a binary tree, print its nodes in inorder*/
        void printInorder(NodeT node)
        {
            if (node == null)
                return;

        /* first recur on left child */
            printInorder(node.left);

        /* then print the data of node */
            System.out.print(node.key + " ");

        /* now recur on right child */
            printInorder(node.right);
        }

        /* Given a binary tree, print its nodes in preorder*/
        void printPreorder(NodeT node)
        {
            if (node == null)
                return;

        /* first print data of node */
            System.out.print(node.key + " ");

        /* then recur on left sutree */
            printPreorder(node.left);

        /* now recur on right subtree */
            printPreorder(node.right);
        }

        // Wrappers over above recursive functions
        void printPostorder()  {     printPostorder(root);  }
        void printInorder()    {     printInorder(root);   }
        void printPreorder()   {     printPreorder(root);  }

        // Driver method
        public static void main(String[] args)
        {
            TreeTraversals tree = new TreeTraversals();
            tree.root = new NodeT(1);
            tree.root.left = new NodeT(2);
            tree.root.right = new NodeT(3);
            tree.root.left.left = new NodeT(4);
            tree.root.left.right = new NodeT(5);

            System.out.println("Preorder traversal of binary tree is ");
            tree.printPreorder();

            System.out.println("\nInorder traversal of binary tree is ");
            tree.printInorder();

            System.out.println("\nPostorder traversal of binary tree is ");
            tree.printPostorder();
        }
    }

