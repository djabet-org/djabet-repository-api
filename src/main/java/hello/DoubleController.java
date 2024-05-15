package hello;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.service.DoubleService;

@RestController
public class DoubleController {

@Autowired
private DoubleService service;

@PostMapping(path = "/api/double/save",
   consumes = MediaType.APPLICATION_JSON_VALUE,
   produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity saveRoll(@Valid @RequestBody Roll newRoll, HttpServletRequest request) {
   System.out.println("Roll received: "+newRoll);
try {
    service.save(newRoll);
    return ResponseEntity.status(HttpStatus.CREATED).build();
} catch (Exception e) {
    // TODO: handle exception
    return ResponseEntity.badRequest().build();
}

}}
