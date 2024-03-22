package io.getarrays.server.service;

import io.getarrays.server.model.Server;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

@Service
public interface ServerService {
    Server create(Server server);
    Server update(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Boolean delete(Long id);

}
