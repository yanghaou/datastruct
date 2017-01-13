package avlTree;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/1
 */
public class AvlTree<T extends Comparable<? super T>> {
    private class AVLNode<T>{
        AVLNode<T> left;//左节点
        AVLNode<T> right;//右节点
        int height;//高度
        T data;//数据

        public AVLNode(T data) {
            this(null,null,data);
        }

        public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
            this.left = left;
            this.right = right;
            this.data = data;
            this.height = 0;
        }
    }

    //根节点
    private AVLNode<T> root ;

    public AvlTree() {
        this.root = null;
    }

    //插入数据，重复数据忽略
    public void insert(T data){
        root = insert(root,data);
    }

    //递归插入
    private AVLNode<T> insert(AVLNode<T> root, T data) {
        //根为空
        if (root == null){
            root = new AVLNode<T>(data);
        }

        int result = data.compareTo(root.data);
        if (result<0){
            root.left = insert(root.left,data);
            if (height(root.left) - height(root.right) == 2){//打破平衡
                if (data.compareTo(root.left.data) <0){//LL型
                    root = rotateWithLeftChild(root);
                }else {
                    //LR型
                    root = doubleWithLeftChild(root);
                }
            }
        }else if (result >0){
            root.right = insert(root.right,data);
            if (height(root.right) - height(root.left) ==2){//打破平衡
                if (data.compareTo(root.right.data) >0){
                    //RR型
                    root = rotateWithRightChild(root);
                }else {
                    //RL型
                    root = doubleWithRightChild(root);
                }
            }
        }
        root.height = Math.max(height(root.left),height(root.right)) + 1;
        return root;
    }

    //带左子树旋转,适用于LL型
    private AVLNode< T> rotateWithLeftChild(AVLNode< T> k2 ) {
        AVLNode< T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }
    //双旋转，适用于LR型
    private AVLNode< T> doubleWithLeftChild(AVLNode< T> k3 ) {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    //带右子树旋转，适用于RR型
    private AVLNode< T> rotateWithRightChild(AVLNode<T> k1 ) {
        AVLNode< T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height( k1.left ), height( k1.right )) + 1;
        k2.height = Math.max(height( k2.right ), k1.height) + 1;
        return k2;
    }
    //双旋转,适用于RL型
    private AVLNode< T> doubleWithRightChild(AVLNode< T> k1 ) {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    //求树高度
    public int height(AVLNode<T> node){
        return node == null? -1 : node.height;
    }
    //删除数据
    public void delete(T data){
        root = delete(root,data);
    }


    /**
     * remove: 删除avl树中的节点
     * @param node    要删除的节点所在的根节点
     * @param value   要删除的value
     * @return
     * AVLNode  返回类型
     */
    private AVLNode<T> delete(AVLNode<T> node, T value) {
        //没有找到 do nothing
        if (node == null || value == null)
            return null;
        int cmp = value.compareTo(node.data);
        if (cmp < 0) { // 待删除的节点在"node的左子树"中
            node.left = delete(node.left, value);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(node.right) - height(node.left) == 2) {
                AVLNode<T> delNode = node.right;
                if (height(delNode.left) > height(delNode.right))
                    node = rotateWithLeftChild(node);//LL
                else
                    node = doubleWithLeftChild(node);//LR
            }
        } else if (cmp > 0) { // 待删除的节点在"tree的右子树"中
            node.right = delete(node.right, value);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(node.left) - height(node.right) == 2) {
                AVLNode<T> delNode = node.left;
                if (height(delNode.right) > height(delNode.left))
                    node = rotateWithRightChild(node);//RR
                else
                    node = doubleWithRightChild(node);//RL
            }
        } else if ((node.left != null) && (node.right != null)) {
            node.data = findMin(node.right).data;
            node.right = delete(node.right,node.data);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }

        if (node !=null)
            node.height = Math.max(height(node.left),height(node.right)) + 1;
        return node;
    }
    /**查询出最小元素所在的结点*/
    public AVLNode<T> findMin(AVLNode<T> node) {
        if(node==null)
            return null;
        else if(node.left==null)
            return node;
        return findMin(node.left);//递归查找
    }

    /**查询出最大元素所在的结点*/
    public AVLNode<T> findMax(AVLNode<T> node) {
        if(node!=null)
        {
            while(node.right!=null)
                node=node.right;
        }
        return node;
    }
    public static void main(String [ ] args ) {
        AvlTree< Integer> t = new AvlTree<Integer>( );

        int []a={3,2,1,4,5,6,7,10,9,8};
        for (int i =0;i<a.length;i++)
            t.insert(a[i]);
        //t.delete(4);
        t.delete(10);
        t.delete(1);
        t.delete(3);
        t.printTree(t.root);
        System.out.println("输的高度="+t.height(t.root));

    }

    //中序遍历
    public void printTree(AVLNode<T> root) {
        if (root !=null){
            System.out.println(root.data);
            printTree(root.left);
            printTree(root.right);
        }
    }
}
