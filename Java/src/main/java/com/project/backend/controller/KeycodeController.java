package com.project.backend.controller;

import com.project.backend.dto.KeyCodeDto;
import com.project.backend.entity.KeyCode;
import com.project.backend.service.KeycodeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/keycode")
@SecurityRequirement(name = "bearerAuth")
public class KeycodeController {
    @Autowired
    private KeycodeService service;

    @PostMapping
    public ResponseEntity<KeyCodeDto> createkeycode(@RequestBody KeyCodeDto keyCodeDto){
        KeyCodeDto savedKeycode = service.createkeycode(keyCodeDto);
        return new ResponseEntity<>(savedKeycode, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<KeyCodeDto> getkeycodeById(@PathVariable("id") Long keycodeId){
        KeyCodeDto Dto = service.getkeycodeById(keycodeId);
        return ResponseEntity.ok(Dto);
    }
    @GetMapping
    public ResponseEntity<List<KeyCodeDto>> getAllkeycode(){
        List<KeyCodeDto> codeDtos = service.getAllkeycode();
        return ResponseEntity.ok(codeDtos);
    }

    
    @GetMapping("enablekey")
    public ResponseEntity<List<KeyCode>> getAllEnable(){
        List<KeyCode> codeDtos = service.findByStatusEnable();
        return ResponseEntity.ok(codeDtos);
    }
    
    @GetMapping("disablekey")
    public ResponseEntity<List<KeyCode>> getAllDisable(){
        List<KeyCode> codeDtos = service.findByStatusDisable();
        return ResponseEntity.ok(codeDtos);
    }
    
    
    @PutMapping("{id}")
    public ResponseEntity<KeyCodeDto> updateKeycode(@PathVariable("id") Long keycodeId, @RequestBody KeyCodeDto dto){
        KeyCodeDto keyCodeDto = service.Updatekeycode(keycodeId , dto);
        return ResponseEntity.ok(keyCodeDto);
    }
    
    
  
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKeycode(@PathVariable("id") Long keycodeId){
        service.deleteGamekeycode(keycodeId);
        return ResponseEntity.ok("Game deleted Successfully");
    }

}
