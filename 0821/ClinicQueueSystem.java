import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ClinicQueueSystem {

    static class Patient {
        private final String medicalRecordNumber;
        private final String name;

        public Patient(String mrn, String name) {
            this.medicalRecordNumber = mrn;
            this.name = name;
        }

        public String getMrn() { return medicalRecordNumber; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return "[" + medicalRecordNumber + "] " + name;
        }
    }

    private final Queue<Patient> waitingQueue = new LinkedList<>();
    private final List<Patient> completedList = new ArrayList<>();

    public void register(String mrn, String name) {
        Patient p = new Patient(mrn, name);
        waitingQueue.offer(p);
        System.out.println("掛號成功: " + p);
    }

    public boolean cancel(String mrn) {
        Iterator<Patient> iterator = waitingQueue.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getMrn().equals(mrn)) {
                iterator.remove();
                System.out.println("取消成功: " + p);
                return true;
            }
        }
        System.out.println("找不到病歷號: " + mrn);
        return false;
    }

    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前無等待病患");
            return null;
        }
        Patient p = waitingQueue.poll();
        completedList.add(p);
        System.out.println("叫號服務: " + p);
        return p;
    }

    public Patient peekNext() {
        return waitingQueue.peek();
    }

    public void printCompleted() {
        System.out.println("當日完成清單: " + completedList);
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register("M001", "張三");
        clinic.register("M002", "李四");
        clinic.register("M003", "王五");
        clinic.register("M004", "趙六");

        System.out.println("下一位: " + clinic.peekNext());

        clinic.cancel("M002");
        clinic.cancel("M999");

        clinic.callNext();
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();

        clinic.printCompleted();
    }
}