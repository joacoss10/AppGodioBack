package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia,Long> {
}
