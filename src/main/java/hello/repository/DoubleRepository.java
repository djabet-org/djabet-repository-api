package hello.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.Roll;

@Repository
public interface DoubleRepository extends JpaRepository<Roll, Long> {
}
