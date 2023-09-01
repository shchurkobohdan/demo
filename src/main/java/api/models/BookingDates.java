package api.models;

import api.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
public class BookingDates {
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date checkin;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date checkout;
}
