package com.project.backend.service;

import com.project.backend.dto.CommentDto;
import com.project.backend.dto.GameSystemRequirementDto;

import java.util.List;

public interface GameSystemRequirementService {
    GameSystemRequirementDto createGameSystemRequirement(GameSystemRequirementDto Dto);
    GameSystemRequirementDto getGameSystemRequirementById(Long GameSystemRequirementId);
    List<GameSystemRequirementDto> getAllGameSystemRequirement();
    List<GameSystemRequirementDto> listGameSystemRequirementByGame(Long gameId);
    GameSystemRequirementDto UpdateGameSystemRequirement(Long GameSystemRequirementId ,GameSystemRequirementDto updatedGameSystemRequirement);
    void deleteGameSystemRequirement(Long GameSystemRequirementId);
}
