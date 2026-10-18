package com.example.devops.controller;

import com.example.devops.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final Map<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public TaskController() {
        save(new Task(counter.getAndIncrement(), "Set up Git repo", "Init and branch strategy", true));
        save(new Task(counter.getAndIncrement(), "Configure Jenkins", "Declarative pipeline with Maven", false));
        save(new Task(counter.getAndIncrement(), "Write Ansible playbook", "Role, vars, handlers, templates", false));
        save(new Task(counter.getAndIncrement(), "Deploy to Kubernetes", "Deployment + Service + ConfigMap", false));
    }

    private void save(Task t) { store.put(t.getId(), t); }

    @GetMapping
    public List<Task> getAll() { return new ArrayList<>(store.values()); }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getOne(@PathVariable Long id) {
        Task t = store.get(id);
        return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task t) {
        if (t.getTitle() == null || t.getTitle().isBlank()) return ResponseEntity.badRequest().build();
        t.setId(counter.getAndIncrement());
        save(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return store.remove(id) != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
