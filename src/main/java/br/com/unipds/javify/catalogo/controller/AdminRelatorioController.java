package br.com.unipds.javify.catalogo.controller;

import br.com.unipds.javify.catalogo.service.RelatorioAlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin/relatorios")
public class AdminRelatorioController {

    private final RelatorioAlbumService relatorioAlbumService;

    public AdminRelatorioController(RelatorioAlbumService relatorioAlbumService) {
        this.relatorioAlbumService = relatorioAlbumService;
    }

    @GetMapping("duracao-genero")
    public ResponseEntity<RelatorioAlbumService.ResultadoRelatorioDuracaoGenero> obterRelatorioDuracaoPorGenero(
            @RequestParam String genero) {
        var resultado = relatorioAlbumService.calcularDuracaoTotalPorGenero(genero);
        if (resultado == null) {
            resultado = new RelatorioAlbumService.ResultadoRelatorioDuracaoGenero(0L, 0L);
        }
        return ResponseEntity.ok(resultado);
    }
}
