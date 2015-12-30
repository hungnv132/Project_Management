package com.hungnv132.core.dao;

import java.util.List;

import com.hungnv132.core.domain.Position;

public interface PositionDAO {

	List<Position> findAll();
	
	Position findById(int positionId);
}
