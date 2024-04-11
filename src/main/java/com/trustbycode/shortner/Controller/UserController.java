package com.trustbycode.shortner.Controller;

import com.trustbycode.shortner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/link")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/short")
    public ResponseEntity<String> shortenLink(@RequestBody String longLink) {
        String shortLink = userService.shortenLink(longLink);
        return ResponseEntity.ok(shortLink);
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Object> redirectToMainLink(@PathVariable String shortLink){
        String longLink = userService.requestLongLink(shortLink);

        if (longLink !=null){

            return ResponseEntity.status(302)
                    .location(URI.create(longLink))
                    .build();
        }

        else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @DeleteMapping("/delete/{shortLink}")
    public ResponseEntity<String> deleteLink(@PathVariable String shortLink){
        if (userService.deleteLink(shortLink)) {
            return ResponseEntity.ok("Link Successfully Deleted from Database");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
