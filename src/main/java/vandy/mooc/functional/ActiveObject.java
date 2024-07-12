package vandy.mooc.functional;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

public class ActiveObject<T, R>
        implements Future<R> {
    R mResult;
    RunnableFuture<R> mRunnableFuture;
    Thread mThread;
    T mParams;

    public ActiveObject(Function<T, R> function,
                        T params) {
        mRunnableFuture = makeThreadClosure(function, params);
    }

    protected RunnableFuture<R> makeThreadClosure(Function<T, R> function,
                                                  T params) {
        mParams = params;
        RunnableFuture<R> runnableFuture = new FutureTask<>(() -> function.apply(params));
        mThread = new Thread(runnableFuture);
        return runnableFuture;
    }

    public void start() {
        mThread.start();
    }

    public static class ActiveObjectArray<K, V, R>
            extends Array<ActiveObject<Map.Entry<K, V>, R>> {
    }

    public static <K, V, R> ActiveObjectArray<K, V, R> makeActiveObjects
            (Function<Map.Entry<K, V>, R> task,
             Map<K, V> inputMap) {
        ActiveObjectArray<K, V, R> activeObjects = new ActiveObjectArray<>();
        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            activeObjects.add(new ActiveObject<>(task, entry));
        }
        return activeObjects;
    }

    public T getParams() {
        return mParams;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return mRunnableFuture.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return mRunnableFuture.isCancelled();
    }

    @Override
    public boolean isDone() {
        return mRunnableFuture.isDone();
    }

    public R get()
            throws InterruptedException, ExecutionException {
        return mRunnableFuture.get();
    }

    @Override
    public R get(long timeout, @NotNull TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return mRunnableFuture.get(timeout, unit);
    }

    public Thread getThread() {
        return mThread;
    }
}