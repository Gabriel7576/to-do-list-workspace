package com.gabriel.todolist.entity.service.constants;

import java.util.Arrays;

public enum TaskStatus {

    PENDENTE(StatusCode.PENDENTE, "Pendente"),
    CONCLUIDO(StatusCode.CONCLUIDO, "Concluido");

    private final int codigo;
    private final String descricao;

    TaskStatus(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TaskStatus fromCodigo(int codigo) {
        return Arrays.stream(TaskStatus.values())
                .filter(status -> status.getCodigo() == codigo)
                .findFirst()
                .orElse(PENDENTE);
    }

    public static TaskStatus fromDescricao(String descricao) {
        return Arrays.stream(TaskStatus.values())
                .filter(status -> status.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElse(PENDENTE);
    }

    public static class StatusCode {
        public static final int PENDENTE = 0;
        public static final int CONCLUIDO = 1;
    }
}
