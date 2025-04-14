package mk.finki.ukim.lab_emt.service.application.impl;

import mk.finki.ukim.lab_emt.model.dto.CreateStayDto;
import mk.finki.ukim.lab_emt.model.dto.DisplayStayDto;
import mk.finki.ukim.lab_emt.model.domain.Host;
import mk.finki.ukim.lab_emt.service.application.StayApplicationService;
import mk.finki.ukim.lab_emt.service.domain.HostService;
import mk.finki.ukim.lab_emt.service.domain.StayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StayApplicationServiceImpl implements StayApplicationService {
    private final StayService stayService;
    private final HostService hostService;

    public StayApplicationServiceImpl(StayService stayService, HostService hostService) {
        this.stayService = stayService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayStayDto> findAll() {
        return DisplayStayDto.from(stayService.findAll());
    }

    @Override
    public List<DisplayStayDto> findFree() {
        return DisplayStayDto.from(stayService.findFree());
    }

    @Override
    public Optional<DisplayStayDto> findById(Long stayId) {
        return stayService.findById(stayId)
                .map(DisplayStayDto::from);
    }

    @Override
    public Optional<DisplayStayDto> save(CreateStayDto createStayDto) {
        Optional<Host> host = hostService.findById(createStayDto.host());
        if (host.isPresent()) {
            return stayService.save(createStayDto.toStay(host.get()))
                    .map(DisplayStayDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayStayDto> update(Long stayId, CreateStayDto createStayDto) {
        Optional<Host> host = hostService.findById(createStayDto.host());
        if (host.isPresent()) {
            return stayService.update(stayId,
                            createStayDto.toStay(
                                    host.orElse(null))
                    )
                    .map(DisplayStayDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long stayId) {
        stayService.deleteById(stayId);

    }

    @Override
    public DisplayStayDto markStayAsRented(Long stayId) {
        return DisplayStayDto.from(stayService.markStayAsRented(stayId));
    }
}
