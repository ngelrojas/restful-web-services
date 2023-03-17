package com.rednodes.rest.webservices.restfulwebsevices.user;

import com.rednodes.rest.webservices.restfulwebsevices.jpa.UserRepository;
import com.rednodes.rest.webservices.restfulwebsevices.post.Post;
import com.rednodes.rest.webservices.restfulwebsevices.post.PostRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

//    private UserDaoService service;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid User user) {
        User saveUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }
        post.setUser(user.get());
        Post savePost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savePost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
