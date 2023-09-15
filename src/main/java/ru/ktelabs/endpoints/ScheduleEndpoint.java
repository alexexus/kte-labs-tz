package ru.ktelabs.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.ktelabs.exceptions.ScheduleNotCreatedException;
import ru.ktelabs.services.CouponService;
import ws.ktelabs.schedule.CreateScheduleRequest;
import ws.ktelabs.schedule.CreateScheduleResponse;

import javax.validation.constraints.NotNull;

@Endpoint
public class ScheduleEndpoint {
    private static final String NAMESPACE_URI = "http://ktelabs.ws/schedule";

    private final CouponService couponService;

    @Autowired
    public ScheduleEndpoint(CouponService couponService) {
        this.couponService = couponService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createScheduleRequest")
    @ResponsePayload
    public CreateScheduleResponse createScheduleResponse(@RequestPayload @NotNull CreateScheduleRequest createScheduleRequest) {
        CreateScheduleResponse createScheduleResponse = new CreateScheduleResponse();
        try {
            couponService.createSchedule(createScheduleRequest.getDoctorId(), createScheduleRequest
                    .getScheduleStartTime()
                    .toGregorianCalendar()
                    .toZonedDateTime()
                    .toLocalDateTime());
        } catch (ScheduleNotCreatedException scheduleNotCreatedException) {
            createScheduleResponse.setStatus(scheduleNotCreatedException.getMessage());
            return createScheduleResponse;
        }
        createScheduleResponse.setStatus("Успешно.");
        return createScheduleResponse;
    }
}