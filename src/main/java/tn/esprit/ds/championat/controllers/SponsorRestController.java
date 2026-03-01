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
import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.services.ISponsorService;

import java.util.List;

/**
 * REST Controller – Gestion des sponsors
 * Base URL : http://localhost:8089/championnat/sponsor
 */
@Tag(name = "Sponsor", description = "API de gestion des sponsors du championnat")
@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
public class SponsorRestController {

    ISponsorService sponsorService;

    // http://localhost:8089/championnat/sponsor/retrieve-all-sponsors
    @Operation(summary = "Lister tous les sponsors",
               description = "Retourne la liste complète des sponsors enregistrés.")
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Sponsor.class))))
    @GetMapping("/retrieve-all-sponsors")
    public List<Sponsor> getSponsors() {
        return sponsorService.listSponsors();
    }

    // http://localhost:8089/championnat/sponsor/retrieve-sponsor/{idSponsor}
    @Operation(summary = "Récupérer un sponsor par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sponsor trouvé",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Sponsor.class))),
            @ApiResponse(responseCode = "404", description = "Sponsor introuvable", content = @Content)
    })
    @GetMapping("/retrieve-sponsor/{idSponsor}")
    public Sponsor retrieveSponsor(
            @Parameter(description = "Identifiant du sponsor", required = true, example = "1")
            @PathVariable Long idSponsor) {
        return sponsorService.recupererSponsor(idSponsor);
    }

    // http://localhost:8089/championnat/sponsor/add-sponsor
    @Operation(summary = "Ajouter un sponsor",
               description = "Crée un nouveau sponsor. dateCreation est initialisée automatiquement,"
                       + " archived et bloquerContrat sont mis à false.")
    @ApiResponse(responseCode = "200", description = "Sponsor créé avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Sponsor.class)))
    @PostMapping("/add-sponsor")
    public Sponsor addSponsor(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objet sponsor à créer (idSponsor ignoré à la création)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Sponsor.class)))
            @RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    // http://localhost:8089/championnat/sponsor/add-sponsors
    @Operation(summary = "Ajouter une liste de sponsors",
               description = "Crée plusieurs sponsors en une seule requête.")
    @ApiResponse(responseCode = "200", description = "Sponsors créés avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Sponsor.class))))
    @PostMapping("/add-sponsors")
    public List<Sponsor> addSponsors(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Liste de sponsors à créer",
                    required = true,
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Sponsor.class))))
            @RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    // http://localhost:8089/championnat/sponsor/modify-sponsor
    @Operation(summary = "Modifier un sponsor existant",
               description = "Met à jour les informations d'un sponsor. dateDerniereModification est actualisée automatiquement.")
    @ApiResponse(responseCode = "200", description = "Sponsor modifié avec succès",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Sponsor.class)))
    @PutMapping("/modify-sponsor")
    public Sponsor modifySponsor(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Sponsor avec les nouvelles valeurs (idSponsor obligatoire)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Sponsor.class)))
            @RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    // http://localhost:8089/championnat/sponsor/remove-sponsor/{idSponsor}
    @Operation(summary = "Supprimer un sponsor")
    @ApiResponse(responseCode = "200", description = "Sponsor supprimé", content = @Content)
    @DeleteMapping("/remove-sponsor/{idSponsor}")
    public void removeSponsor(
            @Parameter(description = "Identifiant du sponsor à supprimer", required = true, example = "1")
            @PathVariable Long idSponsor) {
        sponsorService.supprimerSponsor(idSponsor);
    }

    // http://localhost:8089/championnat/sponsor/archiver-sponsor/{idSponsor}
    @Operation(summary = "Archiver un sponsor",
               description = "Passe le champ archived du sponsor à true.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Résultat de l'archivage",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Sponsor introuvable", content = @Content)
    })
    @PutMapping("/archiver-sponsor/{idSponsor}")
    public Boolean archiverSponsor(
            @Parameter(description = "Identifiant du sponsor à archiver", required = true, example = "1")
            @PathVariable Long idSponsor) {
        return sponsorService.archiverSponsor(idSponsor);
    }
}
