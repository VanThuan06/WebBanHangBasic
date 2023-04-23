package Java6.ASM.Rest.Controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import Java6.ASM.service.UploadService;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin("*")
@RestController
public class UploadRestController {

    @Autowired
    UploadService uploadService;

    @PostMapping("/rest/upload/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file,
            @PathVariable("folder") String folder) {
        File saveFile = uploadService.save(file, folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        System.out.println("-----" + saveFile.getName());
        System.out.println("-----" + saveFile.length());
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        System.out.println("====node=== "+node);
        return node;
    }

}
