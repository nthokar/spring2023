package com.nthokar.spring2023.archive;

import com.nthokar.spring2023.archive.app.entities.Game;
import com.nthokar.spring2023.archive.app.repos.GameRepository;
import com.nthokar.spring2023.archive.infrastracture.services.ArchiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ArchiveServiceTest {
    @Mock
    private GameRepository repo;

    @InjectMocks
    private ArchiveService service;
    @Test
    void testSaveGame() {
        var game = new Game();

        service.save(game);

        verify(repo, times(1)).save(game);
    }
}
