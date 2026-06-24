/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode findNth(ListNode head, int k)
    {   ListNode temp = head;
        int cnt =1;
        while(temp!=null)
        {
            if(cnt == k) return temp;
            cnt++;
            temp=temp.next;
        }
        return temp;
    }
    public ListNode rotateRight(ListNode head, int k) {
         if (head == null || head.next == null || k == 0)
    return head;
        // find length of ll
        int len = 1;
        ListNode tail = head;
        while(tail.next!=null)
        {
            len++;
            tail=tail.next;
        }
        // edge case suppose length =5 and k =5or multiple of 5 then we dont need to do this 
        if(k%len==0)
        {
            return head;
        }
        k=k%len;
        tail.next = head;
        ListNode newlast=findNth(head,len-k);
        head = newlast.next ;
        newlast.next = null;
        return head;
    }
}