package com.first.zuoye;

//设计实现双端队列。
//你的实现需要支持以下操作：
//MyCircularDeque(k)：构造函数,双端队列的大小为k。
//insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
//insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
//deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
//deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
//getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
//getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
//isEmpty()：检查双端队列是否为空。
//isFull()：检查双端队列是否满了。
// 思路
// 主要还是判断条件，是我的弱势，需要加强
public class MyCircularDeque641 {

    int[] myqueue;
    int front;//队头指针
    int rear;//队尾指针
    int size;//队列当前的大小
    int capacity;//队列的容量

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque641(int k) {
        this.myqueue = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (rear == front && size == capacity) return false;//如果队列满，插入失败
        else {
            front = (front + capacity - 1) % capacity;
            myqueue[front] = value;
            size++;
            return true;
        }
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (rear == front && size == capacity) return false;//如果队列满，插入失败
        else {
            myqueue[rear] = value;
            rear = (rear + 1 + capacity) % capacity;
            size++;
            return true;
        }

    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (rear == front && size == 0) return false;
        else {
            front = (front + 1) % capacity;
            size--;
            return true;
        }

    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (rear == front && size == 0) return false;
        else {
            rear = (rear - 1 + capacity) % capacity;
            size--;
            return true;
        }

    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if ((rear == front) && size == 0) return -1;
        else {
            int frontE = myqueue[front];
            //front = (front + 1) % capacity;
            //size--;
            return frontE;
        }
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if ((rear == front) && size == 0) return -1;
        else {
            int rearE = myqueue[(rear - 1 + capacity) % capacity];
            // rear = (rear - 1 +capacity)%capacity;
            //size--;
            return rearE;
        }

    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return (rear == front) && size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return rear == front && size == capacity;

    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */