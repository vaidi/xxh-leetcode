package com.example.xxhleetcode.linknode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: elyuan
 * @Date: 2021/3/27 3:24 下午
 */
public class Matter61 {


    public static void main(String[] args) {
        ///[1,2,3,4,5]
        Integer[] array = {1,2};
        ListNode currNode = new ListNode();
        ListNode firstNode = new ListNode(0,currNode);
        int index =0;
        for (Integer integer : array) {
            index++;
            currNode.val = integer;
            if(index != array.length){
                currNode.next = new ListNode();
                currNode = currNode.next;
            }
        }
        System.out.println(firstNode.next);
        ListNode listNode = rotateRight(firstNode.next, 2);
        System.out.println(listNode);


    }





    public static ListNode rotateRight(ListNode head, int k) {

        if (k < 1 || head == null|| head.next == null) {
            return head;
        }
        int nodeLength = 0;
        Map<Integer, Integer> nodeMap = new HashMap<>();
        ListNode originNode = head;
        while (head != null) {
            nodeLength ++;
            nodeMap.put(nodeLength, head.val);
            head = head.next;
        }
        k = k %nodeLength;
        if(k <1){
            return originNode;
        }
        int index = nodeLength - k + 1;
        ListNode firstCurrNode = new ListNode();
        ListNode firstNode = new ListNode(0,firstCurrNode);
        Set<Integer> keySet = nodeMap.keySet();
        long firstNum = keySet.stream().filter(e -> e >= index).count();
        int firstIndex =0;
        long lastNum = keySet.stream().filter(e -> e < index).count();
        int lastIndex =0;
        ListNode lastCurrNode = new ListNode();
        ListNode lastNode = new ListNode(0,lastCurrNode);
        for (Integer hashKey : keySet) {
            Integer val = nodeMap.get(hashKey);
            if(hashKey >= index){
                firstIndex ++;
                firstCurrNode.val = val;
                if(firstIndex != firstNum){
                    firstCurrNode.next = new ListNode();
                    firstCurrNode = firstCurrNode.next;
                }
            }else{
                lastIndex ++;
                lastCurrNode.val = val;
                if(lastIndex != lastNum){
                    lastCurrNode.next = new ListNode();
                    lastCurrNode = lastCurrNode.next;
                }
            }

        }
        firstCurrNode.next = lastNode.next;
        return firstNode.next;
    }

@Data
   static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode listNode) {
            this.val = val;
            this.next = listNode;
        }

    }


}
