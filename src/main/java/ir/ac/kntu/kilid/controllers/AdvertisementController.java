package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.models.Advertisement;
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
    @GetMapping("/search")
    private List<Advertisement> search(@RequestHeader String token, @RequestParam String address) {
//        securityService.list(token); todo implement security
        return service.search(address);
    }
}
