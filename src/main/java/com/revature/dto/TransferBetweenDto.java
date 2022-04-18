package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferBetweenDto {

	Integer fromAccount;
	Integer toAccount;
	Double amount;
	
	
}
