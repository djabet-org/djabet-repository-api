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

    public void save(Roll roll) {
        repository.save(roll);
    }
    
}
