package job4j_url_shortcut.control;

import job4j_url_shortcut.Job4jUrlShortcutApplication;
import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.SiteRepository;
import job4j_url_shortcut.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Job4jUrlShortcutApplication.class)
@AutoConfigureMockMvc
class StatisticControlTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SiteRepository siteRepository;

    @Test
    void getStats() throws Exception {
        Site site = new Site(0, "site", "login", "password");
        site.addUrl(new Url(0, "asdasd.ru", "asdad", 123));
        site.addUrl(new Url(0, "asdasd.com", "asdad", 1234));
        when(siteRepository.findBySite(anyString())).thenReturn(Optional.of(site));
        mockMvc.perform(get("/statistic/")
                .contentType(MediaType.TEXT_PLAIN)
                .param("site", "http://job4j.ru"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        "[{\"url\":\"asdasd.ru\",\"total\":123}," +
                                "{\"url\":\"asdasd.com\",\"total\":1234}]"))
                .andExpect(status().isOk());
    }
}