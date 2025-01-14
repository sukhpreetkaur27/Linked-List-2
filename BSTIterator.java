import java.util.ArrayDeque;
import java.util.Deque;

// LC 173

/**
 * BST Iterator Brute Force:
 * <p>
 * Store the Inorder traversal in a list and increment an index pointer on eac call to next.
 * <p>
 * Ctr TC: O(n)
 * SC: O(n)
 * <p>
 * next() and hasNext() TC: O(1)
 */

/**
 * BST Iterator == Lazy Loading
 *
 * To optimize the space from O(n) to O(log n) --> just load the left leg
 *
 * this results in an average TC for the operations to O(1)
 *
 * SC: O(h)
 */
public class BSTIterator {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * SC: O(H)
     */
    private final Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        loadLeftLeg(root);
    }

    private void loadLeftLeg(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /**
     * Average TC: O(1)
     * @return
     */
    public int next() {
        TreeNode next = null;
        if (!stack.isEmpty()) {
            next = stack.pop();
            loadLeftLeg(next.right);
        }
        return next != null ? next.val : Integer.MIN_VALUE;
    }

    /**
     * TC: O(1)
     * @return
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

}
