package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.charity.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository <Donation, Long> {

    @Query(value = "SELECT SUM(quantity) FROM donation;", nativeQuery = true)
    int numberOfBags();

    @Query(value = "SELECT COUNT(id) FROM donation;", nativeQuery = true)
    int numberOfDonations();
}
