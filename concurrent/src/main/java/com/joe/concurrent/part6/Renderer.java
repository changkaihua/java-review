package com.joe.concurrent.part6;

import java.util.List;
import java.util.concurrent.*;

import static com.joe.concurrent.utils.LaunderThrowable.launderThrowable;

/**
 * Renderer
 * <p/>
 * Using CompletionService to render page elements as they become available
 *
 * @author Brian Goetz and Tim Peierls
 */
public abstract class Renderer {
    private final ExecutorService executor;

    Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        // CompletionService: 以异步的方式一边生产新的任务，一边处理已完成任务的结果，这样可以将执行任务与处理任务分离开来进行处理
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);

        for (final ImageInfo imageInfo : info) {
            completionService.submit(imageInfo::downloadImage);
        }

        renderText(source);


        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                // take()方法取得最先完成任务的Future对象，谁执行时间最短谁最先返回, 会阻塞
                // waiting if none are yet present
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);

}
