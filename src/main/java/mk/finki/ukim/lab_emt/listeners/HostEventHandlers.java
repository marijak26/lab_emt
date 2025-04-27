package mk.finki.ukim.lab_emt.listeners;

import mk.finki.ukim.lab_emt.events.HostCreatedEvent;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        this.hostService.refreshMaterializedView();
    }

}
