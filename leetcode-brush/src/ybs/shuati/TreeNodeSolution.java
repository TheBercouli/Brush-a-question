package ybs.shuati;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public int maxDepth104(TreeNode root) {
        if (root == null)
            return 0;

        int leftMax = maxDepth104(root.left);
        int rightMax = maxDepth104(root.right);

        return Math.max(leftMax,rightMax) + 1; // +1是此时的根节点

    }

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

     */
    public TreeNode invertTree226(TreeNode root) {
        if (root == null)
            return null;
        invertTree226(root.left);
        invertTree226(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;

    }

    /**
     *100 相同树
     给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

     如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public boolean isSameTree100(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;
        else if (p.val != q.val)
            return false;
        else
            return isSameTree100(p.left, q.left) && isSameTree100(p.right, q.right);
    }

    /**
     * 112. 路径总和
     * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
     * 如果存在，返回 true ；否则，返回 false 。
     *
     * 叶子节点 是指没有子节点的节点。
     */
    public boolean hasPathSum112(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        // 注意 根节点到叶子节点 才算路径，因此不能使用root==null return target == 0;
        if (root.left == null && root.right == null)
            return root.val == targetSum;

        if (hasPathSum112(root.left, targetSum - root.val))
            return true;
        if (hasPathSum112(root.right, targetSum - root.val))
            return true;

        return false;
    }
    // 222 110
    // 111 404

    public List<String> binaryTreePaths257(TreeNode root) {

        // 递归结束条件要思考
        ArrayList<String> res = new ArrayList<>();
        if (root == null)
            return res;
        if (root.left == null && root.right == null)
            res.add(String.valueOf(root.val));

        // 怎么说呢：思路就是咱们确定好了递归的语义，
        // 只需要处理一下：最终的子问题，并脑中建模递归结束可能出现的状态如果有参差需得解决
        // 一般来说解决最终的最小子问题就可以得到正确结果
        List<String> leftStr = binaryTreePaths257(root.left);
        for (String s : leftStr) {
            res.add(root.val + "->" + s);
        }
        List<String> rightStr = binaryTreePaths257(root.right);
        for (String s : rightStr) {
            res.add(root.val + "->" + s);
        }
        return res;
    }
    // 113 129

    /**
     * 437:路径总和3
     * 给定一个二叉树的根节点 root，和一个整数 targetSum
     * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     *
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）
     *
     */
    // 找到一个节点下所有满足要求和的路径
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        // 以下是解决最小问题，一个TreeNode有三个节点，root left right，因此如下操作
        int res = findPathCount(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    // 找到以根节点开始，所有满足要求和的路径
    private int findPathCount(TreeNode root, long targetSum) {
        // 由于不需要在叶子节点结束
        if (root == null)
            return 0;
        int res = 0;
        if (root.val == targetSum) // 解决最小问题
            res += 1; // 这里不能直接返回，因为节点值有负数，可能会出现ABA，也算路径
        res += findPathCount(root.left, targetSum - root.val);
        res += findPathCount(root.right, targetSum - root.val);
        return res;

    }
    /*
    Map<Long,Integer> mem = new HashMap<Long,Integer>();//保存前缀树
    int target;
    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        mem.put(0L,1);//前缀树为0的个数至少是一个
        return dfs(root,0);
    }
    public int dfs(TreeNode root,long curSum){
        if(root == null) return 0;
        curSum += root.val;//得到当前前缀树的值
        int res = 0;
        res = mem.getOrDefault(curSum-target,0);//得到我们想要前缀树的个数，想要前缀树值就是当前前缀树值减去目标值
        mem.put(curSum,mem.getOrDefault(curSum,0)+1);//将当前前缀树的值保存
        int left = dfs(root.left,curSum);//遍历左边
        int right = dfs(root.right,curSum);//遍历右边
        mem.put(curSum,mem.get(curSum)-1);//防止左边前缀树影响右边前缀树，左边前缀树可能有个为6，右边正好想要一个前缀树为6的，这样子就出错了
        return res+left+right;//结果是当前节点前缀树的个数加上左边满足的个数加右边满足的个数
    }
     */

    /**
     * 235:二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
     */
    public TreeNode lowestCommonAncestor235(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root.val > q.val && root.val > p.val) // 此时pq在root的左子树中
            return lowestCommonAncestor235(root.left, p, q);
        if (root.val < q.val && root.val < p.val) // 此时pq在root的右子树中
            return lowestCommonAncestor235(root.right, p, q);

        // 否则,p和q分散在root的左右两颗子树中,此时直接返回根节点就好,
        // 注意是p q的公共祖先，光是p的不行，因此直接返回root就好
        return root;
    }

    /**
     * 98:判断一棵树是不是BST
     * // 忽略了记录节点val的问题
     * 除了下面的代码，还有一种思路就是根据BST的中序遍历会得到递增结果，那么访问到后排序严格递增即可
     */
    long temp = Long.MIN_VALUE;
    public boolean isValidBST98v1(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST98v1(root.left))
            return false;
        if (root.val <= temp)
            return false;
        temp = root.val;
        return isValidBST98v1(root.right);
    }
    TreeNode pre98v3=null;
    public boolean isValidBST98v3(TreeNode root) {
        if(root==null) return true;
        if(!isValidBST98v3(root.left)) return false;
        //这里用于判断是不是找到最左边的节点了，如果是就不用比较
        if(pre98v3==null) pre98v3=root;
            //如果不是就比较这个节点和其左节点
        else if(root.val<=pre98v3.val) return false;
        pre98v3=root; // 相当于回溯到根节点，然后下一行代码去寻找根节点的右节点，因为此时已经确定了左树
        return isValidBST98v3(root.right);
    }
    // 这里就是直接的思路，假设最大最小值，然后依次递归，左子树的最大值变成根的值，右子树的最小值变为根的值
    public boolean isValidBST98v2(TreeNode root) {
        return validate98v2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean validate98v2(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return  validate98v2(node.left, min, node.val) && validate98v2(node.right, node.val, max);
    }
    // 450 230 236 109（有序链表转BST）


}
