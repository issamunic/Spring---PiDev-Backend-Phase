package tn.esprit.spring.springbatch.csv;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.esprit.spring.entities.csv;

@Component
public class DBWriter implements ItemWriter<csv>{

	@Autowired
	private csvRepository csvRepository;
	
    @Autowired
    public DBWriter (csvRepository csvRepository) {
        this.csvRepository = csvRepository;
    }
	
	@Override
	public void write(List<? extends csv> items) throws Exception {
		System.out.println("Data Saved for items" + items);
		csvRepository.saveAll(items);
		
	}

}
