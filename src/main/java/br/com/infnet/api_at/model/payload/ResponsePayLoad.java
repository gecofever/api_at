package br.com.infnet.api_at.model.payload;

import lombok.Data;

@Data
public class ResponsePayLoad {
    private String message;

    public ResponsePayLoad(String message) {
        this.message = message;
    }
}
