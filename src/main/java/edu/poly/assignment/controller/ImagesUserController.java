package edu.poly.assignment.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.assignment.model.User;
import edu.poly.assignment.services.UserService;

@Controller
public class ImagesUserController {
@Autowired
	private UserService UserService;

	@RequestMapping(value = "getimages/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkInages(@PathVariable Long id) {
		
		Optional<User> sopp = UserService.findById(id);
		if (sopp.isPresent()) {
			
			User user = sopp.get();
			try {
				Path fileNamee = Paths.get("images", user.getPhoto());
				if ((user.getPhoto()== null || user.getPhoto().equals("")) || !Files.exists(fileNamee)) {
					return ResponseEntity.notFound().build();
				}
				
				byte[] buffer = Files.readAllBytes(fileNamee);

				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/jpg"))
						.body(bsr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.badRequest().build();
	}
}
