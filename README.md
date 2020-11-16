1.LinkedHashMap和HashMap有以下不同：
      HashMap实现了Map接口，是无序的，不保存元素的插入顺序，在内部也不保证按
照元素的大小顺序排列。
      HashMap存值的时候会根据key的hashCode()来计算存储的位置（位置是散列的，所
以说其无序）。
      其实HashMap还是有序的，只是它是按照key的hashCode()求出的散列码排的序。
      LinkedHashMap是HashMap的子类，其操作和HashMap相同。LinkedHashMap不允
许重复的元素，但是保留插入元素的顺序。
      当对LinkedHashMap进行迭代时，他按照元素添加的顺序返回。
 2.Hash Table的查询、插入、删除操作 时间复杂度为：O(1),最糟糕为：O(n)
 3.二叉搜索树的查询和操作的时间复杂度都是O(logn)
