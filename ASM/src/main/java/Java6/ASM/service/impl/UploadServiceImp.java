package Java6.ASM.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Java6.ASM.service.UploadService;
import jakarta.servlet.ServletContext;

@Service
public class UploadServiceImp implements UploadService {
    @Autowired
    ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
        System.out.println("-----" + folder);
        File dir = new File("D:\\Java6\\ASM\\ASM\\src\\main\\resources\\static\\assets\\" + folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dir2 = new File(app.getRealPath("/assets/" + folder));
        if (!dir2.exists()) {
            dir2.mkdirs();
        }
        // System.out.println("---dir-"+ dir);
        // System.out.println("--------" + file.getOriginalFilename());
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        // System.out.println("---s--" + s);
        String name = Integer.toHexString(s.hashCode()).concat(s.substring(s.lastIndexOf(".")));
        // System.out.println("---name--" + name);
        try {
            File saveFile = new File(dir, name);
            File saveFile2 = new File(dir2, name);

            file.transferTo(saveFile);
            // file.transferTo(saveFile2);

            // System.out.println( "as"+saveFile.getAbsolutePath());
            return saveFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
