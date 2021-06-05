package com.example.demo.user;

import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> index() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User show(@PathVariable Integer id) {
        final Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User with id-" + id + " not found");
        return user.get();
    }

    @PostMapping("/users")
    public ResponseEntity<?> store(@Valid @RequestBody User user) {
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Integer id) {
        if (!userRepository.findById(id).isPresent())
            throw new UserNotFoundException("User with id-" + id + " not found");
        userRepository.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> fetchPosts(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User with id-" + id + " not found");
        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<?> createPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User with id-" + id + " not found");
        post.setUser(user.get());
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }


}
