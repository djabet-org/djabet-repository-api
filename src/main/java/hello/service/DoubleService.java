package hello.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.Roll;
import hello.repository.DoubleRepository;

@Service
public class DoubleService {
    
    @Autowired
    private DoubleRepository repository;

    public Optional<Roll> save(Roll roll) {
        try {
        Roll saved = repository.save(roll);
        return Optional.of(saved);    
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
            return Optional.empty();
        }
    }
    
}
