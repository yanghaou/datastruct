package link;

import sun.font.TrueTypeFont;

import javax.swing.text.html.parser.TagElement;
import java.util.ArrayList;
import java.util.List;

/**
 * function
 * Author: yang.hao
 * Date: 2016/12/15
 */
public class LinkList<T> {
    /**
     * 链表内部节点
     */
    private class Node{
        //数据
        private T data;
        //改节点指向下一个节点的指针
        private Node next;

        Node(){

        }
        Node(T data,Node next){
            this.data = data;
            this.next = next;
        }
    }

    //头节点
    private Node head;
    //尾节点
    private Node tail;
    private int size;//链表大小

    //构造函数初始化数据
    public LinkList(){
        head=null;
        tail=null;
    }

    /**
     * 头插法
     * @param element
     */
    public void addHead(T element){
        Node newHead = new Node(element,head);
        head = newHead;
        if (head == tail)
            tail = head;
        size++;
    }

    /**
     * 尾插法
     */
    public void addTail(T element){
        Node newTail = new Node(element,null);
        if (head == null){
            head = newTail;
            tail = head;
        }else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    //获取索引为index的节点的值,不存在返回null
    public T get(int index){
        return getNodeByIndex(index).data;
    }

    private Node getNodeByIndex(int index) {
        if (index <0 || index >size -1)
            throw new ArrayIndexOutOfBoundsException("链表越界");
        Node current = head;
        for (int i =0;i<size && current !=null;i++,current = current.next){
            if (i == index){
                return current;
            }
        }
        return null;
    }

    //查找元素的索引，若不存在返回-1
    public int getIndex(T element){
        Node current = head;
        for (int i =0;i<size && current !=null;i++,current = current.next){
            if (element.equals(current.data)){
                return i;
            }
        }
        return -1;
    }

    //链表是否为空
    public boolean empty(){
        if (size ==0){
            assert head ==null;
            return true;
        }else {
            assert head ==null;
            return false;
        }
    }

    //链表长度
    public int length(){
        return size;
    }

    //删除index的元素
    public T delete(int index){
        if (index<0 || index >size)
            throw new IndexOutOfBoundsException("下标越界");
        Node del =null;
        if (index == 0){
            del =head;
            head = head.next;
        }else {
            Node pre = getNodeByIndex(index -1);
            del = pre.next;
            pre.next=del.next;
            del.next= null;
        }
        size --;
        return del.data;
    }

    public int deleteByElement(T element){
        int index = getIndex(element);
        delete(index);
        return index;
    }

    //从链表后面删除一个元素
    public T remove(){
        return delete(size-1);
    }

    //清空链表
    public void clear(){
        head = null;
        tail = null;
        size =0;
    }

    //在指定为插入元素
    public void insert(int index, T element){
        if (index <0|| index>size){
            throw new IndexOutOfBoundsException("下标越界");
        }
        if (head ==null){
            addHead(element);
        }else {
            if (index ==0){
                addHead(element);
            }else {
                Node pre = getNodeByIndex(index-1);
                pre.next = new Node(element,pre.next);
                size++;
            }
        }
    }

    //遍历链表
    public List<T> traverse(){
        List<T> list = new ArrayList<T>();
        Node current = head;
        while (current !=null){
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public static void main(String []args){
        LinkList linkList = new LinkList();
        linkList.addTail(1);
        linkList.addTail(2);
        linkList.addTail(3);
        linkList.addTail(4);
        linkList.addHead(2);

        linkList.delete(2);
        System.out.println(linkList.traverse());
    }
}
