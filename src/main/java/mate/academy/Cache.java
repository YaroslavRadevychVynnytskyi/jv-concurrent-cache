package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private Map<K, V> data;
    private ReadWriteLock lock;

    public Cache() {
        data = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            return data.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            data.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            data.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        data = new HashMap<>();
    }

    public int size() {
        return data.size();
    }
}
