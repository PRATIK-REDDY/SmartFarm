package com.jsp.SmartFarm.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.Image;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.repo.ImageRepo;
import com.jsp.SmartFarm.repo.UserRepo;

@Repository
public class ImageDao {

	@Autowired
	private ImageRepo imageRepo;
	
	@Autowired
	private UserDao userDao;
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	save
	public Image saveDao(Image image) {
		return imageRepo.save(image);
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//	fetch

	public Image fetchImageDao(int id) {
		Optional<Image> db = imageRepo.findById(id);

		if (db.isPresent()) {
			return db.get();
		} else {
			return null;
		}
	}

//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//	update

	public Image updateImageDao(Image image) {

		Optional<Image> db = imageRepo.findById(image.getId());
		Image m = db.get();

		if (db.isPresent()) {

			if (image.getName() == null) {
				image.setName(m.getName());
			}
			if (image.getData() == null) {
				image.setData(m.getData());
			}

			imageRepo.save(image);
			return db.get();
		} else {
			return null;
		}
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	delete
	public Image deleteDao(int userId) {
		User db = userDao.fetchDao(userId);
		int imgId = db.getImage().getId();
		
		db.setImage(null);
		
		userDao.updateDao(db);
		return deleteImageById(imgId);
	}

	private Image deleteImageById(int imgId) {
		Optional<Image> img = imageRepo.findById(imgId);
		
		if (img.isPresent()) {
			imageRepo.deleteById(imgId);
			return img.get();
		} else {
			return null;
		}
	}
}
