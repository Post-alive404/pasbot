package com.dden.pasbot.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "chats_pin")
@AllArgsConstructor
@NoArgsConstructor
public class ChatsPinsEntity {

    @Id
    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "department_id")
    private Integer departmentId;
}
