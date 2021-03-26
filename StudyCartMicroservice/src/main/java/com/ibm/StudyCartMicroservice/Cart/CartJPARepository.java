package com.ibm.StudyCartMicroservice.Cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import  com.ibm.StudyCartMicroservice.Cart.Cart;

public interface CartJPARepository extends JpaRepository<Cart, String> {
	
	
	/**
     * Find Cart based on loginName.
     */
    public List<Cart> findByusernameLike(String username);
    
    //public void  deleteBycartid(int cartid);
    
    @Transactional    
    @Modifying
    @Query(value="DELETE FROM cart WHERE cartid=?1", nativeQuery = true)
    public void deleteBycartid(int  cartid);
    
    @Transactional    
    @Modifying
    @Query(value="DELETE FROM cart WHERE username=?1", nativeQuery = true)
    public void deleteByusername(String username);

    
}



