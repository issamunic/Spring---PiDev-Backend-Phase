package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Activity;
import tn.esprit.spring.repository.ActivityRepo;
import tn.esprit.spring.serviceInterface.IActivityService;

@Service
@Slf4j
public class ActivityService implements IActivityService {

	@Autowired
	ActivityRepo ActivityRepo;

	@Override
	public List<Activity> getAll() {
		try {
			List<Activity> Activitys = (List<Activity>) ActivityRepo.findAll();
			for (Activity Activity : Activitys) {
				log.info(" Activity : " + Activity);
			}
			return Activitys;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Activity add(Activity Activity) {
		try {
			return ActivityRepo.save(Activity);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Activity update(Activity Activity) {
		try {
			ActivityRepo.save(Activity);
			return Activity;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long Activityid) {
		try {
			ActivityRepo.deleteById(Activityid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public Activity getById(Long Activityid) {
		try {
			Activity Activity = ActivityRepo.findById(Activityid).orElse(null);
			return Activity;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

}