package hello;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import hello.repository.DoubleRepository;
import hello.service.DoubleService;

@ExtendWith(MockitoExtension.class)
public class DoubleServiceTest {

@InjectMocks
private DoubleService service;

@Mock
private DoubleRepository repository;

@Test
public void testSave() {
    Roll roll = mock(Roll.class);
    service.save(roll);

    verify(repository).save(roll);
}

}