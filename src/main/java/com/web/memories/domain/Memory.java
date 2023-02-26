package com.web.memories.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memory {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private LocalDate dateEntry;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @ManyToOne
    private Author author;
    @Transient
    private Long authorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(LocalDate dateEntry) {
        this.dateEntry = dateEntry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getAuthorId() {
        return this.author.getId();
    }
}
