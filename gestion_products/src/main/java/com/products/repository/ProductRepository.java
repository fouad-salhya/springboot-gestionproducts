package com.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.products.entities.ProductEntity;
import com.products.entities.Type;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long>{
	
	ProductEntity findByProductId(String productId);
	
	/*
	@Query("SELECT product FROM  ProductEntity product")
	List<ProductEntity> findAllByQuery();
	*/
	
	@Query(value = "SELECT * FROM  products ", nativeQuery = true)
	List<ProductEntity> findAllByQuery();
	
	List<ProductEntity> findAllByType(Type type);
	
	

}
