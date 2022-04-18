package com.revature.model;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Model for converting NotiMessage objects to predefined message strings
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Converter(autoApply = true)
public class NotiMessageConverter implements AttributeConverter<NotiMessage, String> {

	@Override
	public String convertToDatabaseColumn(NotiMessage notiMsg) {
		if (notiMsg == null) {
            return null;
        }
        return notiMsg.getMessage();
	}

	@Override
	public NotiMessage convertToEntityAttribute(String dbData) {
		if (dbData == null) {
            return null;
        }

        return Stream.of(NotiMessage.values())
          .filter(c -> c.getMessage().equals(dbData))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);  // Java 8?
	}

}
