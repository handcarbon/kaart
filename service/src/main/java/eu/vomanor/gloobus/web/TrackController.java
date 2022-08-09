package eu.vomanor.gloobus.web;

import eu.vomanor.gloobus.dto.TrackDto;
import eu.vomanor.gloobus.service.TrackService;
import eu.vomanor.gloobus.service.TrackUploadService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.IOException;
import java.util.List;

@Controller("/api/v1/track")
public class TrackController {

    private final TrackUploadService trackUploadService;
    private final TrackService trackService;

    public TrackController(TrackUploadService trackUploadService,
                           TrackService trackService) {
        this.trackUploadService = trackUploadService;
        this.trackService = trackService;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse create() {
        return HttpResponse.ok();
    }

    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Post("/upload")
    public HttpResponse<String> upload(CompletedFileUpload file) throws IOException {
        if ((file.getFilename() == null || file.getFilename().equals(""))) {
            return HttpResponse.badRequest();
        }

        trackUploadService.upload(file);
        return HttpResponse.ok();
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<TrackDto>> list() {
        return HttpResponse.ok(trackService.getTracks());
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<TrackDto> getById(long id) {
        return HttpResponse.ok(trackService.getTrack(id));
    }
}
