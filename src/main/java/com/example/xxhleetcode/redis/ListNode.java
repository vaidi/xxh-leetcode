package com.example.xxhleetcode.redis;

/**
 * @Author: elyuan
 * @Date: 2021/5/6 12:44 下午
 * 1、链表被广泛用于实现 Redis 的各种功能， 比如列表键， 发布与订阅， 慢查询， 监视器， 等等。
 * 2、每个链表节点由一个 listNode 结构来表示， 每个节点都有一个指向前置节点和后置节点的指针， 所以 Redis 的链表实现是双端链表。
 * 3、每个链表使用一个 list 结构来表示， 这个结构带有表头节点指针、表尾节点指针、以及链表长度等信息。
 * 4、因为链表表头节点的前置节点和表尾节点的后置节点都指向 NULL ， 所以 Redis 的链表实现是无环链表。
 * 5、通过为链表设置不同的类型特定函数， Redis 的链表可以用于保存各种不同类型的值。
 *
 */
public class ListNode {

    RedisNode head;
    RedisNode tail;
    long len;

}
class RedisNode{
    RedisNode prev;
    RedisNode next;
    String value;


}