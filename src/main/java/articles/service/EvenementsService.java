package articles.service;

import java.util.List;

import articles.entity.Articles;
import articles.entity.Evenements;

public interface EvenementsService {
	
	Evenements saveEvenement(Evenements evenement);

	public void updateEvent(Integer EventId,Evenements eventEdit);

	public List<Evenements> getLast6Events();

	

}
