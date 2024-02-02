package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.models.AdvertisementOutputDTO;
import ir.ac.kntu.kilid.models.filters.AdvertisementFilter;
import ir.ac.kntu.kilid.models.input.AdvertisementInputDTO;
import ir.ac.kntu.kilid.services.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdvertisementController {
    private final AdvertisementService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/add/search")
    public List<AdvertisementOutputDTO> search(@RequestParam String address) {
        return service.search(address);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add/create")
    public AdvertisementOutputDTO create(@RequestParam String token, @RequestBody AdvertisementInputDTO input) {
        return service.create(token, input);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/search_filtered")
    public List<AdvertisementOutputDTO> findAllBySpecification(@RequestBody AdvertisementFilter input) {
        return service.findAllBySpecification(input);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/add/{id}")
    public AdvertisementOutputDTO info(@PathVariable Long id) {
        return service.info(id);
    }
}
