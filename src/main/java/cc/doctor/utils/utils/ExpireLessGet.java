package cc.doctor.utils.utils;

import cc.doctor.framework.entity.Tuple;

import java.util.Date;

/**
 * 过期数据Get方法
 * @param <T> 数据泛型
 */
public abstract class ExpireLessGet<T> {

    private long lastRefresh;

    private long expiredIn;

    private T data;

    /**
     * Should set the data and expiredIn
     *
     * @return a tuple of data and expiredIn
     */
    public abstract Tuple<T, Long> load();

    public long getLastRefresh() {
        return lastRefresh;
    }

    public long getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(long expiredIn) {
        this.expiredIn = expiredIn;
    }

    public T getData() {
        Date date = new Date();
        // 初始化或者已过期
        if (lastRefresh == 0 || date.getTime() >= lastRefresh + getExpiredIn()) {
            Tuple<T, Long> load = load();
            this.data = load.getT1();
            this.expiredIn = load.getT2();
            this.lastRefresh = date.getTime();
        }
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
