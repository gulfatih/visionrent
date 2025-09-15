package com.visionrent.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "t_imagefile")
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String type;

    private long length;

    @OneToOne(cascade = CascadeType.ALL) // ImageFile silinirse ImageData da silinsin
    private ImageData imageData;

    public ImageFile(String name, String type,ImageData imageData){
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.length = imageData.getData().length; // image file uzunluğu imageData'dan çekiliyor.
    }



}
