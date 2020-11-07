package job4j_url_shortcut.control;

import job4j_url_shortcut.domain.StatisticData;
import job4j_url_shortcut.service.Statistic;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class is a statistic rest controller
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@RestController
@RequestMapping("/statistic")
public class StatisticControl {
    /**
     * Statistic service
     */
    private final Statistic service;

    @Autowired
    public StatisticControl(Statistic statistic) {
        this.service = statistic;
    }

    /**
     * Taking text/plain string and returns special object
     * which encapsulate necessary data
     *
     * @param site
     * @return
     */
    @GetMapping(value = "/", consumes = "text/plain", produces = "application/json")
    public List<StatisticData> getStats(@RequestParam String site) {
        return service.getStats(site);
    }
}
