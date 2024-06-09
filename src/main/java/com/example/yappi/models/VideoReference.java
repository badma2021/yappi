package com.example.yappi.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@Table(name = "video")
public class VideoReference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqGen", sequenceName = "video_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    @ToString.Exclude
    private Long id;
    public VideoReference() {
    }
//    @ToString.Exclude
//    private LocalDateTime createdAt;
    @Column
    private String link;
    @Column
    private String description;
    @Column
    private String hash;
}
