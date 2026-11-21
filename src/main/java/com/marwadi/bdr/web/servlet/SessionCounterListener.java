package com.marwadi.bdr.web.servlet;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCounterListener implements HttpSessionListener {

    // Number of sessions currently open (shared by the whole application)
    private static final AtomicInteger activeSessions = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions.decrementAndGet();
    }

    // Other classes can call this to get the current count
    public static int getActiveSessions() {
        return activeSessions.get();
    }
}