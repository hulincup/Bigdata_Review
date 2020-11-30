package com.suning.linkedlist;

/**
 * @Author lynn
 * @Date 2020/9/9 20:01
 */
class SingleLinkedList {
    //初始化头结点
    HeroNode head = new HeroNode(0,"","");

    /**
     * 增加节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //辅助节点 因为头结点不能动
        HeroNode tmp = head;
        //遍历链表 找到链表的最后
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        //走到这里 说明已经是到了链表的最后
        tmp.next = heroNode;
    }

    /**
     * 修改 根据no进行修改
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        boolean flag = false;
        //遍历 找到要被修改的节点
        while(true){
            if (tmp == null) {
                break;
            }
            if (tmp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        //flag=true,找到该节点后
        if (flag) {
            tmp.name = newHeroNode.name;
            tmp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("节点不存在");
        }
    }

    /**
     * 删除指定的节点
     * @param no
     */
    public void del(int no) {
        HeroNode tmp = head.next;
        boolean flag = false;
        while (true) {
            if (tmp == null) {
                System.out.println("链表为空");
                break;
            }
            if (tmp.next.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;//tmp后移，遍历
        }
        if (flag) {
            tmp.next = tmp.next.next;
        } else {
            System.out.println("要删除的节点不存在");
        }
    }

    /**
     *遍历所有的节点
     */
    public void list() {
        if (head.next == null) {
            return;
        }
        //头节点不能动 所以需要一个辅助变量tmp
        HeroNode tmp = head.next;
        while (true) {
            //判断是否到了最后一个节点
            if (tmp == null) {
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //默认为null,这样在尾部添加的时候,就会默认是最后一个

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode[no="+no+"name="+name+"nickName="+nickName+"]";
    }
}

public class SingleLinkedListTest {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new HeroNode(1,"唐僧","师傅"));
        linkedList.add(new HeroNode(2,"孙悟空","大师兄"));
        linkedList.add(new HeroNode(3,"猪八戒","二师兄"));
        linkedList.add(new HeroNode(4,"沙和尚","沙师弟"));
        linkedList.list();
        linkedList.update(new HeroNode(4,"沙悟净","小沙"));
        linkedList.list();
    }

}
