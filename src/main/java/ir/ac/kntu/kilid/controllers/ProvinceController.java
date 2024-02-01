package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.dao.ProvinceRepository;
import ir.ac.kntu.kilid.models.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceRepository provinceRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/province/")
    public Province add(@RequestParam String provinceName) {
        return provinceRepository.save(Province.builder().name(provinceName).build());
    }

}
