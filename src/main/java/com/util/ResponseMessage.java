package com.util;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {

	public int status;
	public String message;
	public Timestamp hittime;

}
