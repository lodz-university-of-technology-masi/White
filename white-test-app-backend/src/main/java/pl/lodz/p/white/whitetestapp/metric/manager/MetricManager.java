package pl.lodz.p.white.whitetestapp.metric.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.metric.service.MetricService;
import pl.lodz.p.white.whitetestapp.model.UsabilityData;
import pl.lodz.p.white.whitetestapp.repository.MetricRepository;

import java.time.Instant;

@Service
public class MetricManager implements MetricService {

    private final MetricRepository repository;

    @Autowired
    public MetricManager(MetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(UsabilityData usabilityData, String ip, String username) {
        usabilityData
                .setIp(ip)
                .setUsername(username)
                .setSavetime(Instant.now())
                .setmId((long) repository.findAllByUsername(username).size());
        repository.saveAndFlush(usabilityData);
    }
}
