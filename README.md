## BFS、双向BFS

**单词接龙-127** 和 **最小基因变化-433** 的解题思路相似, **BFS 和 双向BFS的模板**(基于单词接龙的) 如下:

#### **1) BFS模板:**

```java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // 第 1 步：将 wordList 放到哈希表里
    Set<String> wordSet = new HashSet<>(wordList);
    //注意特况
    if (wordList.size() == 0 || !wordList.contains(endWord)) {
        return 0;
    }
    //第 2 步：必须使用队列（先进先出）和 表示是否访问过的 visited 哈希表
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    Set<String> visited = new HashSet<>();
    // 第 3 步：开始广度优先遍历，根据题目要求设置返回值的初始值
    int step = 1;
    while (!queue.isEmpty()) {
        int currentSize = queue.size();
        while (currentSize-- > 0) {
            String currentWord = queue.poll();
            char[] charArray = currentWord.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char originChar = charArray[i];
                for (char k = 'a'; k <= 'z'; k++) {
                    if (k == originChar) {
                        continue;
                    }
                    charArray[i] = k;
                    String nextWord = String.valueOf(charArray);
                    if (wordList.contains(nextWord)) {
                        if (nextWord.equals(endWord)) {
                            return step + 1;
                        }
                        if (!visited.contains(nextWord)) {
                            queue.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
                charArray[i] = originChar;
            }
        }
        step++;
    }
    return 0;
}
```

#### **2) 双向BFS模板:**

```java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // 第 1 步：将 wordList 放到哈希表里       
    Set<String> wordSet = new HashSet<>(wordList);
    if (wordList.size() == 0 || !wordList.contains(endWord)) {
        return 0;
    }
    //2.已经访问过的 word 添加到 visited 哈希表里
    Set<String> visited = new HashSet<>();
    Set<String> beginVisited = new HashSet<>();
    beginVisited.add(beginWord);
    Set<String> endVisited = new HashSet<>();
    endVisited.add(endWord);
    // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
    int step = 1;
    while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
        if (beginVisited.size() > endVisited.size()) {
            Set<String> tmp = beginVisited;
            beginVisited = endVisited;
            endVisited = tmp;
        }
        Set<String> nextLevelVisited = new HashSet<>();
        for (String word : beginVisited) {
            char[] charArray = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                char originChar = charArray[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originChar) {
                        continue;
                    }
                    charArray[i] = c;
                    String nextWord = String.valueOf(charArray);
                    if (wordSet.contains(nextWord)) {
                        if (endVisited.contains(nextWord)) {
                            return step + 1;
                        }
                        if (!visited.contains(nextWord)) {
                            nextLevelVisited.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
                charArray[i] = originChar;
            }
        }
        beginVisited = nextLevelVisited;
        step++;
    }
    return 0;
}
```

## 高级树、AVL 树和红黑树

#### 1.回顾

###### 1）树 Tree

  链表(Linked List) 是特殊化的 树(Tree)，树 是特殊化的 图(Graph)。

###### 2）二叉树 Binary Tree

  是指树中节点的度**不大于2**的有序树，它是一种最简单且最重要的树。

  **二叉树的前中后序遍历的** **时间复杂度 均是O(n)** 

  **二叉树的前中后序遍历**

###### 3）二叉搜索树（BST-Binary Search Tree）

​    是指一棵空树 或者 具有以下性质的二叉树：

​    左子树上 所有节点的值均小于 它的根节点上的值；

​    右子树上 所有节点的值均大于 它的根节点上的值；

​    以此类推，左右子树也分别为二叉搜索树。

​    二叉搜索树除此之外还有一条性质：**中序遍历为升序排列的。**

​    **二叉搜索树的查询、删除、插入节点的时间复杂度均为：O(log n)**

​    保持平衡 --> 保证二维维度、左右子树的节点平衡 --> **AVL树**

#### 2.AVL树

1）**Balance Factor（平衡因子）：** 是它的**左子树的高度**减去它的**右子树的高度**（有时相反）。 要保持的取       值可能范围： balance factor = {-1, 0, 1}

**平衡因子的由来** 是基于树的查询的时间复杂度，和树的深度的有关，也就能够想到树的高度差，为使树是平衡的 引入了平衡因子，

2）通过**旋转操作**来进行平衡（四种）：右右子树-->左旋、左左子树-->右旋、左右子树-->左右旋、右左子树-->右左旋、（带子树的旋转）。

   AVL用来平衡二叉搜索树，每个节点存有平衡因子 balance factor = {-1, 0, 1}。

3）不足：节点需要存储额外信息且调整频繁。

#### 3.红黑树（Red-black Tree）

红黑树是一种**近似平衡的二叉树**，能够确保任何节点的左右子树的**高度之差小于2倍**。

具体来说，红黑树是满足如下条件的二叉搜索树：

- 每个节点要么是红色要么是黑色；
- 根节点是黑色的；
- 每个叶子节点是黑色的；
- 不能有相邻的两个红色节点；
- 从任意节点到其每个叶子的所有路径，均包含相同个数的黑色节点。

从根到叶子的最长的可能路径，不会多于 最短的可能路径的 两倍长。

#### 4.AVL和Red-black Tree的区别：

**AVL树比红黑树提供了更快速的查询**，因为AVL树是更加**严格平衡**的。

**红黑树提供了更快的插入删除操作，**因为AVL的旋转操作更多，而红黑树是一个近似平衡的二叉树，添加和删除会更快。

**AVL树**是要存入factors / heights更多，**需要更多的内存**来存储更多额外的信息；**红黑树仅**需要存储是黑还是红，因此**需要的内存会更小**。

如果对读写的需求差别不大，推荐使用红黑树，因为它更容易实现。

高级语言的库像 map / set 基本上都是应用的红黑树 ; 而对于DB，它的读很多，写很少，通常应用的是AVL。
