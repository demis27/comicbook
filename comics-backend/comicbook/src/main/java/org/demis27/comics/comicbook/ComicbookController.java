package org.demis27.comics.comicbook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicbookController {

    @RequestMapping("/comicbook")
    String getAll() {
        return "Hello";
    }
}
