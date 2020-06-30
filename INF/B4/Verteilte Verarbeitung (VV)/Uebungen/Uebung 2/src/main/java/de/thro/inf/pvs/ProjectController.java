package de.thro.inf.pvs;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {
    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/hello/{name}")
    public String helloWorld(@PathVariable String name) {
        return "Hello World " + name;
    }

    @GetMapping("projects")
    public ResponseEntity<List<Project>> getALlProjects() {
        Iterable<Project> allProjects = projectRepo.findAll();
        List<Project> allProjectsList = new ArrayList<>();
        allProjects.forEach(allProjectsList::add);

        return ResponseEntity.ok(allProjectsList);
    }

    @GetMapping(value = "/projects/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        Optional<Project> p = projectRepo.findById(id);

        if(p.isPresent()) {
            return ResponseEntity.ok().body(p.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/projects", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createProject(@RequestBody Project p) {
        Project pCreated = projectRepo.save(p);

        try {
            return ResponseEntity.created(new URI("http://localhost:8080/projects/" + pCreated.getId())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity deleteProject(@PathVariable("id") Long id,
                                        @RequestBody Project toDelete) {
        Optional<Project> p = projectRepo.findById(id);

        if(p.isPresent()) {
            if(!p.get().getVersion().equals(toDelete.getVersion())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            projectRepo.delete(p.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value = "Change Projects",
    response = Project.class,
    produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Change OK"),
                            @ApiResponse(code = 404, message = "Project not found")})
    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id,
                                                 @RequestBody Project toUpdate) {
        Optional<Project> p = projectRepo.findById(id);

        if(p.isPresent()) {
            if(!p.get().getVersion().equals(toUpdate.getVersion())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            p.get().setName(toUpdate.getName());
            Project saved = projectRepo.save(p.get());
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
