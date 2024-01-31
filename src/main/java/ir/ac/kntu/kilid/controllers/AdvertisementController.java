package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.models.Advertisement;
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
    public List<Advertisement> search(@RequestHeader String token, @RequestParam String address) {
        return service.search(address);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/create")
    public Advertisement create(AdvertisementInputDTO input) {
        return service.create(input);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/add/search_filtered")
    public List<Advertisement> findAllBySpecification(AdvertisementFilter input) {
        return service.findAllBySpecification(input);
    }
}
