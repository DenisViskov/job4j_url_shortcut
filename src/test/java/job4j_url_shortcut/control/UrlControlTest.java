package job4j_url_shortcut.control;

import job4j_url_shortcut.Job4jUrlShortcutApplication;
import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.repository.UrlRepository;
import job4j_url_shortcut.service.SiteRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Job4jUrlShortcutApplication.class)
@AutoConfigureMockMvc
class UrlControlTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Autowired
    private UrlRepository urlRepository;
    @MockBean
    @Autowired
    private SiteRepositoryService siteRepositoryService;

    @Test
    void convert() throws Exception {
        when(urlRepository.findByUrl(anyString())).thenReturn(Optional.empty());
        when(siteRepositoryService.findBySiteName(anyString())).thenReturn(Optional.of(new Site(1,
                "https://job4j.ru",
                "job4j",
                "pass")));
        mockMvc.perform(post("/url/convert")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{url:\"https://job4j.ru/TrackStudio/task/8993?thisframe=true\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{123}"))
                .andExpect(status().isOk());
    }
}