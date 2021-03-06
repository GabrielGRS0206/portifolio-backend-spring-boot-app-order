package br.com.controle.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.EntityInUseException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.repository.ClientRepository;

@Component
public class DeleteClientValidation implements Validation {

	private static final String CLIENT_IN_USE = "Cliente de código %d não pode ser removido, pois está em uso";
	@Autowired
	public ClientRepository clienteRepository;

	@Override
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException(MessageException.CLIENT_ID_NOT_FOUND.getValue(), id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(CLIENT_IN_USE, id));
		}
	}

}
