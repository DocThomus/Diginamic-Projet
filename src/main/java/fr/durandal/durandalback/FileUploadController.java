package fr.durandal.durandalback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import fr.durandal.durandalback.storage.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @PostMapping(value = "/uploadImage", consumes = MediaType.ALL_VALUE)
    @ResponseStatus( HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
    public void adb(@RequestBody MultipartFile file) {
        storageService.store(file);
    }

}
