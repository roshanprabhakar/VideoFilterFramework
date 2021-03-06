package org.roshanp.processor;

import org.roshanp.feed.Feed;
import org.roshanp.filter.Filter;
import org.roshanp.renderer.Renderer;

/**
 * a thread for the removal of video frames from a feed queue, processing of said frames, and injection of processed frames into a renderer queue
 */
public class Processor extends Thread {

    private Filter filter;

    private Feed feed;
    private Renderer renderer;

    public Processor(Feed feed, Renderer renderer, Filter filter) {
        this.feed = feed;
        this.renderer = renderer;
        this.filter = filter;
    }

    public void run() {
        while (true) {
            int[][] frame = feed.take();
            frame = filter.filter(frame);
            renderer.add(frame);
        }
    }
}
