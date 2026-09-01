import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    static class Patient implements Comparable<Patient> {
        String mrn;
        int urgency;
        int arrivalSeq;

        Patient(String mrn, int urgency, int arrivalSeq) {
            this.mrn = mrn;
            this.urgency = urgency;
            this.arrivalSeq = arrivalSeq;
        }

        @Override
        public int compareTo(Patient o) {
            if (this.urgency != o.urgency) {
                return Integer.compare(this.urgency, o.urgency);
            }
            if (this.arrivalSeq != o.arrivalSeq) {
                return Integer.compare(this.arrivalSeq, o.arrivalSeq);
            }
            return this.mrn.compareTo(o.mrn);
        }

        @Override
        public String toString() {
            return String.format("[病歷號:%s | 危急等級:%d | 到院序號:%d]", mrn, urgency, arrivalSeq);
        }
    }

    private final PriorityQueue<Patient> queue = new PriorityQueue<>();
    private int seqCounter = 0;

    public void checkIn(String mrn, int urgency) {
        Patient p = new Patient(mrn, urgency, ++seqCounter);
        queue.offer(p);
        System.out.println("已報到: " + p);
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public Patient callNext() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();

        triage.checkIn("P001", 3);
        triage.checkIn("P002", 1);
        triage.checkIn("P003", 2);
        triage.checkIn("P004", 1);
        triage.checkIn("P005", 2);

        System.out.println("\n目前下一位病患: " + triage.peekNext());

        System.out.println("\n開始依序叫號:");
        while (!triage.isEmpty()) {
            System.out.println("叫號 -> " + triage.callNext());
        }

        System.out.println("空佇列叫號測試: " + triage.callNext());
    }
}