package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.*;
import ir.ac.kntu.kilid.models.Advertisement;
import ir.ac.kntu.kilid.models.EstateAgency;
import ir.ac.kntu.kilid.models.Manager;
import ir.ac.kntu.kilid.models.input.EstateAgencyInputDTO;
import ir.ac.kntu.kilid.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstateAgencyService {
    private final EstateAgencyRepository estateAgencyRepository;
    private final CityRepository cityRepository;
    private final AdvertisementRepository advertisementRepository;
    private final TokenUtils tokenUtils;
    public EstateAgency create(String token, EstateAgencyInputDTO input) {
        Manager manager = tokenUtils.getManager(token);
        EstateAgency agency = estateAgencyRepository.save(EstateAgency.builder()
                .name(input.getPersianName())
                .phoneNumber(input.getPhone())
                .numberOfEmployees(input.getNumberOfEmployees())
                .city(cityRepository.findByName(input.getCity()).orElseThrow())
                .password(input.getPassword())
                .manager(manager)
                .build());
        manager.setEstateAgency(agency);
        return agency;
    }

    public EstateAgency info(Long estateAgencyId) {
        return estateAgencyRepository.findById(estateAgencyId).orElseThrow();
    }

    public List<Advertisement> getLastAdvertisements(Long estateAgencyId) {
        return advertisementRepository.findAllByEstateAgencyId(estateAgencyId);
    }

}
