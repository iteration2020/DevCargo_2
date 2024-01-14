package com.example.devcargo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query("SELECT p FROM Cargo p WHERE CONCAT(p.name,'',p.content,'',p.departureCity,'',p.departureDate,'',p.arrivalCity,'',p.arrivalDate) LIKE %?1%")
    List<Cargo> search(String keyword);

    List<Cargo> findByDepartureCity(String departureCity);

    List<Cargo> findByArrivalCity(String arrivalCity);

    List<Cargo> findByDepartureDate(LocalDate departureDate);

    @Query(value = "select new com.example.devcargo.CargoCountByDate(count(c), c.departureDate) from Cargo c group by c.departureDate")
    List<CargoCountByDate> countCargoByDepartureDate();

    List<Cargo> findByArrivalDateAfter(LocalDate arrivalDate);
}

@Data
@RequiredArgsConstructor
class CargoCountByDate {
    private final Long count;
    private final LocalDate departureDate;
}
