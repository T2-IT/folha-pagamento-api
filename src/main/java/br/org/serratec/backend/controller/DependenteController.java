package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.repository.DependenteRepository;
import br.org.serratec.backend.service.DependenteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/dependentes")
public class DependenteController {

	@Autowired
	private DependenteRepository dependenteRepository;
	
	@Autowired
	private DependenteService dependenteService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Insere dados de um dependente", notes="Inserir dependente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Dependente cadastrado com sucesso"),
			@ApiResponse(code = 401 , message="Erro de Autenticação"),
			@ApiResponse(code = 403 , message="Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message="Quando ocorre uma exceçãooo")})
	public Dependente inserir(@Valid @RequestBody Dependente dependente) {
		return dependenteRepository.save(dependente);
	}
	@PostMapping("/inserirTodos")
	@ApiOperation(value="Insere dados de vários dependentes", notes="Inserir dependentes")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Dependentes cadastrados com sucesso"),
			@ApiResponse(code = 401 , message="Erro de Autenticação"),
			@ApiResponse(code = 403 , message="Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message="Quando ocorre uma exceção")})
	@ResponseStatus(HttpStatus.CREATED)
	public List<Dependente> inserirTodos(List<Dependente> dependentes) {
		return dependenteRepository.saveAll(dependentes);
	}

	@GetMapping("{id}")
	@ApiOperation(value="Retorna um dependente", notes="Dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um dependente"),
			@ApiResponse(code = 401 , message="Erro de Autenticação"),
			@ApiResponse(code = 403 , message="Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message="Quando ocorre uma exceção")})
	public ResponseEntity<Dependente> buscar(@PathVariable Long id) {
		Optional<Dependente> dependente = dependenteRepository.findById(id);
		if (dependente.isPresent()) {
			return ResponseEntity.ok(dependente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	@ApiOperation(value="Lista todos os dependentes ", notes="Listagem de dependentes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os dependentes"),
			@ApiResponse(code = 401 , message="Erro de Autenticação"),
			@ApiResponse(code = 403 , message="Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message="Quando ocorre uma exceção")})
	public ResponseEntity<List<Dependente>> listar() {
		List<Dependente> dependentes = dependenteRepository.findAll();
		return ResponseEntity.ok(dependentes);
	}
	@PutMapping("{id}")
	@ApiOperation(value="Atualiza dados de um dependente", notes="Atualizar dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente atualizado"),
			@ApiResponse(code = 401 , message="Erro de Autenticação"),
			@ApiResponse(code = 403 , message="Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message="Quando ocorre uma exceção")})
	public ResponseEntity<Dependente> atualizar(@Valid @RequestBody Dependente dependente, @PathVariable Long id){
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependente.setId(id);
		dependente= dependenteRepository.save(dependente);
		return ResponseEntity.ok(dependente);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value="Remove um dependente", notes="Remover dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente removido"),
			@ApiResponse(code = 401 , message="Erro de Autenticação"),
			@ApiResponse(code = 403 , message="Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message="Quando ocorre uma exceção")})
	public ResponseEntity<Void> deletarDependente(@PathVariable Long id){
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependenteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}