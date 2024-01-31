package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.*;
import ir.ac.kntu.kilid.models.Advertisement;
import ir.ac.kntu.kilid.models.EstateAgency;
import ir.ac.kntu.kilid.models.input.EstateAgencyInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstateAgencyService {
    private final EstateAgencyRepository estateAgencyRepository;
    private final CityRepository cityRepository;
    private final ManagerRepository managerRepository;
    private final AdvertisementRepository advertisementRepository;
    public EstateAgency create(String token, EstateAgencyInputDTO input) {
        return estateAgencyRepository.save(EstateAgency.builder()
                .name(input.getPersianName())
                        .phoneNumber(input.getPhoneNumber())
                        .numberOfEmployees(input.getNumberOfEmployees())
                        .city(cityRepository.findByName(input.getCity()).orElseThrow())
                        .password(input.getPassword())
                        .manager(managerRepository.findById(TokenRepository.TOKEN_REPOSITORY.inverse().get(token))
                                .orElseThrow())
                .build());
    }

    public EstateAgency info(Long estateAgencyId) {
        return estateAgencyRepository.findById(estateAgencyId).orElseThrow();
    }

    public List<Advertisement> getLastAdvertisements(Long estateAgencyId) {
        return advertisementRepository.findAllByEstateAgencyId(estateAgencyId);
    }
}
