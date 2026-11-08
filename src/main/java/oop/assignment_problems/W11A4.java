package oop.assignment_problems;

public class W11A4 {

    static class BusBay {

        private int[] buses;

        private int front;
        private int size;

        private int capacity;

        BusBay(int capacity) {

            if (capacity <= 0) {
                throw new IllegalArgumentException(
                        "Capacity must be positive"
                );
            }

            this.capacity = capacity;
            buses = new int[capacity];

            front = 0;
            size = 0;
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == capacity;
        }

        boolean insertFront(int bus) {

            if (isFull()) {
                return false;
            }

            front = (front - 1 + capacity) % capacity;

            buses[front] = bus;

            size++;

            return true;
        }

        boolean insertLast(int bus) {

            if (isFull()) {
                return false;
            }

            int rear = (front + size) % capacity;

            buses[rear] = bus;

            size++;

            return true;
        }

        boolean deleteFront() {

            if (isEmpty()) {
                return false;
            }

            front = (front + 1) % capacity;

            size--;

            if (size == 0) {
                front = 0;
            }

            return true;
        }

        boolean deleteLast() {

            if (isEmpty()) {
                return false;
            }

            size--;

            if (size == 0) {
                front = 0;
            }

            return true;
        }

        int getFront() {

            if (isEmpty()) {
                return -1;
            }

            return buses[front];
        }

        int getRear() {

            if (isEmpty()) {
                return -1;
            }

            int rear = (front + size - 1) % capacity;

            return buses[rear];
        }
    }

    public static void main(String[] args) {

        BusBay b = new BusBay(3);

        System.out.println(b.insertLast(101));

        System.out.println(b.insertLast(102));

        System.out.println(b.insertFront(100));

        System.out.println(b.insertFront(99));

        System.out.println(b.getRear());

        System.out.println(b.isFull());

        System.out.println(b.deleteLast());

        System.out.println(b.insertFront(99));

        System.out.println(b.getFront());
    }
}

/*
All operations:
Time Complexity: O(1)
Space Complexity: O(capacity)
No Java collection classes used.
*/