package tn.esprit.spring.springbatch.csv;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import tn.esprit.spring.entities.csv;

@Component
public class Processor implements ItemProcessor<csv,csv>{

	@Override
	public csv process(csv csv) throws Exception {
		return csv;
	}

}
