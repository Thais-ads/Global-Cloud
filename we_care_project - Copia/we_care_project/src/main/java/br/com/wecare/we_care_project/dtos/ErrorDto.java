package br.com.wecare.we_care_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String mensagem;
    private String detalhes;
}
