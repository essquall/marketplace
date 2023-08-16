package com.example.marketplace.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pictures")
@Data
public class Picture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "fileName", columnDefinition = "TEXT")
    private String fileName;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(name = "bytes", columnDefinition="LONGBLOB")
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
