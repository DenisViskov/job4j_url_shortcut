package job4j_url_shortcut.control;

import job4j_url_shortcut.Job4jUrlShortcutApplication;
import job4j_url_shortcut.domain.Site;
import job4j_url_shortcut.domain.Url;
import job4j_url_shortcut.repository.SiteRepository;
import job4j_url_shortcut.repository.UrlRepository;
import job4j_url_shortcut.service.Registration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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
    private UrlRepository urlRepository;
    @MockBean
    private SiteRepository siteRepositoryService;

    @Test
    @WithMockUser
    void convert() throws Exception {
        when(urlRepository.findByUrl(anyString())).thenReturn(Optional.empty());
        when(siteRepositoryService.findBySite(anyString())).thenReturn(Optional.of(new Site(1,
                "https://job4j.ru",
                "job4j",
                "pass")));
        mockMvc.perform(post("/url/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\": \"https://job4j.ru/TrackStudio/task/8993?thisframe=true\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":0," +
                        "\"url\":\"https://job4j.ru/TrackStudio/task/8993?thisframe=true\"}"))
                .andExpect(status().isOk());
        verify(urlRepository).save(any());
        verify(siteRepositoryService).save(any());
    }

    @Test
    @WithMockUser
    void WhenWeHave() throws Exception {
        when(urlRepository.findByUrl(anyString())).thenReturn(Optional.of(new Url(0,"url","code",0)));
        mockMvc.perform(post("/url/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\": \"https://job4j.ru/TrackStudio/task/8993?thisframe=true\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":0," +
                        "\"url\":\"url\"}"))
                .andExpect(status().isOk());
    }
}