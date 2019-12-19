package gdb.serverinventorycodealong.dao;

import gdb.serverinventorycodealong.dto.Server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @date Thursday December 19, 2019
 * @author garrettbecker
 */

public class ServerDaoInMemImpl implements ServerDao {
    
    private Map<String, Server> serverMap = new HashMap<>();

    @Override
    public void addServer(Server server) {
        serverMap.put(server.getName(), server);
    }

    @Override
    public Server getServer(String name) {
        return serverMap.get(name);
    }

    @Override
    public void removeServer(String name) {
        serverMap.remove(name);
    }

    @Override
    public List<Server> getAllServers() {
        return new ArrayList<Server>(serverMap.values());
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
        return serverMap.values()
                .stream()
                .collect(Collectors.groupingBy(s -> s.getManufacturer()));
    }

    @Override
    public List<Server> getServersByManufacturer(String manufacturer) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Server> getServersOlderThan(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.groupingBy(s -> s.getManufacturer()));
    }

    @Override
    public double getAverageServerAge() {
        return serverMap.values()
                .stream()
                .mapToLong(s -> s.getServerAge())
                .average()
                .getAsDouble();
    }
    
}
