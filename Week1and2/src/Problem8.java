import java.util.*;

public class Problem8 {

    static class Spot {
        String plate;
        boolean occupied;

        Spot() {
            plate = null;
            occupied = false;
        }
    }

    private Spot[] table = new Spot[500];

    public Problem8() {
        for (int i = 0; i < table.length; i++)
            table[i] = new Spot();
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % table.length;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        int probes = 0;

        while (table[index].occupied) {
            index = (index + 1) % table.length;
            probes++;
        }

        table[index].occupied = true;
        table[index].plate = plate;

        System.out.println("Assigned spot " + index +
                " probes: " + probes);

        return index;
    }

    public void exitVehicle(String plate) {

        for (int i = 0; i < table.length; i++) {
            if (table[i].occupied && table[i].plate.equals(plate)) {
                table[i].occupied = false;
                System.out.println("Spot freed: " + i);
                return;
            }
        }
    }

    public static void main(String[] args) {

        Problem8 parking = new Problem8();

        parking.parkVehicle("ABC1234");
        parking.parkVehicle("XYZ9999");

        parking.exitVehicle("ABC1234");
    }
}