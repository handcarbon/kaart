package eu.vomanor.gloobus.web;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

import java.time.Instant;

@Controller("/api/v1/tracking")
public class TrackingController {

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse postLocation(@Body TrackingEvent event) {
        return HttpResponse.ok();
    }

    record TrackingEvent(String deviceToken, double longitude, double latitude, Instant datetime) {}
}
