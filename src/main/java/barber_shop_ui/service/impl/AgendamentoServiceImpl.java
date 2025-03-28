package barber_shop_ui.service.impl;

import barber_shop_ui.entity.AgendamentoEntity;
import barber_shop_ui.repository.AgendamentoRepository;
import barber_shop_ui.service.AgendamentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository repository;

    @Override
    public AgendamentoEntity salvar(AgendamentoEntity novoAgendamento) {
        return repository.save(novoAgendamento);
    }

    @Override
    public boolean deletarAgendamentoPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // com sucesso
        }
        return false; // não encontrou
    }

    @Override
    public AgendamentoEntity atualizarAgendamento(Long id, AgendamentoEntity agendamentoAtualizado) {
        return repository.findById(id)
                .map(agendamentoExistente -> {
                    agendamentoExistente.setCliente(agendamentoAtualizado.getCliente());
                    agendamentoExistente.setData(agendamentoAtualizado.getData());
                    agendamentoExistente.setHora(agendamentoAtualizado.getHora());
                    agendamentoExistente.setServico(agendamentoAtualizado.getServico());
                    return repository.save(agendamentoExistente);
                }).orElse(null); // retorna null caso não exista ID
    }

    @Override
    public List<AgendamentoEntity> listarTodos() {
        return repository.findAll();
    }
}