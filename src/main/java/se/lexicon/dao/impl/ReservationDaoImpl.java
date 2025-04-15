package se.lexicon.dao.impl;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private ArrayList<Reservation> inMemoryStorage = new ArrayList<>();

    @Override
    public void save(Reservation reservation) {
        inMemoryStorage.add(reservation);
    }

    @Override
    public Optional<Reservation> findById(String id) {
        for (Reservation reservationItem : inMemoryStorage) {
            if (reservationItem.getReservationId().equals(id)) {
                return Optional.of(reservationItem);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Reservation reservation) {
        for (int i = 0; i < inMemoryStorage.size(); i++) {
            if (inMemoryStorage.get(i).getReservationId().equals(reservation.getReservationId())) {
                inMemoryStorage.set(i, reservation);
            }
        }
    }
}
