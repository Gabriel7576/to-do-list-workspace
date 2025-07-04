package com.gabriel.todolist.entity.service.constants;

import java.util.Arrays;

public enum TaskPriority {

    BAIXA(PriorityCode.BAIXA, "Baixa"),
    MEDIA(PriorityCode.MEDIA, "Media"),
    ALTA(PriorityCode.ALTA, "Alta");

    private final int codigo;
    private final String descricao;

    TaskPriority(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TaskPriority fromCodigo(int codigo) {
        return Arrays.stream(TaskPriority.values())
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(BAIXA);
    }

    public static TaskPriority fromDescricao(String descricao) {
        return Arrays.stream(TaskPriority.values())
                .filter(p -> p.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElse(BAIXA);
    }

    public static class PriorityCode {
        public static final int BAIXA = 0;
        public static final int MEDIA = 1;
        public static final int ALTA = 2;
    }
}