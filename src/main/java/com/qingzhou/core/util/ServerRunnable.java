package com.qingzhou.core.util;

import java.io.Closeable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerRunnable implements Runnable, Closeable {
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected final CountDownLatch startupLatch = new CountDownLatch(1);
	protected final CountDownLatch shutdownLatch = new CountDownLatch(1);
	protected final AtomicBoolean alive = new AtomicBoolean(false);

	@Override
	public void close() {
		alive.set(false);
		try {
			shutdownLatch.await();
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void awaitStartup() throws InterruptedException {
		startupLatch.await();
	}

	protected void startupComplete() {
		alive.set(true);
		startupLatch.countDown();
	}

	protected void shutdownComplete() {
		shutdownLatch.countDown();
	}

	protected boolean isRunning() {
		return alive.get();
	}

}
