package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.Roll;

public interface DoubleRepository extends JpaRepository<Roll, Long> {
    
}
