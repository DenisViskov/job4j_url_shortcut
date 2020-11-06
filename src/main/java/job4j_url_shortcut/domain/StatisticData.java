package job4j_url_shortcut.domain;

import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
public class StatisticData {
    private String url;
    private long total;

    public StatisticData(String url, long total) {
        this.url = url;
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticData that = (StatisticData) o;
        return total == that.total &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, total);
    }
}
