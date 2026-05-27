package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReferenceSequences {

    @Id
    private Long id = 1L; // Use a constant ID since we only need one record
    private int sequence;

    public Long getId() {
        return id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}