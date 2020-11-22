## 刷题心得总结

**任何问题都要注意它的特殊情况**

**注意他给的条件，派除边缘特殊情况之后再进行正式的解答**

#### 1）树相关问题基本都可用“递归”来解决，

#### 2）树相关问题除了递归之外，还可用“迭代”的方法解决

   迭代的实现可通过：栈 / 队列（Deque-双端队列可实现）

#### 3）对于涉及到比大小的环节，可借用Long.MIN_VALUE / Long.MAX_VALUE，这样可以使循环或递归更加“顺畅”。

   代码举例如下：

```java
 public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        //不断检测当前节点的值是否在合理范围内
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
```

#### 4）对关于树的几个经典问题总结

**（1）树的先、中遍历** **迭代实现与递归实现的比对**

​     **迭代实现：**
   
​    迭代这里是深度遍历，**栈实现的。（Deque可实现，单头进、出）**

​    栈实现的DFS深度优先搜索，是有**从下往上（找到叶子节点从叶子节点开始）的过程**，

​    所以**需要不断地判断当前根节点root的状态**，来决定是否要继续往下探索。

​     **递归实现：**
      
​     **<img src="https://img2020.cnblogs.com/blog/1919232/202011/1919232-20201121174115777-1488430412.png" alt="img" style="zoom: 50%;" />**

​    **用递归解决二叉树的遍历相关问题的话，一般都会在递归函数前面创建一个集合，用来存储将最后的返回结果。这个集合会作为参数带到递归函数中。**

**（2）树的层序遍历**

​     N叉树的层序遍历是要实现按照层来存储节点的，借助的是**队列（Deque可实现，一头进，另一头出）**。

​     <img src="https://img2020.cnblogs.com/blog/1919232/202011/1919232-20201121180331844-965660324.png" alt="img" style="zoom:50%;" />

​     队列实现BFS广度优先搜索，遵循**先进先出**的原则。

​     介于需要先判断root是否为空，判断不为空后，直接将root加入队列中是比较清晰简洁的，所以，感觉用队列实现根节点的先遍历的操作比较适合。

​     eg: BFS可用于实现 **二叉树的先序遍历、树的层序遍历。**

​     队列实现的话，**需要判断当前队列的状态-.isEmpty()。**

​    不为空则出队列，再针对出队列的节点进行左右节点的入队操作，同时根据题目需求 进行节点的状态判断相关的处理操作。

**（3）二叉树的反序列化**

​     ![img](https://img2020.cnblogs.com/blog/1919232/202011/1919232-20201121181711680-1882388649.png)

​     对于实现二叉树的反序列化问题，如果采用递归的方式实现的话，递归函数应是返回TreeNode类型数据的，

​     这是因为这里递归函数的作用是：根据传入的数据集，例如队列，通过出队，找到对应的节点，而节点的类型就是TreeNode的，

​     只有这样，通过递归调用，才能不断地组建出一棵树。

​     **一般构造二叉树问题，递归解法的递归方法的返回值都是TreeNode的。**

#### 5）模板代码-回溯

Subsets-子集问题-78                               

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsetsHelper(nums, new ArrayList<>(), 0, res);
    return res;
}
private void subsetsHelper(int[] nums, List<Integer> deque, int index, List<List<Integer>> res) {
    //走过的所有路径都是子集的一部分，都要加入到结果集合中
    res.add(new ArrayList<>(deque));
    for (int i = index; i < nums.length; i++) {
        deque.add(nums[i]);
        subsetsHelper(nums, deque, i + 1, res);
        deque.remove(deque.size() - 1); //这里ArrayList的remove参数是index，实现尾元素的删除
    }
}
```

Combinations-组合问题-77

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if (k <= 0 || n < k) {
        return res;
    }
    Deque<Integer> path = new ArrayDeque<>();
    dfs(n, k, 1, path, res);
    return res;
}
private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
    // 递归终止条件是：path 的长度等于 k
    if (path.size() == k) {
        res.add(new ArrayList<>(path));
        return;
    }
    // 如果path没满
    for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
        path.addLast(i);
        // 下一轮搜索，设置的搜索起点要加 1，因为组合数里不允许出现重复的元素
        dfs(n, k, i + 1, path, res);
        // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
        path.removeLast();
    }
}
```
