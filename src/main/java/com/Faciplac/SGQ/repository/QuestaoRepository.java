package com.Faciplac.SGQ.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Faciplac.SGQ.model.Disciplina;
import com.Faciplac.SGQ.model.Questao;
import com.Faciplac.SGQ.model.Usuario;
import com.Faciplac.SGQ.util.TaxonomiaDeBloomEnum;

@Repository
public interface QuestaoRepository extends CrudRepository<Questao, Long> {
	public List<Questao> findAllByDisciplina(Disciplina disciplina);
	public List<Questao> findAllByDisciplinaAndSituacao(Disciplina disciplina, String situacao);
	public List<Questao> findAllByClassificacaoTaxonomiaBloom(TaxonomiaDeBloomEnum classificacaoTaxonomiaBloom);
	public List<Questao> findAllBySituacao(String situacao);
	public List<Questao> findAllByUsuario(Usuario usuario);
	public List<Questao> findTop15ByClassificacaoTaxonomiaBloomAndSituacaoAndDisciplinaOrderByDataDeUsoAsc(TaxonomiaDeBloomEnum classificacaoTaxonomiaBloom, String situacao, Disciplina disciplina);
	public List<Questao> findAll();
}
