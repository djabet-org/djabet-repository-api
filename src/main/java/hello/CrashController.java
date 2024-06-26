package hello;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.service.CrashService;
import hello.service.DoubleService;
import hello.service.SseService;

@RestController
public class CrashController {

@Autowired
private CrashService service;

@Autowired
private SseService sseService;

 @CrossOrigin
 @GetMapping(path = "/api/crash/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(HttpServletResponse response) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        sseService.addEmitter(emitter);
        return emitter;
    } 

@CrossOrigin
@PostMapping(path = "/api/crash",
   consumes = MediaType.APPLICATION_JSON_VALUE,
   produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity saveRoll(@Valid @RequestBody Crash newCrash, HttpServletRequest request) {
   System.out.println("Crash received: "+ newCrash);
try {
    service.save(newCrash);
    sseService.sendEvents(newCrash);
    return ResponseEntity.status(HttpStatus.CREATED).build();
} catch (Exception e) {
    // TODO: handle exception
    return ResponseEntity.badRequest().build();
}

}

// @CrossOrigin
// @GetMapping( path = "/api/double",
// produces = MediaType.APPLICATION_JSON_VALUE)
// public ResponseEntity<String> fetchRolls(@RequestParam("qtd") int qtd,
//  @RequestParam("sort") String sort,
//   @RequestParam("platform") String platform) throws JsonProcessingException {
//     try {
//     List<Roll> rolls = service.fetch(qtd, sort, platform);
//     String rollsAsJson = new ObjectMapper().writeValueAsString(rolls);
//     return ResponseEntity.ok().body(rollsAsJson);
//     } catch (Exception e) {
//         // TODO: handle exception
//     return ResponseEntity.status(500).build();
//     }
// }

}