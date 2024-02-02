package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.models.Advertisement;
import ir.ac.kntu.kilid.models.AdvertisementOutputDTO;
import ir.ac.kntu.kilid.models.EstateAgency;
import ir.ac.kntu.kilid.models.input.EstateAgencyInputDTO;
import ir.ac.kntu.kilid.models.output.EstateAgencyOutputDTO;
import ir.ac.kntu.kilid.services.EstateAgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EstateAgencyController {
    private final EstateAgencyService service;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/agency/")
    public EstateAgencyOutputDTO create(@RequestParam String token, @RequestBody EstateAgencyInputDTO input) {
        return service.create(token, input);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/agency/{id}")
    public EstateAgencyOutputDTO info(@PathVariable Long id) {
        return service.info(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/agency/{id}/advertisements")
    public List<AdvertisementOutputDTO> getLastAdvertisements(@PathVariable Long id) {
        return service.getLastAdvertisements(id);
    }
}
