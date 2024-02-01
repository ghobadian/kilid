package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.dao.CityRepository;
import ir.ac.kntu.kilid.dao.ProvinceRepository;
import ir.ac.kntu.kilid.models.City;
import ir.ac.kntu.kilid.models.CityInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/city/")
    public City add(@RequestBody CityInputDTO input) {
        return cityRepository.save(City.builder().name(input.getCityName())
                .province(provinceRepository.findById(input.getProvinceId()).orElseThrow()).build());
    }


}
