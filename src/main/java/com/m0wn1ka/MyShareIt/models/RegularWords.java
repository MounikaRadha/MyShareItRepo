package com.m0wn1ka.MyShareIt.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "regular_words")
public class RegularWords
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "regular_word")
    public String regularWord;

    @Column(name = "already_used_status")
    public boolean alreadyUsedStatus=Boolean.FALSE;
}
