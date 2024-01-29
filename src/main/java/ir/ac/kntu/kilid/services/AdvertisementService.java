package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.AdvertisementRepository;
import ir.ac.kntu.kilid.models.Advertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository repository;
    public List<Advertisement> search(String address) {
        return repository.findAll();
    }
}
