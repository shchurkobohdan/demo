package api;

import api.clients.BookerClient;
import api.clients.ClientFactory;
import api.models.Booking;
import api.resources.AuthRequest;
import api.resources.CreateBookingResponse;
import api.util.FileUtil;
import feign.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BookingTest {
    private BookerClient bookerClient = ClientFactory.getClient(BookerClient.class);
    private String bookingId;
    private String token;

    @BeforeClass
    public void setup() {
        token = bookerClient.createToken(new AuthRequest("admin", "password123"))
                .getToken();
    }

    @Test
    public void testCreateBooking() {
        Booking booking = FileUtil.readJsonFile("request/createBooking.json", Booking.class);
        CreateBookingResponse response = bookerClient.createBooking(booking);

        assertNotNull(response.getBooking());
        bookingId = response.getBookingid();
    }

    @Test
    public void testGetBooking() {
        Booking response = bookerClient.getBooking(bookingId);
        assertNotNull(response);
    }

    @Test
    public void testUpdateBooking() {
        Booking booking = FileUtil.readJsonFile("request/createBooking.json", Booking.class);
        booking.setFirstname(booking.getFirstname() + "(updated)");
        booking.setLastname(booking.getLastname() + "(updated)");

        Booking response = bookerClient.updateBooking(token, bookingId, booking);
        assertNotNull(response);
        assertEquals(response.getFirstname(), booking.getFirstname());
        assertEquals(response.getLastname(), booking.getLastname());
    }

    @Test
    public void testDeleteBooking() {
        Response response = bookerClient.deleteBooking(token, bookingId);
        assertEquals(response.status(), 201);
    }
}
