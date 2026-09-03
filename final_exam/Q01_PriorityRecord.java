import java.util.*;

public class Q01_PriorityRecord {

    public record Job(String id, int priority, long sequence) {}

    public static List<String> processOrder(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return new ArrayList<>();
        }

        Comparator<Job> jobComparator = Comparator
                .comparingInt(Job::priority)
                .thenComparingLong(Job::sequence)
                .thenComparing(Job::id, Comparator.nullsLast(Comparator.naturalOrder()));

        PriorityQueue<Job> pq = new PriorityQueue<>(jobComparator);

        for (Job job : jobs) {
            if (job != null) {
                pq.offer(job);
            }
        }

        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().id());
        }

        return result;
    }

    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
                new Job("jobD", 2, 100L),
                null,
                new Job("jobB", 1, 200L),
                new Job("jobA", 1, 100L),
                new Job("jobC", 1, 100L)
        );

        List<String> order = processOrder(jobs);
        System.out.println(order);
        System.out.println(processOrder(null));
        System.out.println(processOrder(Collections.emptyList()));
    }
}