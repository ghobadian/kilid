package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.AdvertisementRepository;
import ir.ac.kntu.kilid.dao.DistrictRepository;
import ir.ac.kntu.kilid.dao.ManagerRepository;
import ir.ac.kntu.kilid.models.Advertisement;
import ir.ac.kntu.kilid.models.AdvertisementOutputDTO;
import ir.ac.kntu.kilid.models.AdvertisementType;
import ir.ac.kntu.kilid.models.Manager;
import ir.ac.kntu.kilid.models.filters.AdvertisementFilter;
import ir.ac.kntu.kilid.models.filters.Filter;
import ir.ac.kntu.kilid.models.filters.QueryOperator;
import ir.ac.kntu.kilid.models.input.AdvertisementInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final DistrictRepository districtRepository;
    private final ManagerRepository managerRepository;
    private final SecureRandom random = new SecureRandom();
    public AdvertisementOutputDTO create(String managerUsername, AdvertisementInputDTO input) {
        Advertisement advertisement = Advertisement.from(input);
        advertisement.setDistrict(districtRepository.findByName(input.getDistrict()).orElseThrow());
        advertisement.setCode(generateCode());
        Manager manager = managerRepository.findByUser_Username(managerUsername).orElseThrow();
        advertisement.setEstateAgency(manager.getEstateAgency());
        setFeeBasedOnAdvertisementType(input, advertisement);
        Advertisement ad = advertisementRepository.save(advertisement);
        return AdvertisementOutputDTO.from(ad);
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static void setFeeBasedOnAdvertisementType(AdvertisementInputDTO input, Advertisement advertisement) {
        if (input.getAdvertisementType() == AdvertisementType.BUY) {
            advertisement.setPrice(input.getPrice());
        } else {
            advertisement.setMortgage(input.getMortgage());
            advertisement.setRent(input.getRent());
        }
    }

    public List<AdvertisementOutputDTO> search(String address) {
        return advertisementRepository.searchAdvertisements(address).stream().map(AdvertisementOutputDTO::from).collect(Collectors.toList());
    }

    public List<AdvertisementOutputDTO> findAllBySpecification(AdvertisementFilter input) {
        return advertisementRepository.findAll(getSpecifications(getFilters(input))).stream().map(AdvertisementOutputDTO::from).collect(Collectors.toList());
    }

    private Specification<Advertisement> createSpecification(Filter input) {
        return switch (input.getOperator()) {
            case EQUALS -> (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(input.getField()),
                    castToRequiredType(root.get(input.getField()).getJavaType(),
                            input.getValue()));
            case NOT_EQUALS -> (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(input.getField()),
                    castToRequiredType(root.get(input.getField()).getJavaType(),
                            input.getValue()));
            case GREATER_THAN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.gt(root.get(input.getField()),
                            (Number) castToRequiredType(
                                    root.get(input.getField()).getJavaType(),
                                    input.getValue()));
            case LESS_THAN -> (root, query, criteriaBuilder) -> criteriaBuilder.lt(root.get(input.getField()),
                    (Number) castToRequiredType(
                            root.get(input.getField()).getJavaType(),
                            input.getValue()));
            case LIKE -> (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(input.getField()),
                    "%"+input.getValue()+"%");
            case IN -> (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(input.getField()))
                    .value(castToRequiredType(
                            root.get(input.getField()).getJavaType(),
                            input.getValues()));
        };
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class) || fieldType.getName().equals("double")) {
            return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class) || fieldType.getName().equals("int")) {
            return Integer.valueOf(value);
        } else if(Enum.class.isAssignableFrom(fieldType) || fieldType.getName().equals("enum")) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

    private Specification<Advertisement> getSpecifications(List<Filter> filters){
        Specification<Advertisement> specification = where(createSpecification(filters.remove(0)));
        for (Filter filter : filters) {
            specification = specification.and(createSpecification(filter));
        }
        return specification;
    }

    private List<Filter> getFilters(AdvertisementFilter input) {
        List<Filter> filters = new ArrayList<>();

        if (input.getAdvertisementType() != null) {
            filters.add(Filter.builder()
                    .field("advertisementType")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getAdvertisementType().toString())
                    .build());
        }

        if (input.getYear() != null) {
            filters.add(Filter.builder()
                    .field("year")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getYear().toString())
                    .build());
        }

        if (input.getArea() != null) {
            filters.add(Filter.builder()
                            .field("area")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getArea().toString())
                    .build());
        }

        if (input.getRooms() != null) {
            filters.add(Filter.builder()
                            .field("rooms")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getRooms().toString())
                    .build());
        }

        if (input.getPrice() != null) {
            filters.add(Filter.builder()
                            .field("price")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getPrice().toString())
                    .build());
        }

        if (input.getRent() != null) {
            filters.add(Filter.builder()
                    .field("rent")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getRent().toString())
                    .build());
        }

        if (input.getMortgage() != null) {
            filters.add(Filter.builder()
                    .field("mortgage")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getMortgage().toString())
                    .build());
        }

        if (input.getUseType() != null) {
            filters.add(Filter.builder()
                            .field("useType")
                    .operator(QueryOperator.EQUALS)
                    .value(input.getUseType().toString())
                    .build());
        }

        return filters;
    }


    public AdvertisementOutputDTO info(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow();
        return AdvertisementOutputDTO.from(advertisement);
    }
}
