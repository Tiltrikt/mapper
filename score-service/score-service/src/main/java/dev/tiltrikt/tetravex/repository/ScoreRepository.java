package dev.tiltrikt.tetravex.repository;

import dev.tiltrikt.tetravex.model.Score;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

  @NotNull List<Score> findFirstByOrderByPointsDesc();
}
