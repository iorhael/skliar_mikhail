package com.senla.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionManager {
    private static final SessionFactory SESSION_FACTORY;

    static {
        SESSION_FACTORY = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    private SessionManager() {
    }

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }
}
