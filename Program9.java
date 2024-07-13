class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
class IntervalTreeNode {
    Interval interval;
    int max;
    IntervalTreeNode left;
    IntervalTreeNode right;

    IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.max = interval.end;
        this.left = null;
        this.right = null;
    }
}

import java.util.ArrayList;
import java.util.List;
public class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTree() {
        root = null;
    }

    public void insertInterval(int start, int end) {
        root = insert(root, new Interval(start, end));
    }

    private IntervalTreeNode insert(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return new IntervalTreeNode(interval);
        }

        int low = node.interval.start;
        if (interval.start < low) {
            node.left = insert(node.left, interval);
        } else {
            node.right = insert(node.right, interval);
        }

        if (node.max < interval.end) {
            node.max = interval.end;
        }

        return node;
    }

    public void deleteInterval(int start, int end) {
        root = delete(root, new Interval(start, end));
    }

    private IntervalTreeNode delete(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return null;
        }

        if (interval.start < node.interval.start) {
            node.left = delete(node.left, interval);
        } else if (interval.start > node.interval.start) {
            node.right = delete(node.right, interval);
        } else if (interval.start == node.interval.start && interval.end == node.interval.end) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                IntervalTreeNode minNode = findMin(node.right);
                node.interval = minNode.interval;
                node.right = delete(node.right, minNode.interval);
            }
        } else {
            node.right = delete(node.right, interval);
        }

        if (node != null) {
            node.max = Math.max(node.interval.end, Math.max(getMax(node.left), getMax(node.right)));
        }

        return node;
    }

    private IntervalTreeNode findMin(IntervalTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int getMax(IntervalTreeNode node) {
        return node == null ? Integer.MIN_VALUE : node.max;
    }

    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlapping(root, new Interval(start, end), result);
        return result;
    }

    private void findOverlapping(IntervalTreeNode node, Interval interval, List<Interval> result) {
        if (node == null) {
            return;
        }

        if (isOverlapping(node.interval, interval)) {
            result.add(node.interval);
        }

        if (node.left != null && node.left.max >= interval.start) {
            findOverlapping(node.left, interval, result);
        }

        findOverlapping(node.right, interval, result);
    }

    private boolean isOverlapping(Interval i1, Interval i2) {
        return i1.start <= i2.end && i2.start <= i1.end;
    }

    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();

        // Insert intervals
        tree.insertInterval(15, 20);
        tree.insertInterval(10, 30);
        tree.insertInterval(17, 19);
        tree.insertInterval(5, 20);
        tree.insertInterval(12, 15);
        tree.insertInterval(30, 40);

        // Find overlapping intervals
        System.out.println("Overlapping intervals with [14, 16]: " + tree.findOverlappingIntervals(14, 16));
        System.out.println("Overlapping intervals with [21, 23]: " + tree.findOverlappingIntervals(21, 23));

        // Delete an interval and find again
        tree.deleteInterval(10, 30);
        System.out.println("Overlapping intervals with [14, 16] after deleting [10, 30]: " + tree.findOverlappingIntervals(14, 16));
    }
}
