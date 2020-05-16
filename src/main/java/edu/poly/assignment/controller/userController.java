package edu.poly.assignment.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.assignment.dtos.StaffDto;
import edu.poly.assignment.dtos.UserDto;
import edu.poly.assignment.model.Depart;
import edu.poly.assignment.model.Staff;
import edu.poly.assignment.model.User;
import edu.poly.assignment.services.UserService;

@Controller
@RequestMapping("/user")
public class userController {
	@Autowired
	private UserService UserService;

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("user", UserService.findAll());
		return "user/list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "user/addOrEdit";
	}

	@PostMapping("/saveOrUpdate")
	public String save(ModelMap model, @Validated UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("message", "please input all required fields");
			model.addAttribute("userDto", userDto);

			return "user/addOrEdit";
		}

		// check update hay add new
		if (userDto.getId() != null && userDto.getId() > 0) {

			model.addAttribute("message", "the user update");
		} else {
			model.addAttribute("message", "new user inserted");
		}

		// upload ảnh
		Path path = Paths.get("images/");
		if (userDto.getPhoto() != null && !userDto.getPhoto().isEmpty()) {
			try (InputStream inp = userDto.getPhoto().getInputStream()) {
				Files.copy(inp, path.resolve(userDto.getPhoto().getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				String fileNamee = userDto.getPhoto().getOriginalFilename();
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "Error: " + e.getMessage());
			}
		}
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setBirthday(userDto.getBirthday());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setGioiTinh(userDto.getGioiTinh());
		user.setRole(userDto.getRole());
		
		if (userDto.getPhoto() != null && !userDto.getPhoto().isEmpty()) {
			user.setPhoto(userDto.getPhoto().getOriginalFilename());
		} else {
			Optional<User> s = UserService.findById(user.getId());
			if (s.isPresent()) {
				user.setPhoto(s.get().getPhoto());
			}
		}
		
		UserService.save(user);
		userDto.setId(user.getId());
		model.addAttribute("user", UserService.findAll());
		return "user/list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
		Optional<User> optUser = UserService.findById(id);
		if (optUser.isPresent()) {
			model.addAttribute("userDto", optUser.get());
		} else {
			return list(model);
		}
		
		return "user/addOrEdit";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {
		UserService.deleteById(id);
		return list(model);
	}

	@GetMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<User> list = UserService.findByNameLikeOrderByName(name);
		model.addAttribute("user", list);

		return "user/list";
	}
	
	@ModelAttribute(name = "gioiTinh")
	public String[] getGioiTinh() {
		String[] gioiTinh = { "Nam", "Nu" };

		return gioiTinh;
	}
}
