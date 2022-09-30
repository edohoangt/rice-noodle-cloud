package rccloud.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rccloud.RiceNoodle;
import rccloud.data.RiceNoodleRepository;

@RestController
@RequestMapping(path = "/api/rice-noodles", produces = "application/json")
public class RiceNoodleRestController {
	
	@Autowired
	private RiceNoodleRepository riceNoodleRepository;
	
	public RiceNoodleRestController(RiceNoodleRepository riceNoodleRepository) {
		this.riceNoodleRepository = riceNoodleRepository;
	}
	
	@GetMapping(params = "recent")
	public Iterable<RiceNoodle> recentNoodles() {
		PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return riceNoodleRepository.findAll(pageRequest).getContent();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RiceNoodle> noodleById(@PathVariable("id") Long id) {
		Optional<RiceNoodle> noodleOptional = riceNoodleRepository.findById(id);
		if (noodleOptional.isPresent()) {
			return new ResponseEntity<>(noodleOptional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public RiceNoodle postNoodle(@RequestBody RiceNoodle noodle) {
		return riceNoodleRepository.save(noodle);
	}
	
}
