package com.project.backend.controller;

import com.project.backend.dto.CommentDto;
import com.project.backend.dto.GameDto;
import com.project.backend.dto.GameSystemRequirementDto;
import com.project.backend.service.GameSystemRequirementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/gameSystemRequirement")
@SecurityRequirement(name = "bearerAuth")
public class GameSystemRequirementController {
    private GameSystemRequirementService service;

    @PostMapping
    public ResponseEntity<GameSystemRequirementDto> createGameSystemRequirement(@RequestBody GameSystemRequirementDto Dto){
        GameSystemRequirementDto savedGameSystemRequirementDto = service.createGameSystemRequirement(Dto);
        return new ResponseEntity<>(savedGameSystemRequirementDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<GameSystemRequirementDto> getGameSystemRequirementById(@PathVariable("id") Long GameSystemRequirementId){
        GameSystemRequirementDto gameSystemRequirementDto = service.getGameSystemRequirementById(GameSystemRequirementId);
        return ResponseEntity.ok(gameSystemRequirementDto);
    }

    @GetMapping
    public ResponseEntity<List<GameSystemRequirementDto>> getAllGameSystemRequirement(){
        List<GameSystemRequirementDto> allGameSystemRequirement = service.getAllGameSystemRequirement();
        return ResponseEntity.ok(allGameSystemRequirement);
    }
    @GetMapping("/findByGame/{id}")
    public ResponseEntity<List<GameSystemRequirementDto>> getListCommentByGame(@PathVariable("id") Long gameId){
        List<GameSystemRequirementDto> gameSystemRequirements = service.listGameSystemRequirementByGame(gameId);
        return ResponseEntity.ok(gameSystemRequirements);
    }

    @PutMapping("{id}")
    public ResponseEntity<GameSystemRequirementDto> UpdateGameSystemRequirement(@PathVariable("id") Long GameSystemRequirementId, @RequestBody GameSystemRequirementDto updatedGameSystemRequirement){
        GameSystemRequirementDto gameSystemRequirementDto = service.UpdateGameSystemRequirement(GameSystemRequirementId , updatedGameSystemRequirement);
        return ResponseEntity.ok(gameSystemRequirementDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGameSystemRequirement(@PathVariable("id") Long GameSystemRequirementId){
        service.deleteGameSystemRequirement(GameSystemRequirementId);
        return ResponseEntity.ok("Game deleted Successfully");
    }
}
