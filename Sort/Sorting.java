/**
 * Common Sorting Algorithms Comparasion:
 * Name      | Best     | Average  | Worst    | Space   | Stable
 * Bubble    | Ω(n^2)   | θ(n^2)   | O(n^2)   | O(1)    | Yes
 * Selection | Ω(n^2)   | θ(n^2)   | O(n^2)   | O(1)    | No -> swap changes the relative position at the end of each round: eg. 4(a)-2-3-4(b)-1 -> 1-2-3-4(b)-4(a)
 * Insertion | Ω(n)     | θ(n^2)   | O(n^2)   | O(1)    | Yes
 * Heap      | Ω(nlogn) | θ(nlogn) | O(nlogn) | O(1)    | No -> eg. 4(a)-4(b)-2-1 -> 4(b)-2-1-4(a) -> 2-1-4(b)-4(a) -> 1-2-4(b)-4(a)
 * Quick     | Ω(nlogn) | θ(nlogn) | O(n^2)   | O(logn) | No -> swap elements according to pivot’s position (without considering their original positions)
 * Merge     | Ω(nlogn) | θ(nlogn) | O(nlogn) | O(n)    | Yes
 * Radix     | Ω(nk)    | θ(nk)    | O(nk)    | O(n+k)  | Yes
 * Bucket    | Ω(n+k)   | θ(n+k)   | O(n^2)   | O(n)    | Yes
 * Counting  | Ω(n+k)   | θ(n+k)   | O(n+k)   | O(k)    | Yes
 *
 * Stable & Unstable: A sorting algorithm is said to be stable if the order of the same values in the output remains the same as in input array.
 *                    eg. (stable) 23-32-12(a)-21-12(b) -> 12(a)-12(b)-21-23-32
 *
 */

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//#21 - Merge Two Sorted Lists
//Approach A: Iterative
//A new ListNode for convenient storage?
// -> Sometimes, in industrial projects, it's not trivial to create a ListNode which might require many resource allocations or inaccessible dependencies.
// -> So ideally, we'd pick up either the head of l1 or l2 as the head rather than creating a new one, which makes the initialization step tedious (TBH).
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(0);
    ListNode handler = head;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            handler.next = l1;
            l1 = l1.next;
        }
        else {
            handler.next = l2;
            l2 = l2.next;
        }
        handler = handler.next;
    }
    
    if (l1 != null) {
        handler.next = l1;
    }
    else if (l2 != null) {
        handler.next = l2;
    }

    return head.next;
}

//Approach B: Recursive
//Is this a practical approach?
// -> In real life, the length of a linked list could be much longer than we expected, in which case the recursive approach is likely to introduce a stack overflow.
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
        return l2;
    }
    if (l2 == null) {
        return l1;
    }
        
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    }
    else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
