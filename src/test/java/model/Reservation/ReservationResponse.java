package model.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Weather.Coord;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationResponse {

    @JsonProperty("reservation")
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

