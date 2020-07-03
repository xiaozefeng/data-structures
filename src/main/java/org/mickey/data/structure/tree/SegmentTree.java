package org.mickey.data.structure.tree;

/**
 * @author mickey
 * @date 2020/7/2 19:26
 */
public class SegmentTree<E> {

    private final E[] data;
    private final E[] tree;

    private final Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        //noinspection unchecked
        data = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);

        //noinspection unchecked
        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在 treeIndex 位置创建 [l...r]区间的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 如果左边界和右边界一样，递归到底了
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length
                || queryR < 0 || queryR >= data.length
                || queryL > queryR)
            throw new IllegalArgumentException("index is illegal");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以 treeIndex 为根的线段树中[l...r]区间范围里，查找区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query((leftTreeIndex), l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }


    public void set(int index, E e) {
        if (index <0 || index>data.length)
            throw new IllegalArgumentException("the index illegal.");

        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为跟的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l==r){
            tree[treeIndex] =e ;
            return;
        }

        int mid = l + (r-l)/2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid+1)
            set(rightTreeIndex, mid+1, r, index,e);
        else
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");

        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                sb.append(tree[i]);
            else
                sb.append("null");

            if (i != tree.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, Integer::sum);
        System.out.println(segmentTree);

        System.out.println(segmentTree.query(0,2));
        System.out.println(segmentTree.query(0,5));
        System.out.println(segmentTree.query(2,5));
    }
}
