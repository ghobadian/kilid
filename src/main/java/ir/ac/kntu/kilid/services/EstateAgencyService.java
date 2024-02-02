package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.AdvertisementRepository;
import ir.ac.kntu.kilid.dao.CityRepository;
import ir.ac.kntu.kilid.dao.EstateAgencyRepository;
import ir.ac.kntu.kilid.dao.ManagerRepository;
import ir.ac.kntu.kilid.models.AdvertisementOutputDTO;
import ir.ac.kntu.kilid.models.EstateAgency;
import ir.ac.kntu.kilid.models.Manager;
import ir.ac.kntu.kilid.models.input.EstateAgencyInputDTO;
import ir.ac.kntu.kilid.models.output.EstateAgencyOutputDTO;
import ir.ac.kntu.kilid.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstateAgencyService {
    private final EstateAgencyRepository estateAgencyRepository;
    private final CityRepository cityRepository;
    private final AdvertisementRepository advertisementRepository;
    private final TokenUtils tokenUtils;
    private final ManagerRepository managerRepository;
    public EstateAgencyOutputDTO create(String token, EstateAgencyInputDTO input) {
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
        managerRepository.save(manager);
        return EstateAgencyOutputDTO.from(agency);
    }

    public EstateAgencyOutputDTO info(Long estateAgencyId) {
        return EstateAgencyOutputDTO.from(estateAgencyRepository.findById(estateAgencyId).orElseThrow());
    }

    public List<AdvertisementOutputDTO> getLastAdvertisements(Long estateAgencyId) {
        return advertisementRepository.findAllByEstateAgencyId(estateAgencyId)
                .stream().map(AdvertisementOutputDTO::from).collect(Collectors.toList());
    }

}
