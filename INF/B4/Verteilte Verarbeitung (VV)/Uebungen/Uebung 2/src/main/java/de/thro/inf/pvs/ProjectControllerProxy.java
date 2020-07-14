package de.thro.inf.pvs;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import javax.persistence.OptimisticLockException;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProjectControllerProxy {
    private String baseURI;
    private String projectCollection;
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public ProjectControllerProxy(String baseURI, String projectCollection) {
        this.baseURI = baseURI;
        this.projectCollection = projectCollection;

        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("Anton", "pferd");
        //headers.setBearerAuth(tokenVonAzure);
    }

    public List<Project> getAllProjects() {
        try {
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ResponseEntity<List> result = restTemplate.exchange(baseURI + projectCollection, HttpMethod.GET, entity, List.class);
            if (result.getStatusCode().equals(HttpStatus.OK)) {
                return result.getBody();
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + result.getStatusCode());
        } catch (HttpClientErrorException e) {
            throw new BackendException("Problem in Backend-Access, Status Code: " + e.getStatusCode());
        }
    }

    public Optional<Project> getProject(Long id) {
        try {
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ResponseEntity<Project> result = restTemplate.exchange(baseURI + projectCollection + "/" + id, HttpMethod.GET, entity, Project.class);
            if (result.getStatusCode().equals(HttpStatus.OK)) {
                Optional<Project> optional = Optional.of(result.getBody());
                return optional;
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + result.getStatusCode());
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return Optional.empty();
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + e.getStatusCode());
        }
    }

    public Project createProject(Project p) {
        try {
            HttpEntity<Project> entity = new HttpEntity<>(p, headers);
            ResponseEntity<String> result = restTemplate.exchange(baseURI + projectCollection, HttpMethod.POST, entity, String.class);

            if (result.getStatusCode() == HttpStatus.CREATED) {
                URI location = result.getHeaders().getLocation();
                String path = location.getPath();
                String idStr = path.substring(path.lastIndexOf('/' + 1));
                Long id = Long.parseLong(idStr);
                Project createdProject = this.getProject(id).orElse(null);
                return createdProject;
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + result.getStatusCode());
        } catch (HttpClientErrorException e) {
            throw new BackendException("Problem in Backend-Access, Status Code: " + e.getStatusCode());
        }
    }

    public void deleteProject(Long id, Project toDelete)  throws OptimisticLockException {
        try {
            HttpEntity<Project> entity = new HttpEntity<>(toDelete, headers);
            ResponseEntity<Void> result = restTemplate.exchange(baseURI + projectCollection + "/" + id, HttpMethod.DELETE, entity, Void.class);

            if(result.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
                return;
            }
                        throw new BackendException("Problem in Backend-Access, Status Code: " + result.getStatusCode());
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.CONFLICT)) {
                throw new OptimisticLockException("Project " + id + " changed by another party");
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + e.getStatusCode());
        }
    }

    public Project updateProject(Long id, Project toUpdate) throws OptimisticLockException {
        try {
            HttpEntity<Project> entity = new HttpEntity<>(toUpdate, headers);
            ResponseEntity<Project> result = restTemplate.exchange(baseURI + projectCollection + "/" + id, HttpMethod.PUT, entity, Project.class);

            if(result.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
                return result.getBody();
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + result.getStatusCode());
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.CONFLICT)) {
                throw new OptimisticLockException("Project " + id + " changed by another party");
            }
            throw new BackendException("Problem in Backend-Access, Status Code: " + e.getStatusCode());
        }
    }
}
