package hello.unit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.MultipartFile;

import hello.ExcelHelper;
import hello.Roll;
import hello.repository.DoubleRepository;
import hello.service.DoubleService;

@ExtendWith(MockitoExtension.class)
public class DoubleServiceTest {

@InjectMocks
private DoubleService service;

@Mock
private DoubleRepository repository;

@Mock
private ExcelHelper excelHelper;

@Test
public void testSave() {
    Roll roll = mock(Roll.class);
    service.save(roll);

    verify(repository).save(roll);
}

@Test
public void shouldCallFetchRolls() {
    Roll roll1 = Roll.builder().id(1L).created(Instant.now()).build();
    Roll roll2 = Roll.builder().id(2L).created(Instant.now().plusSeconds(60)).build();

    when(repository.findByPlatform(anyString(), any(PageRequest.class))).thenReturn(new PageImpl<Roll>(List.of(roll1, roll2)));

    List<Roll> rolls = service.fetch(2, "asc", "platform");
    assertEquals(2, rolls.size());
}

@Test
public void shouldUploadFile() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    InputStream in = mock(InputStream.class);

    when(file.getInputStream()).thenReturn(in);

    service.upload(file);

    verify(repository).saveAll(anyIterable());
    verify(excelHelper).excelToRolls(in);
}
}