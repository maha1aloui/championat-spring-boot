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
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.services.IPiloteService;

import java.util.List;

/**
 * REST Controller – Gestion des pilotes
 * Base URL : http://localhost:8089/championnat/pilote
 */
@Tag(name = "Pilote", description = "API de gestion des pilotes du championnat")
@RestController
@AllArgsConstructor
@RequestMapping("/pilote")
public class PiloteRestController {

    IPiloteService piloteService;

    // http://localhost:8089/championnat/pilote/retrieve-all-pilotes
    @Operation(summary = "Lister tous les pilotes",
               description = "Retourne la liste complète des pilotes enregistrés.")
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Pilote.class))))
    @GetMapping("/retrieve-all-pilotes")
    public List<Pilote> getPilotes() {
        return piloteService.listPilotes();
    }

    // http://localhost:8089/championnat/pilote/retrieve-pilote/{idPilote}
    @Operation(summary = "Récupérer un pilote par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pilote trouvé",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pilote.class))),
            @ApiResponse(responseCode = "404", description = "Pilote introuvable", content = @Content)
    })
    @GetMapping("/retrieve-pilote/{idPilote}")
    public Pilote retrievePilote(
            @Parameter(description = "Identifiant du pilote", required = true, example = "1")
            @PathVariable Long idPilote) {
        return piloteService.recupererPilote(idPilote);
    }

    // http://localhost:8089/championnat/pilote/add-pilote
    @Operation(summary = "Ajouter un pilote",
               description = "Crée un nouveau pilote. Retourne un message de confirmation.")
    @ApiResponse(responseCode = "200", description = "Pilote ajouté avec succès",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(type = "string", example = "Pilote Verstappen Max (Numéro: 1) ajouté avec succès!")))
    @PostMapping("/add-pilote")
    public String addPilote(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objet pilote à créer (idPilote ignoré à la création)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Pilote.class)))
            @RequestBody Pilote p) {
        return piloteService.addPilote(p);
    }

    // http://localhost:8089/championnat/pilote/modify-pilote
    @Operation(summary = "Modifier un pilote existant")
    @ApiResponse(responseCode = "200", description = "Pilote modifié avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pilote.class)))
    @PutMapping("/modify-pilote")
    public Pilote modifyPilote(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pilote avec les nouvelles valeurs (idPilote obligatoire)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Pilote.class)))
            @RequestBody Pilote pilote) {
        return piloteService.modifierPilote(pilote);
    }

    // http://localhost:8089/championnat/pilote/remove-pilote/{idPilote}
    @Operation(summary = "Supprimer un pilote")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pilote supprimé", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pilote introuvable", content = @Content)
    })
    @DeleteMapping("/remove-pilote/{idPilote}")
    public void removePilote(
            @Parameter(description = "Identifiant du pilote à supprimer", required = true, example = "1")
            @PathVariable Long idPilote) {
        piloteService.supprimerPilote(idPilote);
    }

    // http://localhost:8089/championnat/pilote/affecter-pilote-equipe/{idPilote}/{idEquipe}
    @Operation(summary = "Affecter un pilote à une équipe",
               description = "Associe un pilote existant à une équipe.")
    @ApiResponse(responseCode = "200", description = "Affectation réussie",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pilote.class)))
    @PutMapping("/affecter-pilote-equipe/{idPilote}/{idEquipe}")
    public Pilote affecterPiloteEquipe(
            @Parameter(description = "Identifiant du pilote", required = true, example = "1")
            @PathVariable Long idPilote,
            @Parameter(description = "Identifiant de l'équipe", required = true, example = "1")
            @PathVariable Long idEquipe) {
        return piloteService.affecterPiloteAEquipe(idPilote, idEquipe);
    }
}
