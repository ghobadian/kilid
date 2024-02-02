package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.dao.CityRepository;
import ir.ac.kntu.kilid.dao.DistrictRepository;
import ir.ac.kntu.kilid.models.District;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/district/")
    public District add(@RequestParam String districtName, @RequestParam Long cityId) {
        return districtRepository.save(District.builder().name(districtName)
                .city(cityRepository.findById(cityId).orElseThrow()).build());
    }
}
