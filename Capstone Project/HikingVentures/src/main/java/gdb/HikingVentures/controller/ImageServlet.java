package gdb.HikingVentures.controller;

import gdb.HikingVentures.entities.Location;
import gdb.HikingVentures.service.HVService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * Capstone Project
 * @author garrettbecker
 */

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
    @Autowired
    HVService service;
    
    //Base directory for saving images
    private String imagePath;

    public void init() throws ServletException {

        this.imagePath = "img/";
    }
    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get key pieces of info for the file
        Part filePart = request.getPart("filePath");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        //Save the photo to the img directory
        BufferedImage image = null;
        try {
            
            image = ImageIO.read(fileName);
            
            ImageIO.write(image, "jpg",new File("C:\\out.jpg"));
            ImageIO.write(image, "gif",new File("C:\\out.gif"));
            ImageIO.write(image, "png",new File("C:\\out.png"));
            
        } catch (IOException e) {
                    e.printStackTrace();
        }
        
        filePart.write(imagePath + fileName);
        File image = new File(imagePath + fileName);
        OutputStream out = new FileOutputStream(filePart);
    }*/
}
