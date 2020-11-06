package job4j_url_shortcut.service;

import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.domain.StatisticData;
import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.SiteRepository;
import job4j_url_shortcut.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.11.2020
 */
@Service
public class StatisticService implements Statistic<StatisticData, Url> {
    private final SiteRepository siteRepository;
    private final UrlRepository urlRepository;

    @Autowired
    public StatisticService(SiteRepository siteRepository, UrlRepository urlRepository) {
        this.siteRepository = siteRepository;
        this.urlRepository = urlRepository;
    }

    @Override
    public List<StatisticData> getStats(String site) {
        Optional<Site> siteBox = siteRepository.findBySite(site);
        List<StatisticData> result = new ArrayList<>();
        siteBox.ifPresent(link -> link.getUrls()
                .forEach(url -> {
                    result.add(new StatisticData(
                            url.getUrl(),
                            url.getTotal()));
                }));
        return result;
    }

    @Override
    public void incrementTotal(Url some) {
        long total = some.getTotal();
        some.setTotal(total++);
        urlRepository.save(some);
    }
}
