package net.playtogether.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat")
@Getter @Setter
public class Chat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chat_type_id")
    private ChatType chatType;

    @JoinColumn(name = "meeting_id")
    @ManyToOne
    private Meeting meeting;

    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;

    @JoinColumn(name = "user1_id")
    @ManyToOne
    private Usuario user1;

    @JoinColumn(name = "user2_id")
    @ManyToOne
    private Usuario user2;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChatMessage> messages;

    @Transient
    public LocalDateTime getDateTimeLastMessage() {
        return this.messages.get(this.messages.size()-1).getDate();
    }
    
}
