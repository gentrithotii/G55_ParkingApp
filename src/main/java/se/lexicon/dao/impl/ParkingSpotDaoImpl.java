package se.lexicon.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private List<ParkingSpot> inMemoryStorage = new ArrayList<>();

    @Override
    public ParkingSpot save(ParkingSpot parkingSpot) {
        inMemoryStorage.add(parkingSpot);
        return null;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return inMemoryStorage;
    }

    @Override
    public List<ParkingSpot> findAllAvailableSpots() {
        List<ParkingSpot> aviableSpotList = new ArrayList<>();
        for (ParkingSpot parkingSpotItem : findAll()) {
            if (!parkingSpotItem.isOccupied()) {
                aviableSpotList.add(parkingSpotItem);
            }
        }
        return aviableSpotList;
    }

    @Override
    public Optional<ParkingSpot> findById(Integer id) {
        for (ParkingSpot parkingSpotItem : inMemoryStorage) {
            if (parkingSpotItem.getSpotNumber().equals(id)) {
                return Optional.of(parkingSpotItem);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        for (int i = 0; i < inMemoryStorage.size(); i++) {
            if (inMemoryStorage.get(i).getSpotNumber().equals(parkingSpot.getSpotNumber())) {
                inMemoryStorage.set(i, parkingSpot);
            }
        }

    }

    @Override
    public void delete(Integer id) {
        Optional<ParkingSpot> foundItem = this.findById(id);

        foundItem.ifPresent((item) -> inMemoryStorage.remove(item));
    }
}
