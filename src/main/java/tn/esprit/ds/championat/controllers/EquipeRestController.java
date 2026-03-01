package tn.esprit.ds.championat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.services.IEquipeService;

import java.util.List;

/**
 * REST Controller – Gestion des équipes
 * Base URL : http://localhost:8089/championnat/equipe
 */
@Tag(name = "Equipe", description = "API de gestion des équipes du championnat")
@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {

    IEquipeService equipeService;

    // http://localhost:8089/championnat/equipe/retrieve-all-equipes
    @Operation(summary = "Lister toutes les équipes",
               description = "Retourne la liste complète des équipes enregistrées.")
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Equipe.class))))
    @GetMapping("/retrieve-all-equipes")
    public List<Equipe> getEquipes() {
        return equipeService.listEquipes();
    }

    // http://localhost:8089/championnat/equipe/retrieve-equipe/{idEquipe}
    @Operation(summary = "Récupérer une équipe par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipe trouvée",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Equipe.class))),
            @ApiResponse(responseCode = "404", description = "Équipe introuvable", content = @Content)
    })
    @GetMapping("/retrieve-equipe/{idEquipe}")
    public Equipe retrieveEquipe(
            @Parameter(description = "Identifiant de l'équipe", required = true, example = "1")
            @PathVariable Long idEquipe) {
        return equipeService.recupererEquipe(idEquipe);
    }

    // http://localhost:8089/championnat/equipe/add-equipe
    @Operation(summary = "Ajouter une équipe",
               description = "Crée une nouvelle équipe dans le championnat.")
    @ApiResponse(responseCode = "200", description = "Équipe créée avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Equipe.class)))
    @PostMapping("/add-equipe")
    public Equipe addEquipe(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objet équipe à créer (idEquipe ignoré à la création)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Equipe.class)))
            @RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }

    // http://localhost:8089/championnat/equipe/modify-equipe
    @Operation(summary = "Modifier une équipe existante")
    @ApiResponse(responseCode = "200", description = "Équipe modifiée avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Equipe.class)))
    @PutMapping("/modify-equipe")
    public Equipe modifyEquipe(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Équipe avec les nouvelles valeurs (idEquipe obligatoire)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Equipe.class)))
            @RequestBody Equipe equipe) {
        return equipeService.modifierEquipe(equipe);
    }

    // http://localhost:8089/championnat/equipe/remove-equipe/{idEquipe}
    @Operation(summary = "Supprimer une équipe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipe supprimée", content = @Content),
            @ApiResponse(responseCode = "404", description = "Équipe introuvable", content = @Content)
    })
    @DeleteMapping("/remove-equipe/{idEquipe}")
    public void removeEquipe(
            @Parameter(description = "Identifiant de l'équipe à supprimer", required = true, example = "1")
            @PathVariable Long idEquipe) {
        equipeService.supprimerEquipe(idEquipe);
    }
}
