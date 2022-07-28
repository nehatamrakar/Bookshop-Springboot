package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.money.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String authorName;
    @Embedded
    private Money price;

    public Book(String name, String authorName, Money price) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
    }

    public BookResponse toResponse() {
        return BookResponse.builder()
                .id(id)
                .name(name)
                .authorName(authorName)
                .price(price)
                .build();
    }


    public static Book update(Book book,CreateBookRequest bookRequest) {
        if(Objects.nonNull(bookRequest.getAuthorName()) &&
                !"".equalsIgnoreCase(bookRequest.getAuthorName())){
            book.setAuthorName(bookRequest.getAuthorName());
        }
        if(Objects.nonNull(bookRequest.getName()) &&
                !"".equalsIgnoreCase(bookRequest.getName())){
            book.setName(bookRequest.getName());
        }
        if(Objects.nonNull(bookRequest.getPrice())){
            if(Objects.nonNull(bookRequest.getPrice().getCurrency()) &&
                    !"".equalsIgnoreCase(bookRequest.getPrice().getCurrency())){
                book.updateCurrency(bookRequest.getPrice().getCurrency());
            }
            if(Objects.nonNull(bookRequest.getPrice().getAmount())){
                book.updateAmount(bookRequest.getPrice().getAmount());
            }
        }
        return book;
    }

    private void setName(String name) {
    }

    private void setAuthorName(String authorName) {
        
    }

    private void updateAmount(double amount) {
        this.price.setAmount(amount);
    }

    private void updateCurrency(String currency) {
        this.price.setCurrency(currency);
    }

}
