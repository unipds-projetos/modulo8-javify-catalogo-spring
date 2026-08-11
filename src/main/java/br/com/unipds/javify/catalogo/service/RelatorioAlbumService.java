package br.com.unipds.javify.catalogo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class RelatorioAlbumService {

    private final MongoTemplate mongoTemplate;

    public RelatorioAlbumService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ResultadoRelatorioDuracaoGenero calcularDuracaoTotalPorGenero(String genero) {
        var pipeline = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("genero").regex(genero, "i")),
                Aggregation.unwind("faixas"),
                Aggregation.group()
                        .sum("faixas.duracaoSegundos").as("duracaoTotalSegundos")
                        .count().as("totalFaixas")
        );

        AggregationResults<ResultadoRelatorioDuracaoGenero> resultados = mongoTemplate.aggregate(pipeline, "albuns", ResultadoRelatorioDuracaoGenero.class);
        return resultados.getUniqueMappedResult();
    }

    public record ResultadoRelatorioDuracaoGenero (Long duracaoTotalSegundos, Long totalFaixas) {}

}
