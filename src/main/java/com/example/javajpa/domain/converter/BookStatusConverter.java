package com.example.javajpa.domain.converter;

import com.example.javajpa.repository.dto.BookStatus;
import com.sun.jndi.ldap.spi.LdapDnsProvider;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

// 두개의 함수 모두 구현되어야한다.
// 그렇지 않으면 데이터가 유실될수 있다.
//@Converter
@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
//        만약 리턴 null 처리로 그냥 두면 findAll() 함수로 데이터를 get 했음에도 update 구문이 발생해 null 값으로 수정된다. (에러 발생 예시)
//        return null;
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
