package com.example.model.DAO;

import com.example.exceptions.InvalidLikeException;
import com.example.model.Like;
import com.example.model.Post;

public interface ILikeDAO {
	int addLike(Like like) throws InvalidLikeException;

	void removeLike(Like like) throws InvalidLikeException;
}
