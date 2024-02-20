package com.minashkin.project.web.controller;

import com.minashkin.project.model.dto.CatDto;
import com.minashkin.project.model.entyti.Cat;
import com.minashkin.project.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepo catRepo;

    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDto dto) {
        catRepo.save(Cat.builder()
                .name(dto.getName())
                .weight(dto.getWeight())
                .age(dto.getAge())
                .build());
    }

    @Operation(summary = "Gets all cats", tags = "cat")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the cats",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CatDto.class)))
                    })
    })
    @GetMapping("api/all")
    public List<CatDto> getAllCats() {
        List<CatDto> catDtos = new ArrayList<>();
        for (Cat cat : catRepo.findAll()) {
            catDtos.add(CatDto.builder()
                    .name(cat.getName())
                    .weight(cat.getWeight())
                    .age(cat.getAge())
                    .build());
        }
        return catDtos;
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam Long id) {
        return catRepo.findById(id).orElseThrow();
    }

    @GetMapping("/api/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam Long id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/{id}")
    public Cat deleteCat(@PathVariable Long id,
                         @RequestBody(required = false) Cat newCat) {
        Cat cat = catRepo.findById(id).orElseThrow();
        cat.setName(newCat.getName() != null ? newCat.getName() : cat.getName());
        cat.setAge(newCat.getAge() != null ? newCat.getAge() : cat.getAge());
        cat.setWeight(newCat.getWeight() != null ? newCat.getWeight() : cat.getAge());
        return catRepo.save(cat);
    }
}
