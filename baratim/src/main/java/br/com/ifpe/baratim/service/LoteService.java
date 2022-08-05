package br.com.ifpe.baratim.service;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LoteService {

	public Long diferencaHoras(LocalDateTime dataEntrega, LocalDateTime dataAtual) throws ParseException{
		
		return Duration.between(dataEntrega, dataAtual).toMinutes();
	}
	
}
