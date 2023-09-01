package api.resources;

import api.models.Booking;
import lombok.Data;

@Data
public class CreateBookingResponse {
    private String bookingid;
    private Booking booking;
}
