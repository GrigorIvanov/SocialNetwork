package com.example.model.DAO;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.InvalidLikeException;
import com.example.exceptions.UserExeption;
import com.example.model.Like;
@Component
public interface ILikeDAO {
	void clickLike(Like like) throws InvalidLikeException, UserExeption, InvalidDataException;

}
