package articles.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import articles.dto.ArticlesDto;
import articles.dto.EvenementsDto;
import articles.entity.Articles;
import articles.entity.Evenements;
import articles.service.EvenementsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/evenements/api/")
public class ApiEvenementsController {

        	@Autowired
	    private EvenementsService evenementsService;

       @GetMapping("/latest-events")
        public ResponseEntity<List<EvenementsDto>> latestevents() throws Exception{
             
             
              try {
               
                List<Evenements> events = evenementsService.getLast6Events();
               List<EvenementsDto> listeEventsDto = events.stream()
                       .map(event -> {
                        EvenementsDto eventsDto = new EvenementsDto();
                        eventsDto.setId(event.getId());
                        eventsDto.setTitre(event.getTitre());
                        eventsDto.setSoustitre(event.getSoustitre());
                        eventsDto.setContenus(event.getContenus());
                        eventsDto.setDateevenement(event.getDateevenement());
                        eventsDto.setSary(event.getSary());
                        eventsDto.setCategorie(event.getCategorie().getNomCategorie());
                           return eventsDto;
                       })
                       .collect(Collectors.toList());
               
               return ResponseEntity.ok(listeEventsDto);
           } catch (Exception e) {
               // Handle exceptions appropriately (e.g., logging, returning error response)
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
           }

    



}
