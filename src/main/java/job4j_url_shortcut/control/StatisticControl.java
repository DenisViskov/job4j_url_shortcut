package job4j_url_shortcut.control;

import job4j_url_shortcut.domain.StatisticData;
import job4j_url_shortcut.service.Statistic;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@RestController
@RequestMapping("/statistic")
public class StatisticControl {
    private final Statistic service;

    @Autowired
    public StatisticControl(Statistic statistic) {
        this.service = statistic;
    }

    @GetMapping(value = "/", consumes = "text/plain", produces = "application/json")
    public List<StatisticData> getStats(@RequestParam String site) {
        return service.getStats(site);
    }
}
