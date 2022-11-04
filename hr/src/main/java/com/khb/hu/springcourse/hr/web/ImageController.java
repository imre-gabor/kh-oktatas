package com.khb.hu.springcourse.hr.web;

import org.apache.coyote.Response;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @GetMapping("/{id}/{imageid}")
    public ResponseEntity<Resource> getImage(@PathVariable String id, @PathVariable String imageid){
        return ResponseEntity
                .status(200)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileSystemResource("/temp/kh-hr/employee/" + id + "/" + imageid));
    }
}
