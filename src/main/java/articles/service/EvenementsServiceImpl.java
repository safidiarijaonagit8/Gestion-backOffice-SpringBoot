package articles.service;

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
}
