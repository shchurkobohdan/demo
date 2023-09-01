package api.clients;

import api.models.Booking;
import api.resources.AuthRequest;
import api.resources.CreateBookingResponse;
import api.resources.TokenResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface BookerClient {

    @RequestLine("POST /auth")
    @Headers("Content-Type: application/json")
    TokenResponse createToken(AuthRequest request);

    @RequestLine("POST /booking")
    @Headers("Content-Type: application/json")
    CreateBookingResponse createBooking(Booking booking);

    @RequestLine("GET /booking/{bookingId}")
    @Headers("Accept: application/json")
    Booking getBooking(@Param("bookingId") String bookingId);

    @RequestLine("PUT /booking/{bookingId}")
    @Headers({"Content-Type: application/json", "Accept: application/json", "Cookie: token={token}"})
    Booking updateBooking(@Param("token") String token, @Param("bookingId") String bookingId, Booking booking);

    @RequestLine("DELETE /booking/{bookingId}")
    @Headers({"Content-Type: application/json",  "Cookie: token={token}"})
    Response deleteBooking(@Param("token") String token, @Param("bookingId") String bookingId);
}
