package comcent.common.builders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ParallelCallBuilder {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static List<Object> execInParallel(Callable<Object>... callables) {
        try {
            return executor.invokeAll(Arrays.stream(callables).collect(Collectors.toList())).stream().map(objectFuture -> {
                try {
                    return objectFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    return null;
                }
            }).collect(Collectors.toList());
        } catch (InterruptedException e) {
            return null;
        }
    }
}
