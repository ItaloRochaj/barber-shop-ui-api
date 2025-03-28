package barber_shop_ui.service;

import barber_shop_ui.entity.AgendamentoEntity;
import java.util.List;

public interface AgendamentoService {
    List<AgendamentoEntity> listarTodos();

    AgendamentoEntity salvar(AgendamentoEntity novoAgendamento);

    boolean deletarAgendamentoPorId(Long id);

    AgendamentoEntity atualizarAgendamento(Long id, AgendamentoEntity agendamentoAtualizado);
}