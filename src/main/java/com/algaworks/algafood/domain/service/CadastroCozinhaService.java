package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d ";
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, id)
			);
		} catch (DataIntegrityViolationException e) {
			throw  new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id)
			);
		}
	}

	public Cozinha buscarOuFalhar(@Valid  Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(()-> new CozinhaNaoEncontradaException(cozinhaId));
	}
}
