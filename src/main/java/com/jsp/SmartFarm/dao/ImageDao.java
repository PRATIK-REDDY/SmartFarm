package com.jsp.SmartFarm.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.Image;

import com.jsp.SmartFarm.repo.ImageRepo;

@Repository
public class ImageDao {

	@Autowired
	private ImageRepo imageRepo;

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
			if (image.getName() == null) {
				image.setName(m.getName());
			}

			imageRepo.save(image);
			return db.get();
		} else {
			return null;
		}
	}
}
