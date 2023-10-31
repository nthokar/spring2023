package com.nthokar.spring2023.main.application.repositories;

import com.nthokar.spring2023.main.application.entities.GameEntity;
import com.nthokar.spring2023.main.application.entities.MoveEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface GameRepository extends CrudRepository<GameEntity, String> {
    @Query("SELECT g FROM GameEntity g WHERE g.id = :id")
    public Optional<GameEntity> findByIdAndFetch(@Param("id") String id);
    @Query("SELECT g.moves FROM GameEntity g")
    public List<MoveEntity> findById2();
}
