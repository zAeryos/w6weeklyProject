package it.epicode.w6weeklyProject.repositories;

import it.epicode.w6weeklyProject.models.objects.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer>, PagingAndSortingRepository<Device, Integer> {
}
