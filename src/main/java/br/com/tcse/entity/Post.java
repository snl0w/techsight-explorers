package br.com.tcse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_POST")
    private Long id;

    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "CATEGORY", length = 125, nullable = false)
    private String category;

    @Column(name = "POST_DATE", nullable = false)
    private LocalDateTime postDate;

    @Column(name = "TAGS", length = 255)
    private String tags;
}

