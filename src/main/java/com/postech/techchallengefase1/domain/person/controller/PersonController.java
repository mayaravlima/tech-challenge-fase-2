package com.postech.techchallengefase1.domain.person.controller;

import com.postech.techchallengefase1.domain.person.dto.CreatePersonDTO;
import com.postech.techchallengefase1.domain.person.dto.PersonResponseDTO;
import com.postech.techchallengefase1.domain.person.dto.UpdatePersonDTO;
import com.postech.techchallengefase1.domain.person.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<PersonResponseDTO> save(@Valid @RequestBody CreatePersonDTO person, @RequestHeader String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePerson(person, username));
    }

    @PostMapping("/{personId}/address/{addressId}")
    public ResponseEntity<PersonResponseDTO> associateAddressWithPerson(
            @RequestHeader String username,
            @PathVariable("personId") Long personId,
            @PathVariable("addressId") Long addressId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.associateAddressWithPerson(username, personId, addressId));
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPerson());
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<PersonResponseDTO>> searchByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersonByName(name));
    }

    @GetMapping("/search/relationship/{relationship}")
    public ResponseEntity<List<PersonResponseDTO>> searchByRelationship(@PathVariable String relationship) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersonByRelationship(relationship));
    }

    @GetMapping("/search/gender/{gender}")
    public ResponseEntity<List<PersonResponseDTO>> searchByGender(@PathVariable String gender) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersonByGender(gender));
    }

    @GetMapping("/search/cpf/{cpf}")
    public ResponseEntity<List<PersonResponseDTO>> searchByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersonByCpf(cpf));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPersonById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> update(@Valid @RequestBody UpdatePersonDTO person, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePerson(person, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Person deleted successfully"));
    }
}
