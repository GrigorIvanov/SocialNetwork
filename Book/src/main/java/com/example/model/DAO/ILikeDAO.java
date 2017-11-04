package com.example.model.DAO;

import com.example.exceptions.InvalidLikeException;
import com.example.model.Like;
import com.example.model.Post;

public interface ILikeDAO {
	void addLike(Like like) throws InvalidLikeException;

	void removeLike(Like like) throws InvalidLikeException;
}
