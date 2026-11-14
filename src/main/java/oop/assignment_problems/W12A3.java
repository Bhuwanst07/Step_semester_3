
package oop.assignment_problems;

import java.util.HashMap;
import java.util.Scanner;

public class W12A3 {

    static class Node {
        int key;
        String page;
        Node prev, next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    static class RecentCache {

        int capacity;
        HashMap<Integer, Node> map;

        Node head, tail;
        int evictedKey = -1;

        RecentCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, "");
            tail = new Node(0, "");

            head.next = tail;
            tail.prev = head;
        }

        // Remove a node from the doubly linked list
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Insert a node immediately after head
        void insertAtFront(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        // Move an existing node to the front
        void makeMostRecent(Node node) {
            remove(node);
            insertAtFront(node);
        }

        // Get a page and mark it as most recently used
        String get(int key) {

            if (!map.containsKey(key)) {
                return "-1";
            }

            Node node = map.get(key);

            makeMostRecent(node);

            return node.page;
        }

        // Add or update a page
        void put(int key, String page) {

            evictedKey = -1;

            // If key already exists, update it
            if (map.containsKey(key)) {

                Node node = map.get(key);
                node.page = page;

                makeMostRecent(node);
                return;
            }

            // If cache is full, remove least recently used node
            if (map.size() == capacity) {

                Node lru = tail.prev;

                evictedKey = lru.key;

                remove(lru);
                map.remove(lru.key);
            }

            // Create and insert the new node
            Node newNode = new Node(key, page);

            insertAtFront(newNode);
            map.put(key, newNode);
        }

        int getEvictedKey() {
            return evictedKey;
        }

        // Display cache from most recent to least recent
        void display() {

            Node current = head.next;

            while (current != tail) {

                System.out.print(
                    "(" + current.key + ", " + current.page + ")"
                );

                current = current.next;

                if (current != tail) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int capacity = sc.nextInt();
        int q = sc.nextInt();

        RecentCache cache = new RecentCache(capacity);

        for (int i = 0; i < q; i++) {

            String operation = sc.next();

            if (operation.equalsIgnoreCase("PUT")) {

                int key = sc.nextInt();
                String page = sc.next();

                cache.put(key, page);

                System.out.println(cache.getEvictedKey());
            }

            else if (operation.equalsIgnoreCase("GET")) {

                int key = sc.nextInt();

                System.out.println(cache.get(key));
            }
        }

        // Display final cache
        cache.display();

        sc.close();
    }
}
