package com.m0wn1ka.MyShareIt.models;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(schema = "file_uploads")
public class FileUploads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "uuid")
    public   String uuid;
    @Column(name="generic_name")
    public String genericName;
    @Column(name = "s3_path")
    public String s3Path;
    @Column(name = "data_type")
    public String dataType;


}
