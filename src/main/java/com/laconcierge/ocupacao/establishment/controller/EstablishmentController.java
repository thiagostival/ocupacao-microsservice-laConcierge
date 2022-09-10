package com.laconcierge.ocupacao.establishment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laconcierge.ocupacao.establishment.dto.EstablishmentDTO;
import com.laconcierge.ocupacao.establishment.entity.Establishment;
import com.laconcierge.ocupacao.establishment.service.EstablishmentService;
import com.laconcierge.ocupacao.responses.Response;

@RestController
@RequestMapping(value = "establishment")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping(value = "/list")
    public List<EstablishmentDTO> list() {
        return establishmentService.list();
    }

    @PutMapping(value = "/updateMaxCapacity")
    public ResponseEntity<Response<Establishment>> updateMaxCapacity(@Valid @RequestBody Establishment establishment, BindingResult result) {
        Response<Establishment> response = new Response<>();
        if (result.hasErrors()) {
            response.getErrors().addAll(result.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return ResponseEntity.badRequest().body(response);
        }

        if (establishment.getMaxCapacity() < establishment.getBusyCapacity()) {
            response.getErrors().add("A capacidade ocupada não pode ser maior que a capacidade máxima!");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
        establishmentService.updateMaxCapacity(establishment);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateBusyCapacity")
    public ResponseEntity<Response<Establishment>> updateBusyCapacity(@Valid @RequestBody Establishment establishment, BindingResult result) {
        Response<Establishment> response = new Response<>();
        if (result.hasErrors()) {
            response.getErrors().addAll(result.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return ResponseEntity.badRequest().body(response);
        }

        if (establishment.getMaxCapacity() < establishment.getBusyCapacity()) {
            response.getErrors().add("A capacidade ocupada não pode ser maior que a capacidade máxima!");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        establishmentService.updateBusyCapacity(establishment);

        return ResponseEntity.ok().build();
    }

}
