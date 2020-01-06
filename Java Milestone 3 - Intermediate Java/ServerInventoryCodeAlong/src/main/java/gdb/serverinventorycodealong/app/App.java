package gdb.serverinventorycodealong.app;

import gdb.serverinventorycodealong.dao.ServerDao;
import gdb.serverinventorycodealong.dao.ServerDaoInMemImpl;
import gdb.serverinventorycodealong.dto.Server;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @date Thursday December 19, 2019
 * @author garrettbecker
 */
public class App {
    public static void main(String[] args) {
        ServerDao dao = new ServerDaoInMemImpl();
        
        //Create several Servers to manipulate
        Server myServer = new Server();
        myServer.setName("web01");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Dell");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        myServer = new Server();
        myServer.setName("db01");
        myServer.setIp("192.168.3.45");
        myServer.setManufacturer("HP");
        myServer.setRam(16);
        myServer.setNumProcessors(24);
        myServer.setPurchaseDate(LocalDate.parse("2013-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        myServer = new Server();
        myServer.setName("hr124");
        myServer.setIp("192.168.32.111");
        myServer.setManufacturer("IBM");
        myServer.setRam(16);
        myServer.setNumProcessors(12);
        myServer.setPurchaseDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        myServer = new Server();
        myServer.setName("eng16");
        myServer.setIp("192.168.32.56");
        myServer.setManufacturer("HP");
        myServer.setRam(4);
        myServer.setNumProcessors(8);
        myServer.setPurchaseDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        myServer = new Server();
        myServer.setName("eng64");
        myServer.setIp("192.168.34.56");
        myServer.setManufacturer("HP");
        myServer.setRam(8);
        myServer.setNumProcessors(16);
        myServer.setPurchaseDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        //A: gets name of servers associated with Dell manufacturer
        List<Server> dells = dao.getServersByManufacturer("Dell");
        for (Server currentServer : dells) {
            System.out.println(currentServer.getName());
        }
        
        //B: this does the same thing as A
        dells.stream()
                .forEach(s -> System.out.println(s.getName()));
        
        //Manufacturer is the String variable for each record
        //So Manufacturer is the key
        Map<String, List<Server>> serverMap = dao.getAllServersGroupByManufacturer();
        
        //Grabbing the key set AKA Set of manufacturer names
        Set<String> manufacturers = serverMap.keySet();
        
        //Outside lambda: iterates through each manufacturer name from keySet
        //and prints out each manufacturer name
        //Inside lambda: iterates through the servers associated with each manufacturer
        //and prints out the server's name
        manufacturers.stream()
                .forEach(name -> {
                    System.out.println("====================================");
                    System.out.println("Manufacturer: " + name);
                    serverMap.get(name).stream().forEach(s -> System.out.println(s.getName()));
                });
                
    }
}
