package hello.unit;
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

import hello.DoubleController;
import hello.Roll;
import hello.repository.DoubleRepository;
import hello.service.DoubleService;
import hello.service.SseService;

@ExtendWith(MockitoExtension.class)
public class DoubleControllerTest {

@InjectMocks
private DoubleController controller;

@Mock
private SseService sseService;
@Mock
private DoubleService service;

@Test
public void shouldCallService() {
    Roll roll = mock(Roll.class);
    controller.saveRoll(roll, null);

    verify(service).save(roll);
}

@Test
public void shouldCallSeeService() {
    Roll roll = mock(Roll.class);
    controller.saveRoll(roll, null);

    verify(sseService).sendEvents();
}

}