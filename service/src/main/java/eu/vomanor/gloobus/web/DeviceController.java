package eu.vomanor.gloobus.web;

import eu.vomanor.gloobus.domain.Device;
import eu.vomanor.gloobus.service.DeviceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse create() {
        deviceService.create("Random");
        return HttpResponse.ok();
    }


    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<Device>> list() {
        return HttpResponse.ok(deviceService.getAll());
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse getById(long id) {
        return HttpResponse.ok();
    }

}
