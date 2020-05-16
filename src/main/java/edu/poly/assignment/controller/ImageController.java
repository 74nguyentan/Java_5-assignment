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

import edu.poly.assignment.model.Staff;
import edu.poly.assignment.services.StaffSevice;

@Controller
public class ImageController {
	@Autowired
	private StaffSevice staffSevice;

	@RequestMapping(value = "getimage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkInage(@PathVariable Long id) {
		Optional<Staff> sop = staffSevice.findById(id);
		if (sop.isPresent()) {
			Staff staff = sop.get();
			try {
				Path filename = Paths.get("images", staff.getPhoto());
				if ((staff.getPhoto()== null || staff.getPhoto().equals("")) || !Files.exists(filename)) {
					return ResponseEntity.notFound().build();
				}
				
				byte[] buffer = Files.readAllBytes(filename);

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
