package hello.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;

import hello.DoubleController;
import hello.Roll;
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
public void shouldCallServiceForCreation() {
    Roll roll = mock(Roll.class);
    controller.saveRoll(roll, null);

    verify(service).save(roll);
}

@Test
public void shouldCallGetRollsService() throws JsonProcessingException {
    when(service.fetch(2, "asc")).thenReturn(
        List.of(
            Roll.builder().created(Instant.now()).build(),
            Roll.builder().created(Instant.now().plusSeconds(60)).build()
        ));

    controller.fetchRolls(2, "asc");

    verify(service).fetch(2,"asc");
}

@Test
public void shouldCallSeeService() {
    Roll roll = mock(Roll.class);
    controller.saveRoll(roll, null);

    verify(sseService).sendEvents(roll);
}

}