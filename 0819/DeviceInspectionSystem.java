class Device {
    private String serialNumber;

    public Device(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void runDiagnostic() {
        System.out.printf("[%s] 執行常規硬體診斷中...\n", serialNumber);
    }
}

class Laptop extends Device {
    public Laptop(String serialNumber) {
        super(serialNumber);
    }

    @Override
    public void runDiagnostic() {
        System.out.printf("[%s Laptop] 檢查電池健康度與 CPU 狀態...\n", getSerialNumber());
    }
}

class Printer extends Device {
    public Printer(String serialNumber) {
        super(serialNumber);
    }

    @Override
    public void runDiagnostic() {
        System.out.printf("[%s Printer] 檢查墨水存量與進紙模組...\n", getSerialNumber());
    }

    public void cleanPrintHead() {
        System.out.printf("[%s Printer] 正在清洗噴頭...\n", getSerialNumber());
    }
}

class Router extends Device {
    public Router(String serialNumber) {
        super(serialNumber);
    }

    @Override
    public void runDiagnostic() {
        System.out.printf("[%s Router] 檢測網路封包傳輸率與連線埠...\n", getSerialNumber());
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = {
            new Laptop("LAP-101"),
            new Printer("PRN-201"),
            new Router("RTR-301"),
            new Printer("PRN-202")
        };

        for (Device dev : devices) {
            dev.runDiagnostic();
            if (dev instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}