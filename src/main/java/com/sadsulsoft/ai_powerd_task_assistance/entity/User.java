package com.sadsulsoft.ai_powerd_task_assistance.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Store the role as a string (e.g., "ADMIN")
    private Role role;           // New field for the user's role

    // --- Relationships ---
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> authoredTasks = new HashSet<>();

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> assignedTasks = new HashSet<>();
    
    // --- Getters and Setters ---
    // (Keep existing getters and setters, and add one for role)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Set<Task> getAuthoredTasks() { return authoredTasks; }
    public void setAuthoredTasks(Set<Task> authoredTasks) { this.authoredTasks = authoredTasks; }
    public Set<Task> getAssignedTasks() { return assignedTasks; }
    public void setAssignedTasks(Set<Task> assignedTasks) { this.assignedTasks = assignedTasks; }

    // --- Methods required by UserDetails ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This tells Spring Security about the user's role.
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}