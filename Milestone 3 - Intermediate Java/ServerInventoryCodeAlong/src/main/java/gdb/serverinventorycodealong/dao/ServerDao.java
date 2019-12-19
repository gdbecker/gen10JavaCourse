package gdb.serverinventorycodealong.dao;

import gdb.serverinventorycodealong.dto.Server;
import java.util.List;
import java.util.Map;

/**
 * @date Thursday December 19, 2019
 * @author garrettbecker
 */

public interface ServerDao {
    public void addServer(Server server);
    
    public Server getServer(String name);
    
    public void removeServer(String name);
    
    public List<Server> getAllServers();
    
    public Map<String, List<Server>> getAllServersGroupByManufacturer();
    
    public List<Server> getServersByManufacturer(String manufacturer);
    
    public List<Server> getServersOlderThan(int ageInYears);
    
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears);
    
    public double getAverageServerAge();

}
