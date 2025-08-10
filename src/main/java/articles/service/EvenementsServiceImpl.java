package articles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import articles.entity.Articles;
import articles.entity.Evenements;
import articles.repository.ArticlesRepository;
import articles.repository.EvenementsRepository;

@Service
public class EvenementsServiceImpl implements EvenementsService{

	
	 @Autowired
	    private EvenementsRepository evenementsRepository;
	 
	 @Override
	    public Evenements saveEvenement(Evenements evenement)
	    {
	        return evenementsRepository.save(evenement);
	    }
		@Override
		public void updateEvent(Integer EventId, Evenements eventEdit) {
			Evenements event = evenementsRepository.findById(EventId).get();
			event.setTitre(eventEdit.getTitre());
			event.setSoustitre(eventEdit.getSoustitre());
			event.setContenus(eventEdit.getContenus());
			  if(!eventEdit.getSary().isBlank())
			  {
				event.setSary(eventEdit.getSary());
			  }
			  
			  event.setCategorie(eventEdit.getCategorie());
			//  event.setDateevenement(eventEdit.getDateevenement());
			  evenementsRepository.save(event);
		   }

		   	@Override
		public List<Evenements> getLast6Events() {
			return evenementsRepository.findTop6ByOrderByDateevenementDesc();
		}
   
}
