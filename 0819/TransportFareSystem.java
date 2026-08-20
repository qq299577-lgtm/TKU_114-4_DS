abstract class Transport {
    private String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public abstract double calculateFare(int distance);
}

class Bus extends Transport {
    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        int d = Math.max(distance, 0);
        return 15 + d * 2.5;
    }
}

class Taxi extends Transport {
    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        int d = Math.max(distance, 0);
        return 85 + d * 20.0;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("市區公車 307"),
            new Bus("紅 26 路線"),
            new Taxi("台灣大車隊"),
            new Taxi("Uber 專車")
        };

        int distance = 10;
        for (Transport t : transports) {
            System.out.printf("路線: %-10s | 距離: %2d km | 票價: $%.1f\n", 
                t.getRouteName(), distance, t.calculateFare(distance));
        }
    }
}