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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats_cities")
public class ChatsCitiesEntity {

    @Id
    @Column(name = "chat_is")
    private Long chatId;

    @Column(name = "city_id")
    private Integer cityId;
}
