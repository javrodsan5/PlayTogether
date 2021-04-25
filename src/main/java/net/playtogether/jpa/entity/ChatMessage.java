package net.playtogether.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="chat_message")
@Getter @Setter
public class ChatMessage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private Usuario usuario;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "message", length = 100000000)
    @NotBlank(message = "El mensaje es un campo requerido.")
    private String message;
}
