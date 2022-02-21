package com.me.nalsinterview.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LocalDateTime getResultTime() {
		return resultTime;
	}

	public void setResultTime(LocalDateTime resultTime) {
		this.resultTime = resultTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@JsonProperty("data")
    private T data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("result_time")
    private LocalDateTime resultTime;

}
