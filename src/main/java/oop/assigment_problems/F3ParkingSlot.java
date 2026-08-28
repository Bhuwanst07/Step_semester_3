package oop.assignment_problems;

public class F3ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    public F3ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    public static F3ParkingSlot findAvailableSlot(F3ParkingSlot[] slots) {
        for (F3ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    public static void safeAllot(F3ParkingSlot[] slots, String vehicleNo) {
        F3ParkingSlot availableSlot = findAvailableSlot(slots);

        if (availableSlot != null) {
            availableSlot.allot(vehicleNo);
        } else {
            System.out.println("No slots available");
        }
    }

    public static void main(String[] args) {

        F3ParkingSlot[] availableSlots = {
            new F3ParkingSlot("A1", 2, 1),
            new F3ParkingSlot("A2", 2, 2)
        };

        safeAllot(availableSlots, "RJ14AB1234");

        F3ParkingSlot[] fullSlots = {
            new F3ParkingSlot("B1", 1, 1),
            new F3ParkingSlot("B2", 2, 2)
        };

        safeAllot(fullSlots, "DL01XY5678");

        // Passing the array copies only the reference to the array.
        // The actual ParkingSlot objects are not copied.
    }
}